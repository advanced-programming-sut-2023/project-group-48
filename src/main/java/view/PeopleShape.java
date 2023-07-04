package view;

import javafx.scene.shape.Rectangle;
import model.People.People;

public class PeopleShape extends Rectangle {
    private final People people;

    public PeopleShape(double width, double height, People people) {
        super(width, height);
        this.people = people;
    }

    public People getPeople() {
        return people;
    }
}
