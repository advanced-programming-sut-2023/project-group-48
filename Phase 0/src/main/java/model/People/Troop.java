package model.People;

import model.Direction;

import java.util.ArrayList;

public class Troop extends People {
    private final Quality attackPower;
    private final Quality defensePower;
    private final Quality speed;
    private final Nation nation;
    private State state;
    private int finalColumn;
    private int finalRow;

    private ArrayList<Direction> path;

    public Troop(String job, int column, int row, Quality attackPower, Quality defesePower, Quality speed, Nation nation) {
        super(job, column, row);
        this.attackPower = attackPower;
        this.defensePower = defesePower;
        this.speed = speed;
        this.nation = nation;
    }

    public Quality getAttackPower() {
        return attackPower;
    }

    public Quality getDefensePower() {
        return defensePower;
    }

    public Quality getSpeed() {
        return speed;
    }

    public Nation getNation() {
        return nation;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public int getFinalColumn() {
        return finalColumn;
    }

    public int getFinalRow() {
        return finalRow;
    }

    public static boolean isMoveValid(int column, int row, Troop troop) {}

    public void setFinalDestination(int column, int row){}

    public void patrol(int column1, int row1, int column2, int row2) {}
}