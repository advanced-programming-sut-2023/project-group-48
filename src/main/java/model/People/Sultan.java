package model.People;

import model.Match.Governance;

public class Sultan extends People {
    private final Governance governance;


    public Sultan(int column, int row, String type, PeopleType peopleType, Governance governance) {
        super(column, row, "Sultan", PeopleType.SULTAN);
        this.governance = governance;
    }

    public Governance getGovernance() {
        return governance;
    }
}