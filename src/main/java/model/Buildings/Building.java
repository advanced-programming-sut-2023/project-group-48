package model.Buildings;

import model.Match.Direction;
import model.Match.LandType;
import model.Match.Resource;
import model.User;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class Building {
    private final User owner;
    private final int column;
    private final int row;
    private final String type;
    private final BuildingType buildingType;
    private int hp;
    private final Direction direction;

    public Building(User owner, int column, int row, String type, BuildingType buildingType, int hp, Direction direction) {
        this.owner = owner;
        this.column = column;
        this.row = row;
        this.type = type;
        this.buildingType = buildingType;
        this.hp = hp;
        this.direction = direction;
    }

    public static boolean isLandTypeValidForBuilding(Building building, LandType landType) {
        return building.buildingType.getValidLandTypes().contains(landType);
    }

    public static boolean isBuildingPlaceValid(int column, int row, String type) {
        // TODO
    }

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }

    public String getType() {
        return type;
    }

    public BuildingType getBuildingType() {
        return buildingType;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public boolean isEnemyNearby() {
        // TODO
    }

    public void repair() {
        // TODO
    }

    public Direction getDirection() {
        return direction;
    }

    public User getOwner() {
        return owner;
    }
}