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
    WORKER(new ArrayList<>(List.of("Engineer", "Baker", "Brewer", "Farmer", "Hunter", "Innkeeper", "Miller", "Iron Miner", "Quarry Worker", "Woodcutter", "Stone Miner", "Trader", "Armourer", "Blacksmith", "Fletcher", "Tanner")));

    private static final PeopleType[] peopleTypes = {TROOP, WORKER};

    private static final HashMap<String, Integer> peopleHp = new HashMap<>() {{
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


    private static final HashMap<String, ArrayList<LandType>> notValidLandTypesToCreate = new HashMap<>() {{
        put("Sultan", new ArrayList<LandType>(Arrays.asList(LandType.ROCK)));
        put("Archer", new ArrayList<LandType>(Arrays.asList(LandType.ROCK)));
        put("Crossbowmen", new ArrayList<LandType>(Arrays.asList(LandType.ROCK)));
        put("Spearmen", new ArrayList<LandType>(Arrays.asList(LandType.ROCK)));
        put("Pikemen", new ArrayList<LandType>(Arrays.asList(LandType.ROCK)));
        put("Macemen", new ArrayList<LandType>(Arrays.asList(LandType.ROCK)));
        put("Swordsmen", new ArrayList<LandType>(Arrays.asList(LandType.ROCK)));
        put("Knight", new ArrayList<LandType>(Arrays.asList(LandType.ROCK)));
        put("Tunneler", new ArrayList<LandType>(Arrays.asList(LandType.ROCK)));
        put("Laddermen", new ArrayList<LandType>(Arrays.asList(LandType.ROCK)));
        put("Black Monk", new ArrayList<LandType>(Arrays.asList(LandType.ROCK)));
        put("Archer Bow", new ArrayList<LandType>(Arrays.asList(LandType.ROCK)));
        put("Slaves", new ArrayList<LandType>(Arrays.asList(LandType.ROCK)));
        put("Slingers", new ArrayList<LandType>(Arrays.asList(LandType.ROCK)));
        put("Horse Archers", new ArrayList<LandType>(Arrays.asList(LandType.ROCK)));
        put("Arabian Swordsmen", new ArrayList<LandType>(Arrays.asList(LandType.ROCK)));
        put("Fire Throwers", new ArrayList<LandType>(Arrays.asList(LandType.ROCK)));
        put("Engineer", new ArrayList<LandType>(Arrays.asList(LandType.ROCK)));
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

    public static ArrayList<LandType> getNotValidLandTypes(String peopleType) {
        return notValidLandTypesToCreate.get(peopleType);
    }

    public static boolean isTroop(String type) {
        return TROOP.People.contains(type);
    }
}