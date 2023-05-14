package model.Buildings;

import model.Match.Direction;
import model.Match.Governance;
import model.Match.LandType;

public class Building {
    private final Governance governance;
    private final int row;
    private final int column;
    private final String type;
    private final BuildingType buildingType;
    private int hp;
    private final Direction direction;


    public Building(Governance governance, int row, int column,  String type, BuildingType buildingType, Direction direction) {
        this.governance = governance;
        this.row = row;
        this.column = column;
        this.type = type;
        this.buildingType = buildingType;
        this.hp = BuildingType.getHP(type);
        this.direction = direction;
    }


    public static Building createBuildingByType(Governance governance, int row, int column, String type, BuildingType buildingType, Direction direction) {
        switch (buildingType) {
            case NORMAL:
                return new Building(governance, row, column, type, BuildingType.NORMAL, direction);
            case TOWER:
                return new Tower(governance, row, column, type, BuildingType.TOWER, direction);
            case INDUSTRIAL_CENTER:
                return new IndustrialCenter(governance, row, column, type, BuildingType.INDUSTRIAL_CENTER, direction);
            case INN:
                return new Inn(governance, row, column, type, BuildingType.INN, direction);
            case TRAP:
                return new Trap(governance, row, column, type, BuildingType.TRAP, direction);
            case RECRUITMENT_CENTER:
                return new RecruitmentCenter(governance, row, column, type, BuildingType.RECRUITMENT_CENTER, direction);
            case STORAGE:
                return new Storage(governance, row, column, type, BuildingType.STORAGE, direction);
            default:
                return null;
        }

    }

    public static boolean isLandTypeValidForBuilding(String buildingType, LandType landType) {
        return BuildingType.getValidLandTypes(buildingType).contains(landType);
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

    public void repair() {
        hp = BuildingType.getHP(type);
    }

    public Direction getDirection() {
        return direction;
    }

    public Governance getGovernance() {
        return governance;
    }

    public void takeDamage(int damage) {
        hp -= damage;
    }

    public boolean canCreateUnit(String type) {
        if(this instanceof RecruitmentCenter) {
            if(((RecruitmentCenter) this).isRecruitTypeValid(type)) {
                return true;
            }
        }
        return false;
    }
}