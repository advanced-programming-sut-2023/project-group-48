package model.Buildings;

import model.Match.Direction;
import model.Match.Governance;
import model.Match.LandType;
import model.User;

public class Building {
    private final Governance governance;
    private final int column;
    private final int row;
    private final String type;
    private final BuildingType buildingType;
    private int hp;
    private final Direction direction;


    public Building(Governance governance, int column, int row, String type, BuildingType buildingType, int hp, Direction direction) {
        this.governance = governance;
        this.column = column;
        this.row = row;
        this.type = type;
        this.buildingType = buildingType;
        this.hp = hp;
        this.direction = direction;
    }

    public String createBuildingByType(String type) {
        if (BuildingType.getBuildingType(type) == null) return null;
        switch (BuildingType.getBuildingType(type)) {
            case NORMAL:
                return new Building();
            break;
            case TOWER:
                return new Tower();
                break;
            case INDUSTRIAL_CENTER:
                return new IndustrialCenter();
                break;
            case INN:
                return new Inn();
                break;
            case TRAP:
                return new Trap();
                break;
            case RECRUITMENT_CENTER:
                return new RecruitmentCenter();
                break;
            case STORAGE:
                return new String();
                break;
        }
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

    public Governance getGovernance() {
        return governance;
    }
}