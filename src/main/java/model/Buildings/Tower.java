package model.Buildings;

import model.Match.Direction;
import model.Match.Governance;
import model.User;

public class Tower extends Building {
    private final int fireRange;
    private final int defendRange;
    private final int capacity;

    public Tower(Governance governance, int column, int row, String type, BuildingType buildingType, Direction direction) {
        super(governance, column, row, type, buildingType, direction);
        // TODO
    }

    public int getFireRange() {
        return fireRange;
    }

    public int getDefendRange() {
        return defendRange;
    }

    public int getCapacity() {
        return capacity;
    }
}
