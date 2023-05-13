package model.Buildings;

import model.Match.LandType;
import model.Match.Property;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public enum BuildingType {
    NORMAL(new ArrayList<>(Arrays.asList("Drawbridge", "Hovel", "Caged War Dogs", "Siege Tent", "Church", "Cathedral",
            "Tall Wall", "Short Wall", "Stair","Good Things","Bad Things"))),
    GATEHOUSE(new ArrayList<>(Arrays.asList("Small Stone Gatehouse", "Large Stone Gatehouse"))),
    TOWER(new ArrayList<>(Arrays.asList("Lookout Tower", "Perimeter Tower", "Defence Turret", "Square Tower", "Round Tower"))),
    INDUSTRIAL_CENTER(new ArrayList<>(Arrays.asList("Stable", "Mill", "Iron Mine", "Pitch Rig", "ًQuarry", "Woodcutter",
            "Oil Smelter", "Apple Orchard", "Diary Farmer", "Hops Farmer", "Hunter Post", "Wheat Farmer", "Bakery", "Brewer",
            "Armourer", "Blacksmith", "Fletcher", "Pole Turner"))),
    INN(new ArrayList<>(List.of("Inn"))),
    TRAP(new ArrayList<>(Arrays.asList("Killing Pit", "Pitch Ditch", "Tunnel"))),
    RECRUITMENT_CENTER(new ArrayList<>(Arrays.asList("Barrack", "Mercenary Post", "Engineer Guild"))),
    STORAGE(new ArrayList<>(Arrays.asList("Armoury", "Stockpile", "Granary"))),
    ENVIRONMENT(new ArrayList<>(Arrays.asList("Desert Shrub", "Cherry Palm", "olive Tree", "Coconut Palm", "Date", "Tree", "Rock")));

    private static final BuildingType[] buildingTypes = {NORMAL, GATEHOUSE, TOWER, INDUSTRIAL_CENTER, INN, TRAP, RECRUITMENT_CENTER, STORAGE, ENVIRONMENT};
    private static final ArrayList<String> castleTypes = new ArrayList<>(Arrays.asList("Small Stone Gatehouse", "Large Stone Gatehouse",
            "Drawbridge", "Lookout Tower", "Perimeter Tower", "Defence Turret", "Square Tower", "Round Tower", "Armoury",
            "Barrack", "Mercenary Post", "Engineer Guild", "Killing Pit", "Oil Smelter", "Pitch Ditch", "Caged War Dogs", "Siege Tent", "Stable"));
    private static final HashMap<String, Integer> buildingsHp = new HashMap<>() {{
        put("Small Stone Gatehouse", 100);
        put("Large Stone Gatehouse", 100);
        put("Drawbridge", 100);
        put("Hovel", 100);
        put("Caged War Dogs", 100);
        put("Siege Tent", 100);
        put("Church", 100);
        put("Cathedral", 100);
        put("Tall Wall", 100);
        put("Short Wall", 100);
        put("Lookout Tower", 100);
        put("Perimeter Tower", 100);
        put("Defence Turret", 100);
        put("Square Tower", 100);
        put("Round Tower", 100);
        put("Stable", 100);
        put("Mill", 100);
        put("Iron Mine", 100);
        put("Pitch Rig", 100);
        put("Quarry", 100);
        put("Woodcutter", 100);
        put("Oil Smelter", 100);
        put("Apple Orchard", 100);
        put("Diary Farmer", 100);
        put("Hops Farmer", 100);
        put("Hunter Post", 100);
        put("Wheat Farmer", 100);
        put("Bakery", 100);
        put("Brewer", 100);
        put("Armourer", 100);
        put("Blacksmith", 100);
        put("Fletcher", 100);
        put("Pole Turner", 100);
        put("Inn", 100);
        put("Killing Pit", 100);
        put("Pitch Ditch", 100);
        put("Mercenary Post", 100);
        put("Engineer Guild", 100);
        put("Armoury", 100);
        put("Stockpile", 100);
        put("Granary", 100);
    }};
    private static final HashMap<String, ArrayList<LandType>> buildingsValidLandTypes = new HashMap<String, ArrayList<LandType>>() {{
        put("Small Stone Gatehouse", new ArrayList<LandType>(Arrays.asList(LandType.LAND)));
        put("Large Stone Gatehouse", new ArrayList<LandType>(Arrays.asList(LandType.LAND)));
        put("Drawbridge", new ArrayList<LandType>(Arrays.asList(LandType.LAND)));
        put("Hovel", new ArrayList<LandType>(Arrays.asList(LandType.LAND)));
        put("Caged War Dogs", new ArrayList<LandType>(Arrays.asList(LandType.LAND)));
        put("Siege Tent", new ArrayList<LandType>(Arrays.asList(LandType.LAND)));
        put("Church", new ArrayList<LandType>(Arrays.asList(LandType.LAND)));
        put("Cathedral", new ArrayList<LandType>(Arrays.asList(LandType.LAND)));
        put("Tall Wall", new ArrayList<LandType>(Arrays.asList(LandType.LAND)));
        put("Short Wall", new ArrayList<LandType>(Arrays.asList(LandType.LAND)));
        put("Lookout Tower", new ArrayList<LandType>(Arrays.asList(LandType.LAND)));
        put("Perimeter Tower", new ArrayList<LandType>(Arrays.asList(LandType.LAND)));
        put("Defence Turret", new ArrayList<LandType>(Arrays.asList(LandType.LAND)));
        put("Square Tower", new ArrayList<LandType>(Arrays.asList(LandType.LAND)));
        put("Round Tower", new ArrayList<LandType>(Arrays.asList(LandType.LAND)));
        put("Stable", new ArrayList<LandType>(Arrays.asList(LandType.LAND)));
        put("Mill", new ArrayList<LandType>(Arrays.asList(LandType.LAND)));
        put("Iron Mine", new ArrayList<LandType>(Arrays.asList(LandType.LAND)));
        put("Pitch Rig", new ArrayList<LandType>(Arrays.asList(LandType.LAND)));
        put("Quarry", new ArrayList<LandType>(Arrays.asList(LandType.LAND)));
        put("Woodcutter", new ArrayList<LandType>(Arrays.asList(LandType.LAND)));
        put("Oil Smelter", new ArrayList<LandType>(Arrays.asList(LandType.LAND)));
        put("Apple Orchard", new ArrayList<LandType>(Arrays.asList(LandType.LAND)));
        put("Diary Farmer", new ArrayList<LandType>(Arrays.asList(LandType.LAND)));
        put("Hops Farmer", new ArrayList<LandType>(Arrays.asList(LandType.LAND)));
        put("Hunter Post", new ArrayList<LandType>(Arrays.asList(LandType.LAND)));
        put("Wheat Farmer", new ArrayList<LandType>(Arrays.asList(LandType.LAND)));
        put("Bakery", new ArrayList<LandType>(Arrays.asList(LandType.LAND)));
        put("Brewer", new ArrayList<LandType>(Arrays.asList(LandType.LAND)));
        put("Armourer", new ArrayList<LandType>(Arrays.asList(LandType.LAND)));
        put("Blacksmith", new ArrayList<LandType>(Arrays.asList(LandType.LAND)));
        put("Fletcher", new ArrayList<LandType>(Arrays.asList(LandType.LAND)));
        put("Pole Turner", new ArrayList<LandType>(Arrays.asList(LandType.LAND)));
        put("Inn", new ArrayList<LandType>(Arrays.asList(LandType.LAND)));
        put("Killing Pit", new ArrayList<LandType>(Arrays.asList(LandType.LAND)));
        put("Pitch Ditch", new ArrayList<LandType>(Arrays.asList(LandType.LAND)));
        put("Mercenary Post", new ArrayList<LandType>(Arrays.asList(LandType.LAND)));
        put("Engineer Guild", new ArrayList<LandType>(Arrays.asList(LandType.LAND)));
        put("Armoury", new ArrayList<LandType>(Arrays.asList(LandType.LAND)));
        put("Stockpile", new ArrayList<LandType>(Arrays.asList(LandType.LAND)));
        put("Granary", new ArrayList<LandType>(Arrays.asList(LandType.LAND)));
    }};
    private static final HashMap<String, HashMap<Property, Integer>> buildingsMaterialsNeedToBuild = new HashMap<String, HashMap<Property, Integer>>() {{
        put("Small Stone Gatehouse", new HashMap<>() {{
            put(Property.WOOD, 10);
        }});
        put("Large Stone Gatehouse", new HashMap<>() {{
            put(Property.WOOD, 10);
        }});
        put("Drawbridge", new HashMap<>() {{
            put(Property.WOOD, 10);
        }});
        put("Hovel", new HashMap<>() {{
            put(Property.WOOD, 10);
        }});
        put("Caged War Dogs", new HashMap<>() {{
            put(Property.WOOD, 10);
        }});
        put("Siege Tent", new HashMap<>() {{
            put(Property.WOOD, 10);
        }});
        put("Church", new HashMap<>() {{
            put(Property.WOOD, 10);
        }});
        put("Cathedral", new HashMap<>() {{
            put(Property.WOOD, 10);
        }});
        put("Tall Wall", new HashMap<>() {{
            put(Property.WOOD, 10);
        }});
        put("Short Wall", new HashMap<>() {{
            put(Property.WOOD, 10);
        }});
        put("Lookout Tower", new HashMap<>() {{
            put(Property.WOOD, 10);
        }});
        put("Perimeter Tower", new HashMap<>() {{
            put(Property.WOOD, 10);
        }});
        put("Defence Turret", new HashMap<>() {{
            put(Property.WOOD, 10);
        }});
        put("Square Tower", new HashMap<>() {{
            put(Property.WOOD, 10);
        }});
        put("Round Tower", new HashMap<>() {{
            put(Property.WOOD, 10);
        }});
        put("Stable", new HashMap<>() {{
            put(Property.WOOD, 10);
        }});
        put("Mill", new HashMap<>() {{
            put(Property.WOOD, 10);
        }});
        put("Iron Mine", new HashMap<>() {{
            put(Property.WOOD, 10);
        }});
        put("Pitch Rig", new HashMap<>() {{
            put(Property.WOOD, 10);
        }});
        put("Quarry", new HashMap<>() {{
            put(Property.WOOD, 10);
        }});
        put("Woodcutter", new HashMap<>() {{
            put(Property.WOOD, 10);
        }});
        put("Oil Smelter", new HashMap<>() {{
            put(Property.WOOD, 10);
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
            put(Property.WOOD, 10);
        }});
        put("Brewer", new HashMap<>() {{
            put(Property.WOOD, 10);
        }});
        put("Armourer", new HashMap<>() {{
            put(Property.WOOD, 10);
        }});
        put("Blacksmith", new HashMap<>() {{
            put(Property.WOOD, 10);
        }});
        put("Fletcher", new HashMap<>() {{
            put(Property.WOOD, 10);
        }});
        put("Pole Turner", new HashMap<>() {{
            put(Property.WOOD, 10);
        }});
        put("Inn", new HashMap<>() {{
            put(Property.WOOD, 10);
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
            put(Property.WOOD, 10);
        }});
        put("Armoury", new HashMap<>() {{
            put(Property.WOOD, 10);
        }});
        put("Stockpile", new HashMap<>() {{
            put(Property.WOOD, 10);
        }});
        put("Granary", new HashMap<>() {{
            put(Property.WOOD, 10);
        }});
    }};
    private static final ArrayList<String> validBuildingsToPass = new ArrayList<>(Arrays.asList("Short Wall"));
    private static final ArrayList<String> towerRelated = new ArrayList<>(Arrays.asList("Tall Wall", "Short Wall", "Stair", "Lookout Tower", "Perimeter Tower", "Defence Turret", "Square Tower", "Round Tower"));

    private final ArrayList<String> buildings;

    private BuildingType(ArrayList<String> buildings) {
        this.buildings = buildings;
    }

    public static BuildingType getBuildingType(String type) {
        for (BuildingType buildingType : buildingTypes) {
            if (buildingType.buildings.contains(type)) return buildingType;
        }
        return null;
    }

    public static int getHP(String type) {
        return buildingsHp.get(type);
    }

    public static ArrayList<LandType> getValidLandTypes(String type) {
        return buildingsValidLandTypes.get(type);
    }

    public static HashMap<Property, Integer> getMaterialsNeedToBuild(String type) {
        return buildingsMaterialsNeedToBuild.get(type);
    }

    public static boolean isCastle(String type) {
        return castleTypes.contains(type);
    }

    public static boolean isTowerRelated(String type) {
        return towerRelated.contains(type);
    }

    public static boolean isGateHouse(String type) {
        return GATEHOUSE.buildings.contains(type);
    }

    public static boolean isBuildingValidToPass(Building building) {
        return validBuildingsToPass.contains(building.getType());
    }
}