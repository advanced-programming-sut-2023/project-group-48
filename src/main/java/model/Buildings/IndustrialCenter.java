package model.Buildings;

import model.Match.Direction;
import model.Match.Property;
import model.User;

public class IndustrialCenter extends Building {
    private final Property property;
    private final int rate;

    public IndustrialCenter(User owner, int column, int row, String type, BuildingType buildingType, int hp, Direction direction) {
        super(owner, column, row, type, buildingType, hp, direction);
        // TODO
    }

    public boolean canProduce() {
        // TODO
    }
    public void produce() {
        // TODO
    }

    public int getRate() {
        return rate;
    }
}
