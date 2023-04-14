package model;

import java.util.ArrayList;

public class Unit {
    static enum LandType {}
    private LandType landType;
    private ArrayList<Component> components;

    public Unit() {}

    public LandType getLandType() {
        return landType;
    }

    public boolean isUnitEmpty() {}
}