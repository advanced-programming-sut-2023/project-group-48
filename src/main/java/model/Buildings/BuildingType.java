package model.Buildings;

import model.Match.LandType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum BuildingType {
    NORMAL(new ArrayList<>(Arrays.asList("Small Stone Gatehouse", "Large Stone Gatehouse", "Drawbridge", "Hovel",
            "Caged War Dogs", "Siege Tent", "Church", "Cathedral"))),
    TOWER(new ArrayList<>(Arrays.asList("Lookout Tower", "Perimeter Tower", "Defence Turret", "Square Tower", "Round Tower"))),
    INDUSTRIAL_CENTER(new ArrayList<>(Arrays.asList("Stable", "Mill", "Iron Mine", "Pitch Rig", "ًQuarry", "Woodcutter",
            "Oil Smelter", "Apple Orchard", "Diary Farmer", "Hops Farmer", "Hunter Post", "Wheat Farmer", "Bakery", "Brewer",
            "Armourer", "Blacksmith", "Fletcher", "Pole Turner"))),
    INN(new ArrayList<>(List.of("Inn"))),
    TRAP(new ArrayList<>(Arrays.asList("Killing Pit", "Pitch Ditch"))),
    RECRUITMENT_CENTER(new ArrayList<>(Arrays.asList("Barrack", "Mercenary Post", "Engineer Guild"))),
    STORAGE(new ArrayList<>(Arrays.asList("Armoury", "Stockpile", "Granary")));

    private static final BuildingType[] buildingTypes = {NORMAL, TOWER, INDUSTRIAL_CENTER, INN, TRAP, RECRUITMENT_CENTER, STORAGE};
    private static final ArrayList<String> castleTypes = new ArrayList<>(Arrays.asList("Small Stone Gatehouse", "Large Stone Gatehouse",
            "Drawbridge", "Lookout Tower", "Perimeter Tower", "Defence Turret", "Square Tower", "Round Tower", "Armoury",
            "Barrack", "Mercenary Post", "Engineer Guild", "Killing Pit", "Oil Smelter", "Pitch Ditch", "Caged War Dogs", "Siege Tent", "Stable"));
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
        // TODO
        switch (type) {
            case "Small Stone Gatehouse":
                return 100;
            case "Large Stone Gatehouse":
                return 100;
            case "Drawbridge":
                return 100;
            case "Hovel":
                return 100;
            case "Caged War Dogs":
                return 100;
            case "Siege Tent":
                return 100;
            case "Church":
                return 100;
            case "Cathedral":
                return 100;
            case "Lookout Tower":
                return 100;
            case "Perimeter Tower":
                return 100;
            case "Defence Turret":
                return 100;
            case "Square Tower":
                return 100;
            case "Round Tower":
                return 100;
            case "Stable":
                return 100;
            case "Mill":
                return 100;
            case "Iron Mine":
                return 100;
            case "Pitch Rig":
                return 100;
            case "Quarry":
                return 100;
            case "Woodcutter":
                return 100;
            case "Oil Smelter":
                return 100;
            case "Apple Orchard":
                return 100;
            case "Diary Farmer":
                return 100;
            case "Hops Farmer":
                return 100;
            case "Hunter Post":
                return 100;
            case "Wheat Farmer":
                return 100;
            case "Bakery":
                return 100;
            case "Brewer":
                return 100;
            case "Armourer":
                return 100;
            case "Blacksmith":
                return 100;
            case "Fletcher":
                return 100;
            case "Pole Turner":
                return 100;
            case "Inn":
                return 100;
            case "Killing Pit":
                return 100;
            case "Pitch Ditch":
                return 100;
            case "Mercenary Post":
                return 100;
            case "Engineer Guild":
                return 100;
            case "Armoury":
                return 100;
            case "Stockpile":
                return 100;
            case "Granary":
                return 100;
            default:
                return -1;
        }
    }

    public ArrayList<LandType> getValidLandTypes() {
    }

    public boolean isCastle(String type) {
        return castleTypes.contains(type);
    }
}