package model.Buildings;

import model.Match.Direction;
import model.Match.Governance;
import model.User;

public class Trap extends Building {
    private final int damage;

    public Trap(Governance governance, int column, int row, String type, BuildingType buildingType, Direction direction, int damage) {
        super(governance, column, row, type, buildingType, direction);
        this.damage = damage;
    }

    public int getDamage() {
        return damage;
    }
}