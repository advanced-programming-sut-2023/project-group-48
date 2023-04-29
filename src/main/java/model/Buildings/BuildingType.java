package model.Buildings;

import model.Match.LandType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public enum BuildingType {
    NORMAL(new ArrayList<>(Arrays.asList("Small Stone Gatehouse", "Large Stone Gatehouse", "Drawbridge", "Hovel", "Caged War Dogs", "Siege Tent", "Church", "Cathedral"))) ,
    TOWER(new ArrayList<>(Arrays.asList("Lookout Tower" , "Perimeter Tower", "Defence Turret", "Square Tower" , "Round Tower"))) ,
    INDUSTRIAL_CENTER(new ArrayList<>(Arrays.asList("Stable", "Mill", "Iron Mine", "Pitch Rig", "ًQuarry", "Woodcutter", "Oil Smelter", "Apple Orchard", "Diary Farmer", "Hops Farmer", "Hunter Post", "Wheat Farmer", "Bakery", "Brewer", "Armourer", "Blacksmith", "Fletcher", "Pole Turner"))),
    INN(new ArrayList<>(List.of("Inn"))) ,
    TRAP(new ArrayList<>(Arrays.asList("Killing Pit", "Pitch Ditch"))) ,
    RECRUITMENT_CENTER(new ArrayList<>(Arrays.asList("Mercenary Post", "Engineer Guild"))) ,
    STORAGE(new ArrayList<>(Arrays.asList("Armoury", "Stockpile", "Granary"))) ,
    ;

    private static final BuildingType[] buildingTypes = {NORMAL, TOWER, INDUSTRIAL_CENTER, INN, TRAP, RECRUITMENT_CENTER, STORAGE};
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

    public static int getHP(BuildingType buildingType, String type) {
    }

    public ArrayList<LandType> getValidLandTypes() {
    }
}