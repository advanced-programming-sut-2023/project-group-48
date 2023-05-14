package model.People;

import model.Buildings.Building;
import model.Match.Governance;

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
    private boolean patrolMode;
    private int[][] patrolPoints;

    public Troop(Governance governance, int row, int column, String type, PeopleType peopleType) {
        super(governance, row, column, type, peopleType);
        this.attackPower = PeopleType.getTroopAttackPower(type);
        this.defensePower = PeopleType.getTroopDefensePower(type);
        this.speed = PeopleType.getTroopSpeed(type);
        this.fireRange = PeopleType.getTroopFireRange(type);
        this.nation = PeopleType.getTroopNation(type);
        this.ladderMan = PeopleType.isTroopLadderMan(type);
        this.hasHorse = PeopleType.isTroopHasHorse(type);
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
        return Math.pow(troop.getSpeed().getValue(), 2) >= Math.pow(troop.getColumn() - column, 2) + Math.pow(troop.getRow() - row, 2);
    }

    public void setFinalDestination(int row, int column) {
        finalRow = row;
        finalColumn = column;
    }

//    public void patrol(int row1, int column1, int row2, int column2) {
//
//    }

    public void attackPeople(People targetPeople) {
        if (targetPeople instanceof Worker) targetPeople.takeDamage(attackPower.getValue());
        else targetPeople.takeDamage(attackPower.getValue() - ((Troop) targetPeople).getDefensePower().getValue());
    }

    public void attackBuilding(Building targetBuilding) {
        targetBuilding.takeDamage(attackPower.getValue());
    }

    public boolean isInPatrolMode() {
        return patrolMode;
    }

    public void setPatrolMode(boolean patrolMode) {
        this.patrolMode = patrolMode;
    }

    public int[][] getPatrolPoints() {
        return patrolPoints;
    }

    public void setPatrolPoints(int[][] patrolPoints) {
        this.patrolPoints = patrolPoints;
    }
}