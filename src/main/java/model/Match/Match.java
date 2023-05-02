package model.Match;

import model.*;
import model.People.Troop;

import java.util.ArrayList;

public class Match {
    private final int rounds;
    private final ArrayList<User> Players;
    private User currentPlayer;
    private final Cell[][] map;
    private final int MAX_COLUMN;
    private final int MAX_ROW;
    private final Market market;
    private final Shop shop;
    private final TurnManager Turn_Manager;
    private ArrayList<Trade> trades;

    public Match(int rounds, ArrayList<User> players, int MAX_COLUMN, int MAX_ROW) {
        this.rounds = rounds;
        Players = players;
        this.MAX_COLUMN = MAX_COLUMN;
        this.MAX_ROW = MAX_ROW;
        this.map = new Cell[this.MAX_COLUMN][this.MAX_ROW];
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
        return Market;
    }

    public TurnManager getTurn_Manager() {
        return Turn_Manager;
    }

    public void fight(Troop troop1, Troop troops2) {
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
}