package model.Buildings;

import model.Match.Resource;
import model.User;

import java.util.HashMap;

public class Building {
    private final User owner;
    private final int column;
    private final int row;
    private HashMap<Resource, Integer> resourcesToBuild;
    private final String type;
    private final BuildingType buildingType;
    private int hp;
    private Direction direction;

    public Building(User owner, int column, int row, String type) {
        this.owner = owner;
        this.column = column;
        this.row = row;
        this.type = type;
        this.buildingType = BuildingType.getBuildingType(type);
    }

    public static Building createABuilding(int column, int row, String type) {
    }

    public static boolean isLandTypeValidForBuilding(Building building, LandType landType) {
    }

    public static boolean isBuildingPlaceValid(int column, int row, String type) {
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

    public boolean areResourcesEnoughToRepair() {
    }

    public boolean isEnemyNearby() {
    }

    public void repair() {
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public User getOwner() {
        return owner;
    }
}