package model.Match;

import model.*;
import model.People.Equipment;
import model.People.Troop;

import java.util.ArrayList;

public class Match {
    private final int rounds;
    private final ArrayList<User> Players;
    private User currentPlayer;
    private final Unit[][] map;
    private final int MAX_COLUMN;
    private final int MAX_ROW;
    private final model.Match.Market Market;
    private Shop shop;
    private final TurnManager Turn_Manager;
    private ArrayList<Trade> trades;

    public Match(int rounds, ArrayList<User> players, int MAX_COLUMN, int MAX_ROW, Market market, TurnManager turn_Manager) {
        this.rounds = rounds;
        Players = players;
        this.MAX_COLUMN = MAX_COLUMN;
        this.MAX_ROW = MAX_ROW;
        map = new Unit[this.MAX_COLUMN][this.MAX_ROW];
        Market = market;
        Turn_Manager = turn_Manager;
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

    public void pourOil(Direction direccion) {
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

    public Unit getUnit(int column, int row) {
        return map[column - 1][row - 1];
    }
}