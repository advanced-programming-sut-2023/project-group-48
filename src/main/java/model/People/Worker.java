package model.People;

import model.Match.Governance;

public class Worker extends People {


    public Worker(Governance governance, int row, int column, String type, PeopleType peopleType) {
        super(governance, row, column, type, peopleType);
    }
}