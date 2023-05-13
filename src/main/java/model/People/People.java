package model.People;

import model.Buildings.BuildingType;
import model.Match.Direction;
import model.Match.Governance;
import model.Match.LandType;

import java.util.ArrayList;

public class People {
    private final Governance governance;
    private int row;
    private int column;
    private final String type;
    private final PeopleType peopleType;
    private int hp;
    private ArrayList<Direction> path;

    public People(Governance governance, int row, int column, String type, PeopleType peopleType) {
        this.governance = governance;
        this.row = row;
        this.column = column;
        this.type = type;
        this.peopleType = PeopleType.getPersonType(type);
        this.hp = PeopleType.getPeopleHp(type);
        this.path = null;
    }

    public static People createPeopleByType(Governance governance, int row, int column, String type, PeopleType peopleType) {
        switch (peopleType) {
            case WORKER:
                return new Worker(governance, row, column, type, PeopleType.WORKER, Job.getJob(type));
            case TROOP:
                return new Troop(governance, row, column, type, PeopleType.TROOP);
            default:
                return null;
        }
    }

    public static boolean canAttack(People people, People targetPeople) {
        if (people instanceof Worker) return false;
        return !(Math.pow(((Troop) people).getFireRange(), 2) < Math.pow(people.getColumn() - targetPeople.getColumn(), 2) + Math.pow(people.getRow() - targetPeople.getRow(), 2));
    }

    public static boolean isLandTypeNotValidForCreatingUnit(String peopleType, LandType landType) {
        return PeopleType.getNotValidLandTypes(peopleType).contains(landType);
    }

    public Governance getGovernance() {
        return governance;
    }

    public String getType() {
        return type;
    }

    public PeopleType getPeopleType() {
        return peopleType;
    }

    public int getHp() {
        return hp;
    }

    public void takeDamage(int damage) {
        this.hp -= damage;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public ArrayList<Direction> getPath() {
        return path;
    }

    public void setPath(ArrayList<Direction> path) {
        this.path = path;
    }
}