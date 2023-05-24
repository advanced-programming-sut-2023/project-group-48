package model.Match;

import model.Buildings.Building;
import model.Buildings.BuildingType;
import model.Buildings.Tower;
import model.People.People;
import model.People.Troop;

import java.util.ArrayList;
import java.util.HashMap;

public class Cell {
    private static final ArrayList<Cell[][]> defaultMaps;
    private final int row;
    private final int column;
    private LandType landType;
    private Building building;
    private final ArrayList<People> people;
    private boolean isAGovernmentBase;
    private boolean hasTunnel;

    static {
        // TODO: 2. add default maps
        defaultMaps = new ArrayList<>();
        defaultMaps.add(new Cell[200][200]);
        for (int i = 0; i < 200; i++) {
            for (int j = 0; j < 200; j++) {
                defaultMaps.get(0)[i][j] = new Cell(i + 1, j + 1, LandType.LAND);
            }
        }
    }

    private Cell(int row, int column, LandType landType) {
        this.row = row;
        this.column = column;
        this.landType = landType;
        this.building = null;
        this.people = new ArrayList<>();
        this.isAGovernmentBase = false;
        this.hasTunnel = false;
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
        if (building == null) return true;
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
            if (Tower.isTowerRelated(building.getType())) return 'W';
            if (building.getType().equals("Tree")) return 'T';
            return 'B';
        }
        return '#';
    }

    public void clearCell() {
        building = null;
        people.clear();
        hasTunnel = false;
    }

    public void addPeople(People people) {
        this.people.add(people);
    }

    public void removePeople(People people) {
        this.people.remove(people);
    }

    public boolean hasTunnel() {
        return hasTunnel;
    }

    public void setHasTunnel(boolean hasTunnel) {
        this.hasTunnel = hasTunnel;
    }
}