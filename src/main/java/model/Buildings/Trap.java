package model.Buildings;

import model.Match.Direction;
import model.Match.Governance;
import model.User;

public class Trap extends Building {
    private boolean isActive;

    public Trap(Governance governance, int column, int row, String type, BuildingType buildingType, Direction direction) {
        super(governance, column, row, type, buildingType, direction);
        isActive = type.equals("Killing Pit");
    }

    public void activate() {
        isActive = true;
    }
}