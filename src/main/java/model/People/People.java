package model.People;

import model.Match.Direction;

import java.util.ArrayList;

public class People {
    private int column;
    private int row;
    private String type;
    private PeopleType peopleType;
    private Job job;

    public People(int column, int row, String type) {
        this.column = column;
        this.row = row;
        this.type = type;
    }

    public ArrayList<Direction> generatePath(int column, int row) {
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
