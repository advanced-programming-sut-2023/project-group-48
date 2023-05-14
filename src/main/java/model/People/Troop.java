package model.People;

import model.Buildings.Building;
import model.Match.Governance;
import model.Match.Property;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;



public class Troop extends People {
    private static final HashMap<String, ArrayList<Property>> requiredResource = new HashMap<>(){{requiredResource.put("Archer", new ArrayList<Property>(Arrays.asList(Property.BOW)));
        requiredResource.put("Crossbowmen", new ArrayList<Property>(Arrays.asList(Property.BOW)));
        requiredResource.put("Spearmen", new ArrayList<Property>(Arrays.asList(Property.SPEAR)));
        requiredResource.put("Pikemen", new ArrayList<Property>(Arrays.asList(Property.PIKE)));
        requiredResource.put("Macemen", new ArrayList<Property>(Arrays.asList(Property.MACE)));
        requiredResource.put("Swordsmen", new ArrayList<Property>(Arrays.asList(Property.SWORD)));
        requiredResource.put("Knight", new ArrayList<Property>(Arrays.asList(Property.SWORD, Property.HORSE)));
        requiredResource.put("Tunneler", new ArrayList<Property>(Arrays.asList()));
        requiredResource.put("Laddermen", new ArrayList<Property>(Arrays.asList()));
        requiredResource.put("Black Monk", new ArrayList<Property>(Arrays.asList()));
        requiredResource.put("Archer Bow", new ArrayList<Property>(Arrays.asList(Property.BOW)));
        requiredResource.put("Slaves", new ArrayList<Property>(Arrays.asList()));
        requiredResource.put("Slingers", new ArrayList<Property>(Arrays.asList()));
        requiredResource.put("Assassins", new ArrayList<Property>(Arrays.asList()));
        requiredResource.put("Horse Archers", new ArrayList<Property>(Arrays.asList(Property.BOW, Property.HORSE)));
        requiredResource.put("Arabian Swordsmen", new ArrayList<Property>(Arrays.asList(Property.SWORD, Property.HORSE)));
        requiredResource.put("Fire Throwers", new ArrayList<Property>(Arrays.asList()));
    }};

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
    public static HashMap<String, ArrayList<Property>> getRequiredResource() {
        return requiredResource;
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
        //TODO : is move valid
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