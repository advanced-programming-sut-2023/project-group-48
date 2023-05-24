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
        put("Sultan", 1500);
        put("Archer", 100);
        put("Crossbowmen", 150);
        put("Spearmen", 200);
        put("Macemen", 200);
        put("Swordsmen", 250);
        put("Knight", 350);
        put("Tunneler", 200);
        put("Laddermen", 200);
        put("Black Monk", 200);
        put("Archer Bow", 200);
        put("Slaves", 70);
        put("Assassins", 200);
        put("Horse Archers", 200);
        put("Arabian Swordsmen", 200);
        put("Fire Throwers", 100);
        put("Slingers", 100);
        put("Engineer", 100);
    }};


    private static final HashMap<String, ArrayList<LandType>> notValidLandTypesToCreate = new HashMap<>() {{
        put("Sultan", new ArrayList<LandType>(Arrays.asList(LandType.ROCK,LandType.BIGPOND,LandType.SEA,LandType.OIL,LandType.IRON,LandType.RIVER)));
        put("Archer", new ArrayList<LandType>(Arrays.asList(LandType.ROCK,LandType.BIGPOND,LandType.SEA,LandType.OIL,LandType.IRON,LandType.RIVER)));
        put("Crossbowmen", new ArrayList<LandType>(Arrays.asList(LandType.ROCK,LandType.BIGPOND,LandType.SEA,LandType.OIL,LandType.IRON,LandType.RIVER)));
        put("Spearmen", new ArrayList<LandType>(Arrays.asList(LandType.ROCK,LandType.BIGPOND,LandType.SEA,LandType.OIL,LandType.IRON,LandType.RIVER)));
        put("Pikemen", new ArrayList<LandType>(Arrays.asList(LandType.ROCK,LandType.BIGPOND,LandType.SEA,LandType.OIL,LandType.IRON,LandType.RIVER)));
        put("Macemen", new ArrayList<LandType>(Arrays.asList(LandType.ROCK,LandType.BIGPOND,LandType.SEA,LandType.OIL,LandType.IRON,LandType.RIVER)));
        put("Swordsmen", new ArrayList<LandType>(Arrays.asList(LandType.ROCK,LandType.BIGPOND,LandType.SEA,LandType.OIL,LandType.IRON,LandType.RIVER)));
        put("Knight", new ArrayList<LandType>(Arrays.asList(LandType.ROCK,LandType.BIGPOND,LandType.SEA,LandType.OIL,LandType.IRON,LandType.RIVER)));
        put("Tunneler", new ArrayList<LandType>(Arrays.asList(LandType.ROCK,LandType.BIGPOND,LandType.SEA,LandType.OIL,LandType.IRON,LandType.RIVER)));
        put("Laddermen", new ArrayList<LandType>(Arrays.asList(LandType.ROCK,LandType.BIGPOND,LandType.SEA,LandType.OIL,LandType.IRON,LandType.RIVER)));
        put("Black Monk", new ArrayList<LandType>(Arrays.asList(LandType.ROCK,LandType.BIGPOND,LandType.SEA,LandType.OIL,LandType.IRON,LandType.RIVER)));
        put("Archer Bow", new ArrayList<LandType>(Arrays.asList(LandType.ROCK,LandType.BIGPOND,LandType.SEA,LandType.OIL,LandType.IRON,LandType.RIVER)));
        put("Slaves", new ArrayList<LandType>(Arrays.asList(LandType.ROCK,LandType.BIGPOND,LandType.SEA,LandType.OIL,LandType.IRON,LandType.RIVER)));
        put("Slingers", new ArrayList<LandType>(Arrays.asList(LandType.ROCK,LandType.BIGPOND,LandType.SEA,LandType.OIL,LandType.IRON,LandType.RIVER)));
        put("Horse Archers", new ArrayList<LandType>(Arrays.asList(LandType.ROCK,LandType.BIGPOND,LandType.SEA,LandType.OIL,LandType.IRON,LandType.RIVER)));
        put("Arabian Swordsmen", new ArrayList<LandType>(Arrays.asList(LandType.ROCK,LandType.BIGPOND,LandType.SEA,LandType.OIL,LandType.IRON,LandType.RIVER)));
        put("Fire Throwers", new ArrayList<LandType>(Arrays.asList(LandType.ROCK,LandType.BIGPOND,LandType.SEA,LandType.OIL,LandType.IRON,LandType.RIVER)));
        put("Engineer", new ArrayList<LandType>(Arrays.asList(LandType.ROCK,LandType.BIGPOND,LandType.SEA,LandType.RIVER)));
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