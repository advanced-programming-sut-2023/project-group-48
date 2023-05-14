package model.Match;

import model.*;
import model.Buildings.Building;
import model.People.People;
import model.People.PeopleType;
import model.People.Troop;

import java.util.ArrayList;

public class Match {
    private final int rounds;
    private int currentRound;
    private final ArrayList<User> players;
    private final ArrayList<Governance> governances;
    private User currentPlayer;
    private final Cell[][] map;
    private Cell selectedCell;
    private final int MAX_COLUMN;
    private final int MAX_ROW;
    private final Shop shop;
    private final TurnManager turnManager;
    private Building selectedBuilding;
    private ArrayList<People> selectedUnit;
    private final ArrayList<People> allTroops;
    private final ArrayList<People> movingPeople;

    public Match(int rounds, ArrayList<User> players, int mapNumber) {
        this.rounds = rounds;
        this.currentRound = 1;
        this.players = players;
        governances = new ArrayList<>();// TODO: generate governances
        for (User player : players) {
            governances.add(new Governance(player));
        }
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

    private boolean pathGenerator(int currentRow, int currentColumn, int destinationRow, int destinationColumn, final ArrayList<Direction> validDirections, ArrayList<Direction> path) {
        for (Direction direction : Direction.getDirections()) {
            path.add(direction);
            switch (direction) {
                case UP:
                    currentRow--;
                    break;
                case DOWN:
                    currentRow++;
                    break;
                case LEFT:
                    currentColumn--;
                    break;
                case RIGHT:
                    currentColumn++;
                    break;
            }
            if (currentColumn == destinationColumn && currentRow == destinationRow) return true;
            if (!map[currentRow - 1][currentColumn - 1].canPeoplePass()) return false;
            if (pathGenerator(currentColumn, currentRow, destinationColumn, destinationRow, validDirections, path))
                return true;
        }
        return false;
    }

    public ArrayList<Direction> givePath(int startRow, int startColumn, int destinationRow, int destinationColumn) {
        ArrayList<Direction> path = new ArrayList<>();
        final ArrayList<Direction> validDirections = new ArrayList<>();
        if (startRow < destinationRow) validDirections.add(Direction.DOWN);
        else if (startRow > destinationRow) validDirections.add(Direction.UP);
        if (startColumn < destinationColumn) validDirections.add(Direction.RIGHT);
        else if (startRow > destinationColumn) validDirections.add(Direction.LEFT);
        if (pathGenerator(startRow, startColumn, destinationRow, destinationColumn, validDirections, path))
            return path;
        return null;
    }

    public void sendTradeRequest(User sender, User receiver, Property property, int amount, int price, String message) {
        Request request = new Request(sender.getGovernance(), receiver.getGovernance(), property, amount, price, message);
        sender.getGovernance().addRequest(request);
        receiver.getGovernance().addRequest(request);
    }

    public ArrayList<People> getAllTroops() {
        return allTroops;
    }

    public ArrayList<People> getMovingPeople() {
        return movingPeople;
    }

    public void placePeople(int row, int column, String type, PeopleType peopleType, int count) {
        for (int i = 0; i < count; i++) {
            People people = People.createPeopleByType(currentPlayer.getGovernance(), row, column, type, peopleType);
            getCell(row, column).addPeople(people);
            if (people instanceof Troop) allTroops.add(people);
        }
    }

    public void removePeople(People people) {
        people.getGovernance().setPopulation(people.getGovernance().getPopulation() - 1);
        getCell(people.getRow(), people.getColumn()).removePeople(people);
        if (people instanceof Troop) allTroops.remove(people);

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

    public ArrayList<Building> getNearByEnemyBuildings(int row, int column, int range) {
        ArrayList<Building> buildings = new ArrayList<>();
        int startRow = row - range;
        int startColumn = column - range;
        for (int i = 0; i < 2 * range + 1; i++) {
            for (int j = 0; j < 2 * range + 1; j++) {
                if (areCoordinatesNotValid(startRow + i, startColumn + j)) continue;
                Building building = getCell(startRow + i, startColumn + j).getBuilding();
                if (building != null && !building.getGovernance().equals(currentPlayer.getGovernance()))
                    buildings.add(getCell(startRow + i, startColumn + j).getBuilding());
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

    public void nextRound() {
        turnManager.run();
        currentRound++;
    }

    public Shop getShop() {
        return shop;
    }
}