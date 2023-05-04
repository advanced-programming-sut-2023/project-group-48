package model.People;

import model.Buildings.BuildingType;
import model.Match.Direction;

import java.util.ArrayList;

public class People {
    private int column;
    private int row;
    private final String type;
    private final PeopleType peopleType;
    private Job job;
    private int hp;
    private ArrayList<Direction> path;

    public People(int column, int row, String type, PeopleType peopleType) {
        this.column = column;
        this.row = row;
        this.type = type;
        this.peopleType = peopleType;
        this.path = null;
    }

    public static People generatePeople(PeopleType peopleType) {

    }

    public static boolean canAttack(People people, People targetPeople) {
        if (people instanceof Worker) return false;
        return !(Math.pow(((Troop) people).getFireRange(), 2) < Math.pow(people.getColumn() - targetPeople.getColumn(), 2) + Math.pow(people.getRow() - targetPeople.getRow(), 2));
    }

    public PeopleType getPeopleType() {
        return peopleType;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
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

    public void setPath(ArrayList<Direction> path) {
        this.path = path;
    }
}