package model.Buildings;

import model.Match.Direction;
import model.Match.Governance;
import model.People.Nation;
import model.People.People;
import model.People.PeopleType;
import model.User;

import java.util.ArrayList;
import java.util.HashMap;

public class RecruitmentCenter extends Building {
    private static final HashMap<String, String> validRecruitTypes = new HashMap<>() {{
        put("Barrack","Swordsmen");
        put("Mercenary Post","Arabian Swordsmen");
        put("Engineer Guild", "Engineer");
    }};
    private final ArrayList<People> recruits;
    private final String validRecruitType;

    public RecruitmentCenter(Governance governance, int row, int column, String type, BuildingType buildingType, Direction direction) {
        super(governance, row, column, type, buildingType, direction);
        this.recruits = new ArrayList<>();
        this.validRecruitType = validRecruitTypes.get(type);

        // TODO validRecruitType
    }

    public void addRecruits(PeopleType peopleType, int count) {
        for (int i = 0; i < count; i++) {
            recruits.add(People.createPeopleByType(getGovernance(), getRow(), getColumn(), validRecruitType, peopleType));
        }
    }
    public void removeRecruit(People people) {
        recruits.remove(people);
    }

    public ArrayList<People> getRecruits() {
        return recruits;
    }
    public boolean isRecruitTypeValid(String type) {
        return type.equals(validRecruitType);
    }
}