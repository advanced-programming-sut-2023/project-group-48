package view;

import javafx.scene.shape.Rectangle;
import model.People.People;

public class PeopleShape extends Rectangle {
    private final People people;
    private Tile currentTile;
    private WalkingAnimation walkingAnimation;

    public PeopleShape(double width, double height, People people) {
        super(width, height);
        this.people = people;
    }

    public People getPeople() {
        return people;
    }

    public Tile getCurrentTile() {
        return currentTile;
    }

    public void setCurrentTile(Tile currentTile) {
        this.currentTile = currentTile;
    }

    public WalkingAnimation getWalkingAnimation() {
        return walkingAnimation;
    }

    public void setWalkingAnimation(WalkingAnimation walkingAnimation) {
        this.walkingAnimation = walkingAnimation;
    }
}
