package model.People;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import model.Match.LandType;

import java.util.*;

public enum PeopleType {
    TROOP(new ArrayList<>(List.of("Sultan", "Archer", "Crossbowmen", "Spearmen", "Pikemen", "Macemen", "Swordsmen",
            "Knight", "Tunneler", "Laddermen", "Black Monk", "Archer Bow", "Slaves",
            "Slingers", "Assassins", "Horse Archers", "Arabian Swordsmen", "Fire Throwers"))),
    WORKER(new ArrayList<>(List.of("Engineer", "Baker", "Brewer", "Farmer", "Hunter", "Innkeeper", "Miller", "Iron Miner",
            "Quarry Worker", "Woodcutter", "Stone Miner", "Trader", "Armourer", "Blacksmith", "Fletcher", "Tanner")));

    public static final int WALKIG_IMAGE_COUNT = 16;
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
        put("Sultan", new ArrayList<>(Arrays.asList(LandType.ROCK, LandType.BIG_POND, LandType.SEA, LandType.OIL, LandType.IRON, LandType.RIVER)));
        put("Archer", new ArrayList<>(Arrays.asList(LandType.ROCK, LandType.BIG_POND, LandType.SEA, LandType.OIL, LandType.IRON, LandType.RIVER)));
        put("Crossbowmen", new ArrayList<>(Arrays.asList(LandType.ROCK, LandType.BIG_POND, LandType.SEA, LandType.OIL, LandType.IRON, LandType.RIVER)));
        put("Spearmen", new ArrayList<>(Arrays.asList(LandType.ROCK, LandType.BIG_POND, LandType.SEA, LandType.OIL, LandType.IRON, LandType.RIVER)));
        put("Pikemen", new ArrayList<>(Arrays.asList(LandType.ROCK, LandType.BIG_POND, LandType.SEA, LandType.OIL, LandType.IRON, LandType.RIVER)));
        put("Macemen", new ArrayList<>(Arrays.asList(LandType.ROCK, LandType.BIG_POND, LandType.SEA, LandType.OIL, LandType.IRON, LandType.RIVER)));
        put("Swordsmen", new ArrayList<>(Arrays.asList(LandType.ROCK, LandType.BIG_POND, LandType.SEA, LandType.OIL, LandType.IRON, LandType.RIVER)));
        put("Knight", new ArrayList<>(Arrays.asList(LandType.ROCK, LandType.BIG_POND, LandType.SEA, LandType.OIL, LandType.IRON, LandType.RIVER)));
        put("Tunneler", new ArrayList<>(Arrays.asList(LandType.ROCK, LandType.BIG_POND, LandType.SEA, LandType.OIL, LandType.IRON, LandType.RIVER)));
        put("Laddermen", new ArrayList<>(Arrays.asList(LandType.ROCK, LandType.BIG_POND, LandType.SEA, LandType.OIL, LandType.IRON, LandType.RIVER)));
        put("Black Monk", new ArrayList<>(Arrays.asList(LandType.ROCK, LandType.BIG_POND, LandType.SEA, LandType.OIL, LandType.IRON, LandType.RIVER)));
        put("Archer Bow", new ArrayList<>(Arrays.asList(LandType.ROCK, LandType.BIG_POND, LandType.SEA, LandType.OIL, LandType.IRON, LandType.RIVER)));
        put("Slaves", new ArrayList<>(Arrays.asList(LandType.ROCK, LandType.BIG_POND, LandType.SEA, LandType.OIL, LandType.IRON, LandType.RIVER)));
        put("Assassins", new ArrayList<>(Arrays.asList(LandType.ROCK, LandType.BIG_POND, LandType.SEA, LandType.OIL, LandType.IRON, LandType.RIVER)));
        put("Slingers", new ArrayList<>(Arrays.asList(LandType.ROCK, LandType.BIG_POND, LandType.SEA, LandType.OIL, LandType.IRON, LandType.RIVER)));
        put("Horse Archers", new ArrayList<>(Arrays.asList(LandType.ROCK, LandType.BIG_POND, LandType.SEA, LandType.OIL, LandType.IRON, LandType.RIVER)));
        put("Arabian Swordsmen", new ArrayList<>(Arrays.asList(LandType.ROCK, LandType.BIG_POND, LandType.SEA, LandType.OIL, LandType.IRON, LandType.RIVER)));
        put("Fire Throwers", new ArrayList<>(Arrays.asList(LandType.ROCK, LandType.BIG_POND, LandType.SEA, LandType.OIL, LandType.IRON, LandType.RIVER)));
        put("Engineer", new ArrayList<>(Arrays.asList(LandType.ROCK, LandType.BIG_POND, LandType.SEA, LandType.RIVER)));
    }};

    private static final HashMap<String, ArrayList<ArrayList<ArrayList<ImagePattern>>>> movingImages = new HashMap<>();
    private static final HashMap<String, ArrayList<ImagePattern>> standingImages = new HashMap<>();


    private final ArrayList<String> people;

    PeopleType(ArrayList<String> people) {
        this.people = people;
    }

    public static PeopleType getPersonType(String type) {
        for (PeopleType peopleType : peopleTypes) {
            if (peopleType.people.contains(type)) return peopleType;
        }
        return null;
    }

    public static int getPeopleHp(String type) {
        if (peopleHp.containsKey(type)) return peopleHp.get(type);
        else return 100;
    }

    public static ArrayList<LandType> getNotValidLandTypes(String peopleType) {
        return notValidLandTypesToCreate.get(peopleType);
    }

    public static boolean isTroop(String type) {
        return TROOP.people.contains(type);
    }

    public static ArrayList<ImagePattern> getMovingImages(String type, int colorNumber, int directionNumber) {
        if (!movingImages.containsKey(type)) {
            movingImages.put(type, new ArrayList<>());
            for (int i = 0; i < 8; i++) {
                movingImages.get(type).add(new ArrayList<>());
                for (int j = 0; j < 8; j++) {
                    movingImages.get(type).get(i).add(new ArrayList<>());
                    for (int k = 0; k < WALKIG_IMAGE_COUNT; k++) {
                        movingImages.get(type).get(i).get(j).add(new ImagePattern(new Image(Objects.requireNonNull(
                                PeopleType.class.getResource("/people/Color " + (i + 1) + "/" + type + "/" + (j + 1) + "/" + (k + 1) + ".png")).toString())));
                    }
                }
            }
        }
        return movingImages.get(type).get(colorNumber).get(directionNumber);
    }

    public static ImagePattern getStandingImage(String type, int colorNumber) {
        if (!standingImages.containsKey(type)) {
            standingImages.put(type, new ArrayList<>());
            for (int i = 0; i < 8; i++) {
                standingImages.get(type).add(new ImagePattern(new Image(Objects.requireNonNull(PeopleType.class.getResource("/people/Color " + (i + 1) + "/" + type + "/4/1.png")).toExternalForm())));
            }
        }
        return standingImages.get(type).get(colorNumber);
    }

    public static String getTroopType(int index) {
        return TROOP.people.get(index);
    }
}