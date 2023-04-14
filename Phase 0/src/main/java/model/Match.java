package model;

import java.util.ArrayList;
import java.util.Map;

public class Match {
    private final ArrayList<User> Players;
    private User currentPlayer;
    private final Unit[][] Map;
    private final int MAX_COLUMN;
    private final int MAX_ROW;
    private final Market Market;
    private final TurnManager Turn_Manager;

    public Match(ArrayList<User> players, int MAX_COLUMN, int MAX_ROW, model.Market market, TurnManager turn_Manager) {
        Players = players;
        this.MAX_COLUMN = MAX_COLUMN;
        this.MAX_ROW = MAX_ROW;
        Map = new Unit[this.MAX_COLUMN][this.MAX_ROW];
        Market = market;
        Turn_Manager = turn_Manager;
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
}