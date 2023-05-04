package model.Match;

import model.*;
import model.People.People;
import model.People.Troop;

import java.util.ArrayList;

public class Match {
    private final int rounds;
    private final ArrayList<User> players;
    private User currentPlayer;
    private final Cell[][] map;
    private final int MAX_COLUMN;
    private final int MAX_ROW;
    private final Market market;
    private final Shop shop;
    private final TurnManager Turn_Manager;
    private ArrayList<Trade> trades;

    public Match(int rounds, ArrayList<User> players, int mapNumber) {
        this.rounds = rounds;
        this.players = players;
        this.map = Cell.generateMap(mapNumber);
        this.MAX_COLUMN = map[0].length;
        this.MAX_ROW = map.length;
        this.market = new Market();
        this.shop = new Shop();
        this.Turn_Manager = new TurnManager();
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

    public Market getMarket() {
        return market;
    }

    public TurnManager getTurn_Manager() {
        return Turn_Manager;
    }

    public void fight(People people1, People people2) {
        if (People.canAttack(people1, people2))
            ((Troop) people1).attack(people2);
        if (People.canAttack(people2, people1))
            ((Troop) people2).attack(people1);
    }

    public void pourOil(Direction direction) {
    }

    public void digTunnels(int column, int row) {
    }

    public void buildEquipment(Equipment equipment) {
    }

    public void disbandUnit() {
    }

    public ArrayList<Trade> getTrades() {
        return trades;
    }

    public Cell getCell(int column, int row) {
        return map[column - 1][row - 1];
    }

    private boolean pathGenerator(int currentColumn, int currentRow, int destinationColumn, int destinationRow, final ArrayList<Direction> validDirections, ArrayList<Direction> path) {
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
            if (!map[currentRow][currentColumn].canPeopleGoThrough()) return false;
            if (pathGenerator(currentColumn, currentRow, destinationColumn, destinationRow, validDirections, path))
                return true;
        }
        return false;
    }

    public ArrayList<Direction> givePath(People people, int destinationColumn, int destinationRow) {
        ArrayList<Direction> path = new ArrayList<>();
        final ArrayList<Direction> validDirections = new ArrayList<>();
        if (people.getRow() < destinationRow) validDirections.add(Direction.DOWN);
        else if (people.getRow() > destinationRow) validDirections.add(Direction.UP);
        if (people.getColumn() < destinationColumn) validDirections.add(Direction.RIGHT);
        else if (people.getColumn() > destinationColumn) validDirections.add(Direction.LEFT);
        if (pathGenerator(people.getColumn(), people.getRow(), destinationColumn, destinationRow, validDirections, path))
            return path;
        return null;
    }
}