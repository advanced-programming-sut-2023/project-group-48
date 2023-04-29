package model.Buildings;

import model.Match.Direction;
import model.Match.Governance;
import model.Match.Property;
import model.User;

public class IndustrialCenter extends Building {
    private final Property usedProperty;
    private final Property producedProperty;
    private final int rate;

    public IndustrialCenter(Governance governance, int column, int row, String type, BuildingType buildingType, Direction direction) {
        super(governance, column, row, type, buildingType, direction);
    }

    public boolean canProduce() {
    }

    public void produce() {
        this.getGovernance().reduceProperty(usedProperty, rate);
        this.getGovernance().addProperty(producedProperty, rate);
    }

    public int getRate() {
        return rate;
    }
}