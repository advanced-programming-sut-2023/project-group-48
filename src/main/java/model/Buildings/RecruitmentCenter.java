package model.Buildings;

import model.Match.Direction;
import model.Match.Governance;
import model.People.People;
import model.People.PeopleType;
import model.User;

import java.util.ArrayList;
import java.util.HashMap;

public class RecruitmentCenter extends Building {
    private static HashMap<String, Integer> costsByCharacterType = new HashMap<>();
    private final ArrayList<People> recruits;

    public RecruitmentCenter(Governance governance, int column, int row, String type, BuildingType buildingType, Direction direction, ArrayList<People> recruits) {
        super(governance, column, row, type, buildingType, direction);
        this.recruits = new ArrayList<>();
    }

    public boolean areResourcesEnoughToAdd() {
    }

    public boolean isPopularityEnough() {
    }

    public boolean isRecruitTypeValid() {
    }

    public void addRecruits(PeopleType peopleType, int count) {
        for (int i = 0; i < count; i++) {
            recruits.add(People.generatePeople(peopleType));
        }
    }
}