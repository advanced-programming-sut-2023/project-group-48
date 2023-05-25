package model.Match;

import model.*;
import model.Buildings.Building;
import model.Buildings.BuildingType;
import model.Buildings.Inn;
import model.Buildings.Storage;
import model.People.People;
import model.People.PeopleType;
import model.People.Troop;

import java.util.ArrayList;

public class Match {
    private final int rounds;
    private int currentRound;
    private ArrayList<User> players;
    private final ArrayList<Governance> governances;
    private Governance matchWinner;
    private User currentPlayer;
    private final Cell[][] map;
    private Cell selectedCell;
    private final int MAX_COLUMN;
    private final int MAX_ROW;
    private final Shop shop;
    private final TurnManager turnManager;
    private Building selectedBuilding;
    private ArrayList<People> selectedUnit;
    private final ArrayList<Troop> allTroops;
    private final ArrayList<People> movingPeople;
    private final ArrayList<Integer> BaseLocationRows;
    private final ArrayList<Integer> BaseLocationColumns;

    {
        BaseLocationRows = new ArrayList<>() {{
            add(30);
            add(90);
            add(120);
            add(170);
            add(30);
            add(90);
            add(120);
            add(170);
        }};
        BaseLocationColumns = new ArrayList<>() {{
            add(50);
            add(50);
            add(50);
            add(50);
            add(150);
            add(150);
            add(150);
            add(150);
        }};

    }

    public Match(int rounds, ArrayList<User> players, int mapNumber) {
        this.rounds = rounds;
        this.currentRound = 1;
        this.players = players;
        this.currentPlayer = players.get(0);
        this.map = Cell.generateMap(mapNumber);
        this.selectedCell = null;
        this.MAX_COLUMN = map[0].length;
        this.MAX_ROW = map.length;
        this.shop = new Shop();
        this.turnManager = new TurnManager(this);
        this.selectedBuilding = null;
        this.selectedUnit = null;
        this.allTroops = new ArrayList<>();
        this.movingPeople = new ArrayList<>();
        this.matchWinner = null;

        this.governances = new ArrayList<>();
        for (User player : players) {
            Governance governance = new Governance(player);
            governances.add(governance);
            player.setGovernance(governance);
            int row, column;
            row = BaseLocationRows.get(players.indexOf(player));
            column = BaseLocationColumns.get(players.indexOf(player));
            allTroops.add(new Troop(governance, row, column, "Sultan", PeopleType.TROOP));
            getCell(row, column).addPeople(allTroops.get(allTroops.size() - 1));
            getCell(row, column).setAGovernmentBase(true);
            Building building = new Building(governance, row, column, "Base", BuildingType.NORMAL, null);
            getCell(row, column).setBuilding(building);
            governance.addBuilding(building);
            Storage storage = new Storage(governance, row + 1, column, "Stockpile", BuildingType.STORAGE, null);
            governance.addProperty(Property.WOOD, 1000);
            governance.addProperty(Property.STONE, 1000);
            governance.addProperty(Property.COIN, 1000);
            getCell(row + 1, column).setBuilding(storage);
            governance.addBuilding(storage);
        }
    }

    public void removePlayer(User player) {
        players.remove(player);
    }


    public int getRounds() {
        return rounds;
    }

    public User getCurrentPlayer() {
        return currentPlayer;
    }

    public int getMAX_COLUMN() {
        return MAX_COLUMN;
    }

    public int getMAX_ROW() {
        return MAX_ROW;
    }

    public TurnManager getTurnManager() {
        return turnManager;
    }

    public void attack(People people1, People people2) {
        if (People.canAttack(people1, people2))
            ((Troop) people1).attackPeople(people2);
        if (People.canAttack(people2, people1))
            ((Troop) people2).attackPeople(people1);
    }

//    public void pourOil(Direction direction) {
//    }
//
//    public void digTunnels(int row, int column) {
//    }
//
//    public void buildEquipment(Equipment equipment) {
//    }
//
//    public void disbandUnit() {
//    }

    public Cell getCell(int row, int column) {
        return map[row - 1][column - 1];
    }

    public Cell getSelectedCell() {
        return selectedCell;
    }

    public void setSelectedCell(Cell selectedCell) {
        this.selectedCell = selectedCell;
    }

    private boolean pathGenerator(int currentRow, int currentColumn, final int destinationRow, final int destinationColumn,
                                  final int borderRow, final int borderColumn, final ArrayList<Direction> validDirections, ArrayList<Direction> path) {
        for (Direction direction : validDirections) {
            path.add(direction);
            switch (direction) {
                case UP:
                    currentRow++;
                    break;
                case DOWN:
                    currentRow--;
                    break;
                case LEFT:
                    currentColumn--;
                    break;
                case RIGHT:
                    currentColumn++;
                    break;
            }
            if (areCoordinatesNotValid(currentRow, currentColumn)) {
                path.remove(path.size() - 1);
                return false;
            }
            if (!map[currentRow - 1][currentColumn - 1].canPeoplePass()) {
                path.remove(path.size() - 1);
                return false;
            }
            if (currentRow == borderRow || currentColumn == borderColumn) {
                path.remove(path.size() - 1);
                return false;
            }
            if (currentColumn == destinationColumn && currentRow == destinationRow) {
                return true;
            }
            if (pathGenerator(currentRow, currentColumn, destinationRow, destinationColumn, borderRow, borderColumn, validDirections, path))
                return true;
            path.remove(path.size() - 1);
            switch (direction) {
                case UP:
                    currentRow--;
                    break;
                case DOWN:
                    currentRow++;
                    break;
                case LEFT:
                    currentColumn++;
                    break;
                case RIGHT:
                    currentColumn--;
                    break;
            }
        }
        return false;
    }

    public ArrayList<Direction> givePath(int startRow, int startColumn, int destinationRow, int destinationColumn) {
        ArrayList<Direction> path = new ArrayList<>();
        final ArrayList<Direction> validDirections = new ArrayList<>();
        int borderRow = -1, borderColumn = -1;
        if (startRow < destinationRow) {
            validDirections.add(Direction.UP);
            borderRow = destinationRow + 1;
        } else if (startRow > destinationRow) {
            validDirections.add(Direction.DOWN);
            borderRow = destinationRow - 1;
        }
        if (startColumn < destinationColumn) {
            validDirections.add(Direction.RIGHT);
            borderColumn = destinationColumn + 1;
        } else if (startRow > destinationColumn) {
            validDirections.add(Direction.LEFT);
            borderColumn = destinationColumn - 1;
        }
        if (pathGenerator(startRow, startColumn, destinationRow, destinationColumn, borderRow, borderColumn, validDirections, path))
            return path;
        return null;
    }

    public void sendTradeRequest(User sender, User receiver, Property property, int amount, int price, String message) {
        Request request = new Request(sender.getGovernance(), receiver.getGovernance(), property, amount, price, message);
        sender.getGovernance().addRequest(request);
        receiver.getGovernance().addRequest(request);
    }

    public ArrayList<Troop> getAllTroops() {
        return allTroops;
    }

    public ArrayList<People> getMovingPeople() {
        return movingPeople;
    }

    public void placePeople(int row, int column, String type, PeopleType peopleType, int count) {
        for (int i = 0; i < count; i++) {
            People people = People.createPeopleByType(currentPlayer.getGovernance(), row, column, type, peopleType);
            getCell(row, column).addPeople(people);
            if (people instanceof Troop) allTroops.add((Troop) people);
        }
    }

    public void removePeople(People people) {
        people.getGovernance().setPopulation(people.getGovernance().getPopulation() - 1);
        getCell(people.getRow(), people.getColumn()).removePeople(people);
        if (people instanceof Troop) allTroops.remove((Troop) people);
        movingPeople.remove(people);
    }

    public int getRoundsPlayed() {
        return currentRound - 1;
    }

    public Building getSelectedBuilding() {
        return selectedBuilding;
    }

    public void setSelectedBuilding(Building selectedBuilding) {
        this.selectedBuilding = selectedBuilding;
    }

    public boolean areCoordinatesNotValid(int row, int column) {
        return row < 1 || row > MAX_ROW || column < 1 || column > MAX_COLUMN;
    }

    public ArrayList<People> getNearByEnemy(int row, int column, int range) {
        ArrayList<People> enemies = new ArrayList<>();
        int startRow = row - range;
        int startColumn = column - range;
        for (int i = 0; i < 2 * range + 1; i++) {
            for (int j = 0; j < 2 * range + 1; j++) {
                if (areCoordinatesNotValid(startRow + i, startColumn + j)) continue;
                for (People person : getCell(startRow + i, startColumn + j).getPeople()) {
                    if (!person.getGovernance().equals(currentPlayer.getGovernance()) && person instanceof Troop) {
                        enemies.add(person);
                    }
                }
            }
        }
        return enemies;
    }

    public ArrayList<Building> getNearByEnemyBuildings(People people, int row, int column, int range) {
        ArrayList<Building> buildings = new ArrayList<>();
        int startRow = row - range;
        int startColumn = column - range;
        for (int i = 0; i < 2 * range + 1; i++) {
            for (int j = 0; j < 2 * range + 1; j++) {
                if (areCoordinatesNotValid(startRow + i, startColumn + j)) continue;
                Building building = getCell(startRow + i, startColumn + j).getBuilding();
                if (building != null && building.getGovernance() != null && !building.getGovernance().equals(people.getGovernance()))
                    buildings.add(building);
            }
        }
        return buildings;
    }

    public ArrayList<People> getSelectedUnit() {
        return selectedUnit;
    }

    public void setSelectedUnit(ArrayList<People> selectedUnit) {
        this.selectedUnit = selectedUnit;
    }

    public ArrayList<Governance> getGovernances() {
        return governances;
    }

    public void nextTurn() {
        currentPlayer = currentPlayer.equals(players.get(players.size() - 1)) ?
                players.get(0) : players.get(players.indexOf(currentPlayer) + 1);
    }

    public boolean isRoundFinished() {
        return currentPlayer.equals(players.get(0));
    }

    public int getSultanCount() {
        int sultanCount = 0;
        for (Governance governance : governances) {
            if (governance.isSultanAlive) sultanCount++;
        }
        return sultanCount;
    }

    public boolean nextRound() {
        if (currentRound == rounds) return true;

        if (getSultanCount() == 1) {
            for (Governance governance : governances) {
                if (governance.isSultanAlive) matchWinner = governance;
            }
            return true;
        }
        turnManager.run();
        currentRound++;
        return false;
    }

    public Shop getShop() {
        return shop;
    }

    public Governance getMatchWinner() {
        return matchWinner;
    }
}