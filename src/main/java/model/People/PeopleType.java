package model.People;

import model.Buildings.Building;

import java.util.ArrayList;
import java.util.List;

public enum PeopleType {
    TROOP(new ArrayList<>(List.of("Archer", "Crossbowmen", "Spearmen", "Pikemen", "Macemen", "Swordsmen", "Knight", "Tunneler", "Laddermen", "Engineer", "Black Monk", "Archer Bow", "Slaves", "Slingers", "Assassins", "Horse Archers", "Arabian Swordsmen", "Fire Throwers"))),
    WORKER(new ArrayList<>(List.of("Engineer")))
    ;

    private static final PeopleType[] peopleTypes = {TROOP, WORKER};
    private final ArrayList<String> People;

    PeopleType(ArrayList<String> people) {
        People = people;
    }

    public static PeopleType getPersonType(String type) {
        for (PeopleType peopleType : peopleTypes) {
            if (peopleType.People.contains(type)) return peopleType;
        }
        return null;
    }
}
