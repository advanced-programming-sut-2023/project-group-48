package model.Buildings;

import model.Match.Direction;
import model.Match.Governance;
import model.People.Nation;
import model.People.People;
import model.People.PeopleType;
import model.People.Troop;
import model.User;

import java.util.ArrayList;
import java.util.HashMap;

public class RecruitmentCenter extends Building {
    private static final HashMap<String, String> validRecruitTypes = new HashMap<>() {{
        put("Barrack","European");
        put("Mercenary Post","Arabian");
        put("Engineer Guild", "Engineer");
    }};
    private final ArrayList<People> recruits;
    private final String validRecruitType;

    public RecruitmentCenter(Governance governance, int row, int column, String type, BuildingType buildingType, Direction direction) {
        super(governance, row, column, type, buildingType, direction);
        this.recruits = new ArrayList<>();
        this.validRecruitType = validRecruitTypes.get(type);

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
        if(type.equals("Engineer")){
            return type.equals(validRecruitType);
        }
        else if(Troop.getTroopNation(type) == Nation.ARAB){
            return "Arabian".equals(validRecruitType);}
        else if(Troop.getTroopNation(type) == Nation.EUROPE){
            return "European".equals(validRecruitType);}
        return false;
    }
}