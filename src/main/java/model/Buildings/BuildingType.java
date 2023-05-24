package model.Buildings;

import model.Match.LandType;
import model.Match.Property;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public enum BuildingType {
    NORMAL(new ArrayList<>(Arrays.asList("Drawbridge", "Hovel", "Caged War Dogs", "Siege Tent", "Church", "Cathedral",
            "Tall Wall", "Short Wall", "Stair", "Good Things", "Bad Things", "Shop"))), // 12
    GATEHOUSE(new ArrayList<>(Arrays.asList("Small Stone Gatehouse", "Large Stone Gatehouse"))), // 2
    TOWER(new ArrayList<>(Arrays.asList("Lookout Tower", "Perimeter Tower", "Defence Turret", "Square Tower", "Round Tower"))), // 5
    INDUSTRIAL_CENTER(new ArrayList<>(Arrays.asList("Stable", "Mill", "Iron Mine", "Pitch Rig", "ًQuarry", "Woodcutter",
            "Oil Smelter", "Apple Orchard", "Diary Farmer", "Hops Farmer", "Hunter Post", "Wheat Farmer", "Bakery", "Brewer",
            "Armourer", "Blacksmith", "Fletcher", "Pole Turner"))), // 18
    INN(new ArrayList<>(List.of("Inn"))), // 1
    TRAP(new ArrayList<>(Arrays.asList("Killing Pit", "Pitch Ditch", "Tunnel"))), // 3
    RECRUITMENT_CENTER(new ArrayList<>(Arrays.asList("Barrack", "Mercenary Post", "Engineer Guild"))), // 3
    STORAGE(new ArrayList<>(Arrays.asList("Armoury", "Stockpile", "Granary"))), // 3
    ENVIRONMENT(new ArrayList<>(Arrays.asList("Desert Shrub", "Cherry Palm", "olive Tree", "Coconut Palm", "Date", "Tree", "Rock"))),
    BASE(new ArrayList<>(Arrays.asList("Base"))); // 7

    private static final BuildingType[] buildingTypes = {NORMAL, GATEHOUSE, TOWER, INDUSTRIAL_CENTER, INN, TRAP, RECRUITMENT_CENTER, STORAGE, ENVIRONMENT};
    private static final ArrayList<String> castleTypes = new ArrayList<>(Arrays.asList("Small Stone Gatehouse", "Large Stone Gatehouse",
            "Drawbridge", "Lookout Tower", "Perimeter Tower", "Defence Turret", "Square Tower", "Round Tower", "Armoury",
            "Barrack", "Mercenary Post", "Engineer Guild", "Killing Pit", "Oil Smelter", "Pitch Ditch", "Caged War Dogs", "Siege Tent", "Stable"));
    private static final HashMap<String, Integer> buildingsHp = new HashMap<>() {{
        put("Small Stone Gatehouse", 300);
        put("Large Stone Gatehouse", 400);
        put("Drawbridge", 300);
        put("Hovel", 400);
        put("Caged War Dogs", 300);
        put("Siege Tent", 300);
        put("Church", 500);
        put("Cathedral", 700);
        put("Tall Wall", 300);
        put("Short Wall", 200);
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
        put("Shop", 300);
        put("Good Things", 100);
        put("Bad Things", 100);
        //TODO: soldiers should not hurt all buildings(like trees and rocks)
        //TODO: adding shop and its effect
    }};
    private static final HashMap<String, ArrayList<LandType>> ValidLandTypes = new HashMap<String, ArrayList<LandType>>() {{
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
        put("Barrack", new ArrayList<LandType>(Arrays.asList(LandType.LAND)));
        put("Pitch Ditch", new ArrayList<LandType>(Arrays.asList(LandType.LAND)));
        put("Mercenary Post", new ArrayList<LandType>(Arrays.asList(LandType.LAND)));
        put("Engineer Guild", new ArrayList<LandType>(Arrays.asList(LandType.LAND)));
        put("Armoury", new ArrayList<LandType>(Arrays.asList(LandType.LAND)));
        put("Stockpile", new ArrayList<LandType>(Arrays.asList(LandType.LAND)));
        put("Granary", new ArrayList<LandType>(Arrays.asList(LandType.LAND)));
    }};
    private static final HashMap<String, HashMap<Property, Integer>> buildingsMaterialsNeedToBuild = new HashMap<String, HashMap<Property, Integer>>() {{
        put("Small Stone Gatehouse", new HashMap<>() {{
            put(Property.WOOD, 5);
        }});
        put("Large Stone Gatehouse", new HashMap<>() {{
            put(Property.WOOD, 10);
        }});
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
}