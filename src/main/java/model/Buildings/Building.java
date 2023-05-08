package model.Buildings;

import model.Match.Direction;
import model.Match.Governance;
import model.Match.LandType;
import model.People.PeopleType;
import model.User;

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

    // TODO
    public Building createBuildingByType(Governance governance, int row, int column,  String type, Direction direction, BuildingType buildingType) {
        switch (BuildingType.getBuildingType(type)) {
            case NORMAL:
                return new Building(governance, column, row, type, BuildingType.NORMAL, direction);
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
                return new Storage();
            break;
            case null:
                return null;
            break;
            default:
                return null;
        }

    }

    public static boolean isLandTypeValidForBuilding(String buildingType, LandType landType) {
        return BuildingType.getValidLandTypes(buildingType).contains(landType);
    }

    public static boolean isBuildingPlaceValid(int row, int column, String type) {
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

    public boolean isEnemyNearby(int row, int column) {
        // if enemy building is near the location
    }

    public void repair() {
        hp = BuildingType.getHP(type);
    } // why not static ?

    public Direction getDirection() {
        return direction;
    }

    public Governance getGovernance() {
        return governance;
    }

    public void takeDamage(int damage) {
        hp -= damage;
    }
}