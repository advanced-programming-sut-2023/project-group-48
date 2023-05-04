package model.Match;

import model.Buildings.Building;
import model.Buildings.BuildingType;
import model.People.People;

import java.util.ArrayList;

public class Cell {
    private static ArrayList<Cell[][]> defaultMaps;
    private final int column;
    private final int row;
    private LandType landType;
    private Building building;
    private ArrayList<People> people;
    private boolean isAGovernmentBase;

    static {
        // TODO: add default maps
    }

    private Cell(int column, int row) {
        this.column = column;
        this.row = row;
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
    public boolean canPeopleGoThrough() {
        return BuildingType.isBuildingValidToGoThrough(building);
    }
}