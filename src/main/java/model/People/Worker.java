package model.People;

import model.Match.Governance;

public class Worker extends People {
    private final Job job;

    public Worker(Governance governance, int row, int column, String type, PeopleType peopleType, Job job) {
        super(governance, row, column, type, peopleType);
        this.job = job;
    }
}