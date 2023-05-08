package model.People;

import model.Match.Direction;

import java.util.ArrayList;

public class Troop extends People {
    private final Quality attackPower;
    private final Quality defensePower;
    private final Quality speed;
    private final int fireRange;
    private final Nation nation;
    private State state;
    private final boolean ladderMan;
    private final boolean hasHorse;
    private int finalColumn;
    private int finalRow;

    private ArrayList<Direction> path;

    public Troop(int row, int column, String type, PeopleType peopleType, Quality attackPower, Quality defensePower, Quality speed, int fireRange, Nation nation, boolean ladderMan, boolean hasHorse) {
        super(row, column, type, peopleType);
        this.attackPower = attackPower;
        this.defensePower = defensePower;
        this.speed = speed;
        this.fireRange = fireRange;
        this.nation = nation;
        this.ladderMan = ladderMan;
        this.hasHorse = hasHorse;
        this.state = State.STANDING;
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

    public int getFireRange() {
        return fireRange;
    }

    public Nation getNation() {
        return nation;
    }

    public boolean isLadderMan() {
        return ladderMan;
    }

    public boolean hasHorse() {
        return hasHorse;
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

    public static boolean isMoveValid(int row, int column, Troop troop) {
    }

    public void setFinalDestination(int row, int column) {
        finalRow = row;
        finalColumn = column;
    }

    public void patrol(int row1, int column1, int row2, int column2) {
    }

    public void attack(People targetPeople) {
        if (targetPeople instanceof Worker) targetPeople.takeDamage(attackPower.getValue());
        else targetPeople.takeDamage(attackPower.getValue() - ((Troop) targetPeople).getDefensePower().getValue());
    }
}