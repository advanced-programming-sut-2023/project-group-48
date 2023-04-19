package model.Match;

import java.util.ArrayList;

public class Unit {
    private final int Column;
    private final int row;
    private LandType landType;
    private ArrayList<Component> components;

    public Unit(int column, int row) {
        Column = column;
        this.row = row;
    }

    public LandType getLandType() {
        return landType;
    }

    public void setLandType(LandType landType) {
        this.landType = landType;
    }

    public boolean isUnitEmpty() {}

    public int getColumn() {
        return Column;
    }

    public int getRow() {
        return row;
    }
}