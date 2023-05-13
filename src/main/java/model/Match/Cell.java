package model.Match;

import model.Buildings.Building;
import model.Buildings.BuildingType;
import model.People.People;
import model.People.Troop;

import java.util.ArrayList;
import java.util.HashMap;

public class Cell {
    private static ArrayList<Cell[][]> defaultMaps;
    private final int row;
    private final int column;
    private LandType landType;
    private Building building;
    private ArrayList<People> people;

    private boolean isAGovernmentBase;

    static {
        // TODO: add default maps
    }

    private Cell(int row, int column) {
        this.row = row;
        this.column = column;
        isAGovernmentBase = false;
    }

    public static Cell[][] generateMap(int mapNumber) {
        return defaultMaps.get(mapNumber);
    }

    public LandType getLandType() {
        return landType;
    }

    public void setLandType(LandType landType) {
        this.landType = landType;
    }

    public boolean isAGovernmentBase() {
        return isAGovernmentBase;
    }

    public void setAGovernmentBase(boolean AGovernmentBase) {
        isAGovernmentBase = AGovernmentBase;
    }

    public boolean isCellEmpty() {
        return building == null && people.isEmpty();
    }

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    public ArrayList<People> getPeople() {
        return people;
    }

    public boolean canPeoplePass() {
        return BuildingType.isBuildingValidToPass(building);
    }

    public HashMap<String, Integer> getTroopsPassing() {
        HashMap<String, Integer> troopsPassing = new HashMap<>();
        for (People person : people) {
            if (person instanceof Troop) {
                String type = person.getType();
                if (troopsPassing.containsKey(type)) troopsPassing.put(type, troopsPassing.get(type) + 1);
                else troopsPassing.put(type, 1);
            }
        }
        return troopsPassing;
    }

    private boolean isTroopGoingThrough() {
        for (People person : people) {
            if (person instanceof Troop) return true;
        }
        return false;
    }

    public char getSymbol() {
        if (isTroopGoingThrough()) return 'S';
        if (building != null) {
            if (BuildingType.isTowerRelated(building.getType())) return 'W';
            if (building.getType().equals("Tree")) return 'T';
            return 'B';
        }
        return '#';
    }

    public void clearCell() {
        building = null;
        people.clear();
    }

    public void addPeople(People people) {
        this.people.add(people);
    }
}