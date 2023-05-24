package model.Buildings;

import model.Match.Direction;
import model.Match.Governance;
import model.User;

public class Trap extends Building {
    private boolean isActive;
    private boolean hasOil;

    public Trap(Governance governance, int row, int column, String type, BuildingType buildingType, Direction direction) {
        super(governance, row, column, type, buildingType, direction);
        isActive = type.equals("Killing Pit");
    }

    public boolean hasOil() {
        return hasOil;
    }

    public void setHasOil(boolean hasOil) {
        this.hasOil = hasOil;
    }

    public boolean isActive() {
        return isActive;
    }

    public void activate() {
        isActive = true;
    }
}