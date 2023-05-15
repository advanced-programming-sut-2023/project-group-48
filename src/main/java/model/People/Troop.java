package model.People;

import model.Buildings.Building;
import model.Match.Governance;
import model.Match.LandType;
import model.Match.Property;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;


public class Troop extends People {
    private static final HashMap<String, ArrayList<Property>> requiredResource = new HashMap<>() {{
        put("Archer", new ArrayList<Property>(Arrays.asList(Property.BOW)));
        put("Crossbowmen", new ArrayList<Property>(Arrays.asList(Property.CROSSBOW)));
        put("Spearmen", new ArrayList<Property>(Arrays.asList(Property.SPEAR)));
        put("Pikemen", new ArrayList<Property>(Arrays.asList(Property.PIKE)));
        put("Macemen", new ArrayList<Property>(Arrays.asList(Property.MACE)));
        put("Swordsmen", new ArrayList<Property>(Arrays.asList(Property.SWORD)));
        put("Knight", new ArrayList<Property>(Arrays.asList(Property.SWORD, Property.HORSE)));
        put("Tunneler", new ArrayList<Property>(Arrays.asList()));
        put("Laddermen", new ArrayList<Property>(Arrays.asList()));
        put("Black Monk", new ArrayList<Property>(Arrays.asList()));
        put("Archer Bow", new ArrayList<Property>(Arrays.asList(Property.BOW)));
        put("Slaves", new ArrayList<Property>(Arrays.asList()));
        put("Slingers", new ArrayList<Property>(Arrays.asList()));
        put("Assassins", new ArrayList<Property>(Arrays.asList()));
        put("Horse Archers", new ArrayList<Property>(Arrays.asList(Property.BOW, Property.HORSE)));
        put("Arabian Swordsmen", new ArrayList<Property>(Arrays.asList(Property.SWORD, Property.HORSE)));
        put("Fire Throwers", new ArrayList<Property>(Arrays.asList()));
    }};
    private static final HashMap<String, Quality[]> troopPowers = new HashMap<>() {{
        put("Sultan", new Quality[]{Quality.LOW, Quality.LOW, Quality.LOW});
        put("Archer", new Quality[]{Quality.LOW, Quality.LOW, Quality.LOW});
        put("Crossbowmen", new Quality[]{Quality.LOW, Quality.LOW, Quality.LOW});
        put("Spearmen", new Quality[]{Quality.LOW, Quality.LOW, Quality.LOW});
        put("Macemen", new Quality[]{Quality.LOW, Quality.LOW, Quality.LOW});
        put("Swordsmen", new Quality[]{Quality.LOW, Quality.LOW, Quality.LOW});
        put("Knight", new Quality[]{Quality.LOW, Quality.LOW, Quality.LOW});
        put("Tunneler", new Quality[]{Quality.LOW, Quality.LOW, Quality.LOW});
        put("Laddermen", new Quality[]{Quality.LOW, Quality.LOW, Quality.LOW});
        put("Black Monk", new Quality[]{Quality.LOW, Quality.LOW, Quality.LOW});
        put("Archer Bow", new Quality[]{Quality.LOW, Quality.LOW, Quality.LOW});
        put("Slaves", new Quality[]{Quality.LOW, Quality.LOW, Quality.LOW});
        put("Assassins", new Quality[]{Quality.LOW, Quality.LOW, Quality.LOW});
        put("Horse Archers", new Quality[]{Quality.LOW, Quality.LOW, Quality.LOW});
        put("Arabian Swordsmen", new Quality[]{Quality.LOW, Quality.LOW, Quality.LOW});
        put("Fire Throwers", new Quality[]{Quality.LOW, Quality.LOW, Quality.LOW});
        put("Slingers", new Quality[]{Quality.LOW, Quality.LOW, Quality.LOW});
    }};
    private static final HashMap<String, Integer> troopFireRange = new HashMap<>() {{
        put("Sultan", 100);
        put("Archer", 100);
        put("Crossbowmen", 100);
        put("Spearmen", 100);
        put("Macemen", 100);
        put("Swordsmen", 100);
        put("Knight", 100);
        put("Tunneler", 100);
        put("Laddermen", 100);
        put("Black Monk", 100);
        put("Archer Bow", 100);
        put("Slaves", 100);
        put("Assassins", 100);
        put("Horse Archers", 100);
        put("Arabian Swordsmen", 100);
        put("Fire Throwers", 100);
        put("Slingers", 100);
        put("Engineer", 100);
    }};
    private static final HashMap<String, Integer> troopCost = new HashMap<>() {{
        put("Sultan", 100);
        put("Archer", 100);
        put("Crossbowmen", 100);
        put("Spearmen", 100);
        put("Macemen", 100);
        put("Swordsmen", 100);
        put("Knight", 100);
        put("Tunneler", 100);
        put("Laddermen", 100);
        put("Black Monk", 100);
        put("Archer Bow", 100);
        put("Slaves", 100);
        put("Assassins", 100);
        put("Horse Archers", 100);
        put("Arabian Swordsmen", 100);
        put("Fire Throwers", 100);
        put("Slingers", 100);
        put("Engineer", 100);
    }};
    private static final ArrayList<String> arabTroops = new ArrayList<>(Arrays.asList("ArcherBow", "Slaves", "Slingers", "Assassins", "HorseArchers", "ArabianSwordsmen", "FireThrowers"));
    private static final ArrayList<String> wallCrawler = new ArrayList<>(Arrays.asList("Laddermen", "Assassins"));
    private static final ArrayList<String> horseMan = new ArrayList<>(Arrays.asList("Knight", "HorseArchers", "ArabianSwordsmen"));

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
        this.attackPower = Troop.getTroopAttackPower(type);
        this.defensePower = Troop.getTroopDefensePower(type);
        this.speed = Troop.getTroopSpeed(type);
        this.fireRange = Troop.getTroopFireRange(type);
        this.nation = Troop.getTroopNation(type);
        this.ladderMan = Troop.isTroopWallCrawler(type);
        this.hasHorse = Troop.isTroopHasHorse(type);
    }

    public static ArrayList<Property> getRequiredResource(String type) {
        return requiredResource.get(type);
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

    public static Quality getTroopAttackPower(String type) {
        return troopPowers.get(type)[0];
    }

    public static Quality getTroopDefensePower(String type) {
        return troopPowers.get(type)[1];
    }

    public static Quality getTroopSpeed(String type) {
        return troopPowers.get(type)[2];
    }

    public static int getTroopFireRange(String type) {
        return troopFireRange.get(type);
    }

    public static Nation getTroopNation(String type) {
        if (arabTroops.contains(type)) return Nation.ARAB;
        return Nation.EUROPE;
    }

    public static boolean isTroopWallCrawler(String type) {
        return wallCrawler.contains(type);
    }

    public static boolean isTroopHasHorse(String type) {
        return horseMan.contains(type);
    }


    public static int getTroopCost(String type) {
        return troopCost.get(type);
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