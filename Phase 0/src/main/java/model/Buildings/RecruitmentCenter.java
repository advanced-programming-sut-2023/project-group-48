package model.Buildings;

import model.People.People;
import model.User;

import java.util.ArrayList;
import java.util.HashMap;

public class RecruitmentCenter extends Building {
    private static HashMap<String, Integer> costsByCharacterType;
    private ArrayList<People> people;

    public RecruitmentCenter(User owner, int column, int row, String type) {
        super(owner, column, row, type);
    }

    public boolean areResourcesEnoughToAdd() {}

    public boolean isPopularityEnough() {}

    public boolean isRecruitTypeValid() {}

    public void AddRecruits(String type, int count) {}
}
