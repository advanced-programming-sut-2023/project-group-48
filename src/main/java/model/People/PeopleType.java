package model.People;

import model.Buildings.Building;
import model.Match.LandType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public enum PeopleType {
    TROOP(new ArrayList<>(List.of("Sultan", "Archer", "Crossbowmen", "Spearmen", "Pikemen", "Macemen", "Swordsmen",
                                           "Knight", "Tunneler", "Laddermen", "Black Monk", "Archer Bow", "Slaves",
                                           "Slingers", "Assassins", "Horse Archers", "Arabian Swordsmen", "Fire Throwers"))),
    WORKER(new ArrayList<>(List.of("Engineer")))
    ;

    private static final PeopleType[] peopleTypes = {TROOP, WORKER};
    private static final HashMap<String, Integer> peopleHp = new HashMap<>(){{
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
    private static final HashMap<String, Quality[]> troopPowers = new HashMap<>(){{
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
    private static final HashMap<String, Integer> troopFireRange = new HashMap<>(){{
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
    // TODO: 4/30/2021
    private static final ArrayList<String> arabTroops = new ArrayList<>(Arrays.asList());
    private static final ArrayList<String> ladderMen = new ArrayList<>(Arrays.asList());
    private static final ArrayList<String> horseMan = new ArrayList<>(Arrays.asList());
    private static final HashMap<String , ArrayList<LandType>> validLandTypesToCreate = new HashMap<>(){{
        put("Sultan", new ArrayList<LandType>(Arrays.asList(LandType.LAND)));
        put("Archer", new ArrayList<LandType>(Arrays.asList(LandType.LAND)));
        put("Crossbowmen", new ArrayList<LandType>(Arrays.asList(LandType.LAND)));
        put("Spearmen", new ArrayList<LandType>(Arrays.asList(LandType.LAND)));
        put("Pikemen", new ArrayList<LandType>(Arrays.asList(LandType.LAND)));
        put("Macemen", new ArrayList<LandType>(Arrays.asList(LandType.LAND)));
        put("Swordsmen", new ArrayList<LandType>(Arrays.asList(LandType.LAND)));
        put("Knight", new ArrayList<LandType>(Arrays.asList(LandType.LAND)));
        put("Tunneler", new ArrayList<LandType>(Arrays.asList(LandType.LAND)));
        put("Laddermen", new ArrayList<LandType>(Arrays.asList(LandType.LAND)));
        put("Black Monk", new ArrayList<LandType>(Arrays.asList(LandType.LAND)));
        put("Archer Bow", new ArrayList<LandType>(Arrays.asList(LandType.LAND)));
        put("Slaves", new ArrayList<LandType>(Arrays.asList(LandType.LAND)));
        put("Slingers", new ArrayList<LandType>(Arrays.asList(LandType.LAND)));
        put("Horse Archers", new ArrayList<LandType>(Arrays.asList(LandType.LAND)));
        put("Arabian Swordsmen", new ArrayList<LandType>(Arrays.asList(LandType.LAND)));
        put("Fire Throwers", new ArrayList<LandType>(Arrays.asList(LandType.LAND)));
        put("Engineer", new ArrayList<LandType>(Arrays.asList(LandType.LAND)));
    }};
    private final ArrayList<String> People;

    PeopleType(ArrayList<String> people) {
        People = people;
    }

    public static PeopleType getPersonType(String type) {
        for (PeopleType peopleType : peopleTypes) {
            if (peopleType.People.contains(type)) return peopleType;
        }
        return null;
    }
    public static int getPeopleHp(String type) {
        return peopleHp.get(type);
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

    public static boolean isTroopLadderMan(String type) {
        return ladderMen.contains(type);
    }

    public static boolean isTroopHasHorse(String type) {
        return horseMan.contains(type);
    }

    public static ArrayList<LandType> getValidLandTypes(String peopleType) {

    }
}