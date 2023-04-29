package model.People;

import model.Match.Direction;

import java.util.ArrayList;

public class People {
    private int column;
    private int row;
    private final String type;
    private final PeopleType peopleType;
    private Job job;

    public People(int column, int row, String type, PeopleType peopleType) {
        this.column = column;
        this.row = row;
        this.type = type;
        this.peopleType = peopleType;
    }

    public static People generatePeople(PeopleType peopleType) {}
    public static ArrayList<Direction> generatePath(int destinationColumn, int destinationRow, People people) {
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
}
