package model.Buildings;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import model.Match.LandType;
import model.Match.Property;

import java.util.*;

public enum BuildingType {
    NORMAL(new ArrayList<>(Arrays.asList("Base", "Drawbridge", "Hovel", "Caged War Dogs", "Siege Tent", "Church", "Cathedral",
            "Tall Wall", "Short Wall", "Stair", "Good Things", "Bad Things", "Shop"))), // 13
    GATEHOUSE(new ArrayList<>(Arrays.asList("Small Stone Gatehouse", "Large Stone Gatehouse"))), // 2
    TOWER(new ArrayList<>(Arrays.asList("Lookout Tower", "Perimeter Tower", "Defence Turret", "Square Tower", "Round Tower"))), // 5
    INDUSTRIAL_CENTER(new ArrayList<>(Arrays.asList("Stable", "Mill", "Iron Mine", "Pitch Rig", "ًQuarry", "Woodcutter",
            "Oil Smelter", "Apple Orchard", "Diary Farmer", "Hops Farmer", "Hunter Post", "Wheat Farmer", "Bakery", "Brewer",
            "Armourer", "Blacksmith", "Fletcher", "Pole Turner"))), // 18
    INN(new ArrayList<>(List.of("Inn"))), // 1
    TRAP(new ArrayList<>(Arrays.asList("Killing Pit", "Pitch Ditch"))), // 2
    RECRUITMENT_CENTER(new ArrayList<>(Arrays.asList("Barrack", "Mercenary Post", "Engineer Guild"))), // 3
    STORAGE(new ArrayList<>(Arrays.asList("Armoury", "Stockpile", "Granary"))), // 3
    ENVIRONMENT(new ArrayList<>(Arrays.asList("Desert Shrub", "Cherry Palm", "olive Tree", "Coconut Palm", "Date", "Tree", "Rock"))); // 7

    private static final BuildingType[] buildingTypes = {NORMAL, GATEHOUSE, TOWER, INDUSTRIAL_CENTER, INN, TRAP, RECRUITMENT_CENTER, STORAGE, ENVIRONMENT};
    private static final ArrayList<String> castleTypes = new ArrayList<>(Arrays.asList("Small Stone Gatehouse", "Large Stone Gatehouse",
            "Drawbridge", "Lookout Tower", "Perimeter Tower", "Defence Turret", "Square Tower", "Round Tower", "Armoury",
            "Barrack", "Mercenary Post", "Engineer Guild", "Killing Pit", "Oil Smelter", "Pitch Ditch", "Caged War Dogs", "Siege Tent", "Stable"));
    private static final HashMap<String, Integer> buildingsHp = new HashMap<>() {{
        put("Base", 1000);
        put("Drawbridge", 300);
        put("Hovel", 400);
        put("Caged War Dogs", 300);
        put("Siege Tent", 300);
        put("Church", 500);
        put("Cathedral", 700);
        put("Tall Wall", 300);
        put("Short Wall", 200);
        put("Stair", 200);
        put("Good Things", 100);
        put("Bad Things", 100);
        put("Shop", 300);
        put("Small Stone Gatehouse", 300);
        put("Large Stone Gatehouse", 400);
        put("Lookout Tower", 400);
        put("Perimeter Tower", 400);
        put("Defence Turret", 300);
        put("Square Tower", 400);
        put("Round Tower", 400);
        put("Stable", 400);
        put("Mill", 400);
        put("Iron Mine", 400);
        put("Pitch Rig", 400);
        put("Quarry", 400);
        put("Woodcutter", 100);
        put("Oil Smelter", 300);
        put("Apple Orchard", 200);
        put("Diary Farmer", 300);
        put("Hops Farmer", 200);
        put("Hunter Post", 300);
        put("Wheat Farmer", 300);
        put("Bakery", 300);
        put("Brewer", 300);
        put("Armourer", 400);
        put("Blacksmith", 500);
        put("Fletcher", 400);
        put("Pole Turner", 400);
        put("Inn", 300);
        put("Killing Pit", 300);
        put("Pitch Ditch", 300);
        put("Barrack", 500);
        put("Mercenary Post", 300);
        put("Engineer Guild", 400);
        put("Armoury", 500);
        put("Stockpile", 500);
        put("Granary", 400);
        //TODO: soldiers should not hurt all buildings(like trees and rocks)
        //TODO: adding shop and its effect
    }};
    private static final HashMap<String, ArrayList<LandType>> ValidLandTypes = new HashMap<>() {{
        put("Base", new ArrayList<>(Arrays.asList(LandType.LAND)));
        put("Drawbridge", new ArrayList<>(Arrays.asList(LandType.LAND)));
        put("Hovel", new ArrayList<>(Arrays.asList(LandType.LAND)));
        put("Caged War Dogs", new ArrayList<>(Arrays.asList(LandType.LAND)));
        put("Siege Tent", new ArrayList<>(Arrays.asList(LandType.LAND)));
        put("Church", new ArrayList<>(Arrays.asList(LandType.LAND)));
        put("Cathedral", new ArrayList<>(Arrays.asList(LandType.LAND)));
        put("Tall Wall", new ArrayList<>(Arrays.asList(LandType.LAND)));
        put("Short Wall", new ArrayList<>(Arrays.asList(LandType.LAND)));
        put("Stair", new ArrayList<>(Arrays.asList(LandType.LAND)));
        put("Small Stone Gatehouse", new ArrayList<>(Arrays.asList(LandType.LAND)));
        put("Large Stone Gatehouse", new ArrayList<>(Arrays.asList(LandType.LAND)));
        put("Shop", new ArrayList<>(Arrays.asList(LandType.LAND)));
        put("Lookout Tower", new ArrayList<>(Arrays.asList(LandType.LAND)));
        put("Perimeter Tower", new ArrayList<>(Arrays.asList(LandType.LAND)));
        put("Defence Turret", new ArrayList<>(Arrays.asList(LandType.LAND)));
        put("Square Tower", new ArrayList<>(Arrays.asList(LandType.LAND)));
        put("Round Tower", new ArrayList<>(Arrays.asList(LandType.LAND)));
        put("Stable", new ArrayList<>(Arrays.asList(LandType.LAND)));
        put("Mill", new ArrayList<>(Arrays.asList(LandType.LAND)));
        put("Iron Mine", new ArrayList<>(Arrays.asList(LandType.LAND)));
        put("Pitch Rig", new ArrayList<>(Arrays.asList(LandType.LAND)));
        put("Quarry", new ArrayList<>(Arrays.asList(LandType.LAND)));
        put("Woodcutter", new ArrayList<>(Arrays.asList(LandType.LAND)));
        put("Oil Smelter", new ArrayList<>(Arrays.asList(LandType.LAND)));
        put("Apple Orchard", new ArrayList<>(Arrays.asList(LandType.LAND)));
        put("Diary Farmer", new ArrayList<>(Arrays.asList(LandType.LAND)));
        put("Hops Farmer", new ArrayList<>(Arrays.asList(LandType.LAND)));
        put("Hunter Post", new ArrayList<>(Arrays.asList(LandType.LAND)));
        put("Wheat Farmer", new ArrayList<>(Arrays.asList(LandType.LAND)));
        put("Bakery", new ArrayList<>(Arrays.asList(LandType.LAND)));
        put("Brewer", new ArrayList<>(Arrays.asList(LandType.LAND)));
        put("Armourer", new ArrayList<>(Arrays.asList(LandType.LAND)));
        put("Blacksmith", new ArrayList<>(Arrays.asList(LandType.LAND)));
        put("Fletcher", new ArrayList<>(Arrays.asList(LandType.LAND)));
        put("Pole Turner", new ArrayList<>(Arrays.asList(LandType.LAND)));
        put("Inn", new ArrayList<>(Arrays.asList(LandType.LAND)));
        put("Killing Pit", new ArrayList<>(Arrays.asList(LandType.LAND)));
        put("Barrack", new ArrayList<>(Arrays.asList(LandType.LAND)));
        put("Pitch Ditch", new ArrayList<>(Arrays.asList(LandType.LAND)));
        put("Mercenary Post", new ArrayList<>(Arrays.asList(LandType.LAND)));
        put("Engineer Guild", new ArrayList<>(Arrays.asList(LandType.LAND)));
        put("Armoury", new ArrayList<>(Arrays.asList(LandType.LAND)));
        put("Stockpile", new ArrayList<>(Arrays.asList(LandType.LAND)));
        put("Granary", new ArrayList<>(Arrays.asList(LandType.LAND)));
    }};
    private static final HashMap<String, HashMap<Property, Integer>> buildingsMaterialsNeedToBuild = new HashMap<>() {{
        put("Drawbridge", new HashMap<>() {{
            put(Property.WOOD, 10);
        }});
        put("Hovel", new HashMap<>() {{
            put(Property.WOOD, 20);
        }});
        put("Caged War Dogs", new HashMap<>() {{
            put(Property.WOOD, 10);
        }});
        put("Siege Tent", new HashMap<>() {{
            put(Property.WOOD, 10);
        }});
        put("Church", new HashMap<>() {{
            put(Property.WOOD, 50);
        }});
        put("Cathedral", new HashMap<>() {{
            put(Property.WOOD, 100);
        }});
        put("Tall Wall", new HashMap<>() {{
            put(Property.WOOD, 10);
        }});
        put("Short Wall", new HashMap<>() {{
            put(Property.WOOD, 5);
        }});
        put("Stair", new HashMap<>() {{
            put(Property.WOOD, 5);
        }});
        put("Good Things", new HashMap<>() {{
            put(Property.WOOD, 5);
        }});
        put("Bad Things", new HashMap<>() {{
            put(Property.WOOD, 5);
        }});
        put("Shop", new HashMap<>() {{
            put(Property.WOOD, 5);
        }});
        put("Small Stone Gatehouse", new HashMap<>() {{
            put(Property.WOOD, 5);
        }});
        put("Large Stone Gatehouse", new HashMap<>() {{
            put(Property.WOOD, 10);
        }});
        put("Lookout Tower", new HashMap<>() {{
            put(Property.WOOD, 50);
        }});
        put("Perimeter Tower", new HashMap<>() {{
            put(Property.WOOD, 50);
        }});
        put("Defence Turret", new HashMap<>() {{
            put(Property.WOOD, 20);
        }});
        put("Square Tower", new HashMap<>() {{
            put(Property.WOOD, 20);
        }});
        put("Round Tower", new HashMap<>() {{
            put(Property.WOOD, 20);
        }});
        put("Stable", new HashMap<>() {{
            put(Property.WOOD, 20);
        }});
        put("Mill", new HashMap<>() {{
            put(Property.WOOD, 20);
        }});
        put("Iron Mine", new HashMap<>() {{
            put(Property.WOOD, 20);
        }});
        put("Pitch Rig", new HashMap<>() {{
            put(Property.WOOD, 20);
        }});
        put("Quarry", new HashMap<>() {{
            put(Property.WOOD, 20);
        }});
        put("Woodcutter", new HashMap<>() {{
            put(Property.WOOD, 5);
        }});
        put("Oil Smelter", new HashMap<>() {{
            put(Property.WOOD, 20);
        }});
        put("Apple Orchard", new HashMap<>() {{
            put(Property.WOOD, 10);
        }});
        put("Diary Farmer", new HashMap<>() {{
            put(Property.WOOD, 10);
        }});
        put("Hops Farmer", new HashMap<>() {{
            put(Property.WOOD, 10);
        }});
        put("Hunter Post", new HashMap<>() {{
            put(Property.WOOD, 10);
        }});
        put("Wheat Farmer", new HashMap<>() {{
            put(Property.WOOD, 10);
        }});
        put("Bakery", new HashMap<>() {{
            put(Property.WOOD, 30);
        }});
        put("Brewer", new HashMap<>() {{
            put(Property.WOOD, 30);
        }});
        put("Armourer", new HashMap<>() {{
            put(Property.WOOD, 40);
        }});
        put("Blacksmith", new HashMap<>() {{
            put(Property.WOOD, 40);
        }});
        put("Fletcher", new HashMap<>() {{
            put(Property.WOOD, 40);
        }});
        put("Pole Turner", new HashMap<>() {{
            put(Property.WOOD, 40);
        }});
        put("Inn", new HashMap<>() {{
            put(Property.WOOD, 30);
        }});
        put("Barrack", new HashMap<>() {{
            put(Property.WOOD, 40);
        }});
        put("Killing Pit", new HashMap<>() {{
            put(Property.WOOD, 10);
        }});
        put("Pitch Ditch", new HashMap<>() {{
            put(Property.WOOD, 10);
        }});
        put("Mercenary Post", new HashMap<>() {{
            put(Property.WOOD, 10);
        }});
        put("Engineer Guild", new HashMap<>() {{
            put(Property.WOOD, 30);
        }});
        put("Armoury", new HashMap<>() {{
            put(Property.WOOD, 50);
        }});
        put("Stockpile", new HashMap<>() {{
            put(Property.WOOD, 10);
        }});
        put("Granary", new HashMap<>() {{
            put(Property.WOOD, 10);
        }});
    }};
    private static final ArrayList<String> validBuildingsToPass = new ArrayList<>(Arrays.asList("Short Wall"));
    private static final HashMap<String, ImagePattern> imagePatterns = new HashMap<>() {{
        put("Base", new ImagePattern(new Image(Objects.requireNonNull(getClass().getResource("/buildings/Normal/Base.png")).toExternalForm())));
        put("Drawbridge", new ImagePattern(new Image(Objects.requireNonNull(getClass().getResource("/buildings/Normal/Drawbridge.png")).toExternalForm())));
        put("Hovel", new ImagePattern(new Image(Objects.requireNonNull(getClass().getResource("/buildings/Normal/Hovel.png")).toExternalForm())));
//        put("Caged War Dogs", new ImagePattern(new Image(Objects.requireNonNull(getClass().getResource("/buildings/Normal/Caged War Dogs.png")).toExternalForm())));
//        put("Siege Tent", new ImagePattern(new Image(Objects.requireNonNull(getClass().getResource("/buildings/Normal/Siege Tent.png")).toExternalForm())));
        put("Church", new ImagePattern(new Image(Objects.requireNonNull(getClass().getResource("/buildings/Normal/Church.png")).toExternalForm())));
        put("Cathedral", new ImagePattern(new Image(Objects.requireNonNull(getClass().getResource("/buildings/Normal/Cathedral.png")).toExternalForm())));
        put("Tall Wall", new ImagePattern(new Image(Objects.requireNonNull(getClass().getResource("/buildings/Normal/Tall Wall.png")).toExternalForm())));
        put("Short Wall", new ImagePattern(new Image(Objects.requireNonNull(getClass().getResource("/buildings/Normal/Short Wall.png")).toExternalForm())));
//        put("Stair", new ImagePattern(new Image(Objects.requireNonNull(getClass().getResource("/buildings/Normal/Stair.png")).toExternalForm())));
//        put("Good Things", new ImagePattern(new Image(Objects.requireNonNull(getClass().getResource("/buildings/Normal/Good Stuff.png")).toExternalForm())));
//        put("Bad Things", new ImagePattern(new Image(Objects.requireNonNull(getClass().getResource("/buildings/Normal/Bad Stuff.png")).toExternalForm())));
        put("Shop", new ImagePattern(new Image(Objects.requireNonNull(getClass().getResource("/buildings/Normal/Shop.png")).toExternalForm())));
        put("Small Stone Gatehouse", new ImagePattern(new Image(Objects.requireNonNull(getClass().getResource("/buildings/GateHouse/Small Stone Gatehouse.png")).toExternalForm())));
        put("Large Stone Gatehouse", new ImagePattern(new Image(Objects.requireNonNull(getClass().getResource("/buildings/GateHouse/Large Stone Gatehouse.png")).toExternalForm())));
        put("Lookout Tower", new ImagePattern(new Image(Objects.requireNonNull(getClass().getResource("/buildings/Tower/Lookout Tower.png")).toExternalForm())));
        put("Perimeter Tower", new ImagePattern(new Image(Objects.requireNonNull(getClass().getResource("/buildings/Tower/Perimeter Tower.png")).toExternalForm())));
        put("Defence Turret", new ImagePattern(new Image(Objects.requireNonNull(getClass().getResource("/buildings/Tower/Defence Turret.png")).toExternalForm())));
        put("Square Tower", new ImagePattern(new Image(Objects.requireNonNull(getClass().getResource("/buildings/Tower/Square Tower.png")).toExternalForm())));
        put("Round Tower", new ImagePattern(new Image(Objects.requireNonNull(getClass().getResource("/buildings/Tower/Round Tower.png")).toExternalForm())));
        put("Stable", new ImagePattern(new Image(Objects.requireNonNull(getClass().getResource("/buildings/IndustrialCenter/Stable.png")).toExternalForm())));
        put("Mill", new ImagePattern(new Image(Objects.requireNonNull(getClass().getResource("/buildings/IndustrialCenter/Mill.png")).toExternalForm())));
        put("Iron Mine", new ImagePattern(new Image(Objects.requireNonNull(getClass().getResource("/buildings/IndustrialCenter/Iron Mine.png")).toExternalForm())));
        put("Pitch Rig", new ImagePattern(new Image(Objects.requireNonNull(getClass().getResource("/buildings/IndustrialCenter/Pitch Rig.png")).toExternalForm())));
        put("Quarry", new ImagePattern(new Image(Objects.requireNonNull(getClass().getResource("/buildings/IndustrialCenter/Quarry.png")).toExternalForm())));
        put("Woodcutter", new ImagePattern(new Image(Objects.requireNonNull(getClass().getResource("/buildings/IndustrialCenter/Woodcutter.png")).toExternalForm())));
        put("Oil Smelter", new ImagePattern(new Image(Objects.requireNonNull(getClass().getResource("/buildings/IndustrialCenter/Oil Smelter.png")).toExternalForm())));
        put("Apple Orchard", new ImagePattern(new Image(Objects.requireNonNull(getClass().getResource("/buildings/IndustrialCenter/Apple Orchard.png")).toExternalForm())));
        put("Diary Farmer", new ImagePattern(new Image(Objects.requireNonNull(getClass().getResource("/buildings/IndustrialCenter/Diary Farmer.png")).toExternalForm())));
        put("Hops Farmer", new ImagePattern(new Image(Objects.requireNonNull(getClass().getResource("/buildings/IndustrialCenter/Hops Farmer.png")).toExternalForm())));
        put("Hunter Post", new ImagePattern(new Image(Objects.requireNonNull(getClass().getResource("/buildings/IndustrialCenter/Hunter Post.png")).toExternalForm())));
        put("Wheat Farmer", new ImagePattern(new Image(Objects.requireNonNull(getClass().getResource("/buildings/IndustrialCenter/Wheat Farmer.png")).toExternalForm())));
        put("Bakery", new ImagePattern(new Image(Objects.requireNonNull(getClass().getResource("/buildings/IndustrialCenter/Bakery.png")).toExternalForm())));
        put("Brewer", new ImagePattern(new Image(Objects.requireNonNull(getClass().getResource("/buildings/IndustrialCenter/Brewer.png")).toExternalForm())));
        put("Armourer", new ImagePattern(new Image(Objects.requireNonNull(getClass().getResource("/buildings/IndustrialCenter/Armourer.png")).toExternalForm())));
        put("Blacksmith", new ImagePattern(new Image(Objects.requireNonNull(getClass().getResource("/buildings/IndustrialCenter/Blacksmith.png")).toExternalForm())));
        put("Fletcher", new ImagePattern(new Image(Objects.requireNonNull(getClass().getResource("/buildings/IndustrialCenter/Fletcher.png")).toExternalForm())));
        put("Pole Turner", new ImagePattern(new Image(Objects.requireNonNull(getClass().getResource("/buildings/IndustrialCenter/Pole Turner.png")).toExternalForm())));
        put("Inn", new ImagePattern(new Image(Objects.requireNonNull(getClass().getResource("/buildings/Inn/Inn.png")).toExternalForm())));
        put("Barrack", new ImagePattern(new Image(Objects.requireNonNull(getClass().getResource("/buildings/RecruitmentCenter/Barrack.png")).toExternalForm())));
        put("Mercenary Post", new ImagePattern(new Image(Objects.requireNonNull(getClass().getResource("/buildings/RecruitmentCenter/Mercenary Post.png")).toExternalForm())));
        put("Engineer Guild", new ImagePattern(new Image(Objects.requireNonNull(getClass().getResource("/buildings/RecruitmentCenter/Engineer Guild.png")).toExternalForm())));
//        put("Killing Pit", new ImagePattern(new Image(Objects.requireNonNull(getClass().getResource("/buildings/Trap/Killing Pit.png")).toExternalForm())));
        put("Pitch Ditch", new ImagePattern(new Image(Objects.requireNonNull(getClass().getResource("/buildings/Trap/Pitch Ditch.png")).toExternalForm())));
        put("Armoury", new ImagePattern(new Image(Objects.requireNonNull(getClass().getResource("/buildings/Storage/Armoury.png")).toExternalForm())));
        put("Stockpile", new ImagePattern(new Image(Objects.requireNonNull(getClass().getResource("/buildings/Storage/Stockpile.png")).toExternalForm())));
        put("Granary", new ImagePattern(new Image(Objects.requireNonNull(getClass().getResource("/buildings/Storage/Granary.png")).toExternalForm())));
    }};

    private final ArrayList<String> buildings;

    private BuildingType(ArrayList<String> buildings) {
        this.buildings = buildings;
    }

    public static BuildingType getBuildingType(String type) {
        System.out.println(type);
        for (BuildingType buildingType : buildingTypes) {
            if (buildingType.buildings.contains(type)) return buildingType;
        }
        return null;
    }

    public static int getHP(String type) {
        return buildingsHp.get(type);
    }

    public static ArrayList<LandType> getValidLandTypes(String type) {
        return ValidLandTypes.get(type);
    }

    public static HashMap<Property, Integer> getMaterialsNeedToBuild(String type) {
        return buildingsMaterialsNeedToBuild.get(type);
    }

    public static boolean isCastle(String type) {
        return castleTypes.contains(type);
    }

    public static boolean isGateHouse(String type) {
        return GATEHOUSE.buildings.contains(type);
    }

    public static boolean isBuildingValidToPass(Building building) {
        return validBuildingsToPass.contains(building.getType());
    }

    public static ImagePattern getImagePattern(String type) {
        return imagePatterns.get(type);
    }

    public static String getTypeByImagePattern(ImagePattern imagePattern) {
        for (String type : imagePatterns.keySet()) {
            if (imagePatterns.get(type).equals(imagePattern)) return type;
        }
        return null;
    }
}