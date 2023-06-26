package view;

import javafx.animation.Transition;
import model.Match.Direction;
import model.People.People;
import model.People.PeopleType;

import java.util.ArrayList;

public class WalkingAnimation extends Transition {
    private final People people;
    private final ArrayList<Direction> path;

    public WalkingAnimation(People people, ArrayList<Direction> path) {
        this.people = people;
        this.path = path;
    }

    @Override
    protected void interpolate(double v) {
        if (path.size() == 0) {
            stop();
            return;
        }
        Direction direction = path.get(0);
        if (direction == Direction.UP) {
            people.setRow(people.getRow() + 1);
        } else if (direction == Direction.DOWN) {
            people.setRow(people.getRow() - 1);
        } else if (direction == Direction.LEFT) {
            people.setRow(people.getRow() - 1);
        } else if (direction == Direction.RIGHT) {
            people.setRow(people.getRow() + 1);
        }
        path.remove(0);
    }
}