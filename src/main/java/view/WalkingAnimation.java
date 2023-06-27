package view;

import javafx.animation.Transition;
import javafx.util.Duration;
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
        setCycleDuration(Duration.millis(1000));
        setCycleCount(path.size());
    }

    @Override
    protected void interpolate(double v) {
        Direction direction = path.get(0);
        // TODO: 5/29/2021
    }
}