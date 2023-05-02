package model.Match;

import java.util.ArrayList;

public class Cell {
    private final int column;
    private final int row;
    private LandType landType;
    private ArrayList<Component> components;

    public Cell(int column, int row) {
        this.column = column;
        this.row = row;
    }

    public LandType getLandType() {
        return landType;
    }

    public void setLandType(LandType landType) {
        this.landType = landType;
    }

    public boolean isCellEmpty() {}

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }
}