package model.People;

import model.Buildings.Building;
import model.Match.Direction;
import model.Match.Governance;
import model.Match.Property;

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
        put("Archer Bow", new ArrayList<Property>(Arrays.asList()));
        put("Slaves", new ArrayList<Property>(Arrays.asList()));
        put("Slingers", new ArrayList<Property>(Arrays.asList()));
        put("Assassins", new ArrayList<Property>(Arrays.asList()));
        put("Horse Archers", new ArrayList<Property>(Arrays.asList()));
        put("Arabian Swordsmen", new ArrayList<Property>(Arrays.asList()));
        put("Fire Throwers", new ArrayList<Property>(Arrays.asList()));
    }};
    private static final HashMap<String, Quality[]> troopPowers = new HashMap<>() {{
        put("Sultan", new Quality[]{Quality.LOW, Quality.LOW, Quality.HIGH});
        put("Archer", new Quality[]{Quality.LOW, Quality.LOW, Quality.HIGH});
        put("Crossbowmen", new Quality[]{Quality.LOW, Quality.MEDIUM, Quality.LOW});
        put("Spearmen", new Quality[]{Quality.MEDIUM, Quality.VERY_LOW, Quality.MEDIUM});
        put("Macemen", new Quality[]{Quality.HIGH, Quality.MEDIUM, Quality.MEDIUM});
        put("Swordsmen", new Quality[]{Quality.VERY_HIGH, Quality.VERY_LOW, Quality.VERY_LOW});
        put("Knight", new Quality[]{Quality.VERY_HIGH, Quality.HIGH, Quality.VERY_HIGH});
        put("Tunneler", new Quality[]{Quality.MEDIUM, Quality.VERY_LOW, Quality.HIGH});
        put("Laddermen", new Quality[]{Quality.ZERO, Quality.VERY_LOW, Quality.HIGH});
        put("Black Monk", new Quality[]{Quality.MEDIUM, Quality.MEDIUM, Quality.LOW});
        put("Archer Bow", new Quality[]{Quality.LOW, Quality.LOW, Quality.HIGH});
        put("Slaves", new Quality[]{Quality.VERY_LOW, Quality.ZERO, Quality.HIGH});
        put("Assassins", new Quality[]{Quality.MEDIUM, Quality.MEDIUM, Quality.MEDIUM});
        put("Horse Archers", new Quality[]{Quality.LOW, Quality.MEDIUM, Quality.VERY_HIGH});
        put("Arabian Swordsmen", new Quality[]{Quality.HIGH, Quality.HIGH, Quality.VERY_HIGH});
        put("Fire Throwers", new Quality[]{Quality.HIGH, Quality.LOW, Quality.VERY_HIGH});
        put("Slingers", new Quality[]{Quality.LOW, Quality.VERY_LOW, Quality.HIGH});
    }};
    private static final HashMap<String, Integer> troopFireRange = new HashMap<>() {{
        put("Sultan", 3);
        put("Archer", 20);
        put("Crossbowmen", 30);
        put("Spearmen", 1);
        put("Macemen", 1);
        put("Swordsmen", 1);
        put("Knight", 2);
        put("Tunneler", 1);
        put("Laddermen", 1);
        put("Black Monk", 1);
        put("Archer Bow", 20);
        put("Slaves", 1);
        put("Assassins", 1);
        put("Horse Archers", 20);
        put("Arabian Swordsmen", 1);
        put("Fire Throwers", 5);
        put("Slingers", 5);
        put("Engineer", 1);
    }};
    private static final HashMap<String, Integer> troopCost = new HashMap<>() {{
        put("Sultan", 1000);
        put("Archer", 0);
        put("Crossbowmen", 0);
        put("Spearmen", 0);
        put("Macemen", 0);
        put("Swordsmen", 0);
        put("Knight", 0);
        put("Tunneler", 0);
        put("Laddermen", 0);
        put("Black Monk", 0);
        put("Archer Bow", 20);
        put("Slaves", 10);
        put("Assassins", 30);
        put("Horse Archers", 30);
        put("Arabian Swordsmen", 30);
        put("Fire Throwers", 40);
        put("Slingers", 50);
        put("Engineer", 0);
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
    private ArrayList<Direction> patrolPath1, patrolPath2;
    private int patrolPathIndex = 0;

    public Troop(Governance governance, int row, int column, String type, PeopleType peopleType) {
        super(governance, row, column, type, peopleType);
        this.attackPower = Troop.getTroopAttackPower(type);
        this.defensePower = Troop.getTroopDefensePower(type);
        this.speed = Troop.getTroopSpeed(type);
        this.fireRange = Troop.getTroopFireRange(type);
        this.nation = Troop.getTroopNation(type);
        this.state = null;
        this.ladderMan = Troop.isTroopWallCrawler(type);
        this.hasHorse = Troop.isTroopHasHorse(type);
        this.patrolMode = false;
        this.patrolPath1 = null;
        this.patrolPath2 = null;
    }

    public static ArrayList<Property> getRequiredResource(String type) {
        return requiredResource.get(type);
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

    public ArrayList<Direction> getPatrolPath1() {
        return patrolPath1;
    }

    public void setPatrolPath1(ArrayList<Direction> patrolPath1) {
        this.patrolPath1 = patrolPath1;
    }

    public ArrayList<Direction> getPatrolPath2() {
        return patrolPath2;
    }

    public void setPatrolPath2(ArrayList<Direction> patrolPath2) {
        this.patrolPath2 = patrolPath2;
    }

    public int getPatrolPathIndex() {
        return patrolPathIndex;
    }

    public void setPatrolPathIndex(int patrolPathIndex) {
        this.patrolPathIndex = patrolPathIndex;
    }
}