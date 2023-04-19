package model.People;

import java.util.ArrayList;
import java.util.List;

public enum PeopleType {
    ARCHER(new ArrayList<>(List.of())),
    FIGHTER(new ArrayList<>(List.of())),
    SLAVE(new ArrayList<>(List.of())),
    SWORDS_MAN(new ArrayList<>(List.of())),
    THROWERS(new ArrayList<>(List.of())),
    WALL_CRAWLER(new ArrayList<>(List.of())),
    WORKER(new ArrayList<>(List.of()))
    ;

    private final ArrayList<String> People;

    PeopleType(ArrayList<String> people) {
        People = people;
    }

    public static PeopleType getPersonType(String type) {
    }
}
