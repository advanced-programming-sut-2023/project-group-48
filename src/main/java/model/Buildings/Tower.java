package model.Buildings;

import model.Match.Direction;
import model.User;

public class Tower extends Building {
    private final int fireRange;
    private final int defendRange;
    private final int capacity;

    public Tower(User owner, int column, int row, String type, BuildingType buildingType, int hp, Direction direction) {
        super(owner, column, row, type, buildingType, hp, direction);
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
