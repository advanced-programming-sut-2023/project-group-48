package view;

import javafx.animation.Transition;
import javafx.scene.paint.ImagePattern;
import javafx.util.Duration;
import model.Match.Direction;
import model.Match.Match;
import model.People.People;
import model.People.PeopleType;

import java.util.ArrayList;

public class WalkingAnimation extends Transition {
    private static final double TILE_DISTANCE = Math.sqrt(Math.pow(Tile.WIDTH, 2) + Math.pow(Tile.HEIGHT, 2));
    private final Match match;
    private final People people;
    private final MapJFX mapJFX;
    private final int imagesCount;
    private ArrayList<ImagePattern> moves;

    private ImagePattern currentImagePattern;
    private int lastPathSize, indexOfCurrentImage;
    private Tile targetTile;

    public WalkingAnimation(Match match, People people, MapJFX mapJFX) {
        this.match = match;
        this.people = people;
        this.mapJFX = mapJFX;
        this.imagesCount = PeopleType.getMovingImages(people.getType(), 1).size();
        setCycleDuration(Duration.millis((double) 1000 / imagesCount));
        setCycleCount(people.getPath().size() * imagesCount);
    }

    @Override
    protected void interpolate(double v) {
        if (people.getPath().size() != lastPathSize) {
            targetTile = getTargetTile();
            moves = PeopleType.getMovingImages(people.getType(), match.getGovernances().indexOf(people.getGovernance()) + 1);
            indexOfCurrentImage = 0;
            lastPathSize = people.getPath().size();
        }

        double x = people.getRectangle().getX() + (targetTile.getX() - people.getCurrentTile().getX()) / imagesCount;
        double y = people.getRectangle().getY() + (targetTile.getY() - people.getCurrentTile().getY()) / imagesCount;
        currentImagePattern = moves.get(indexOfCurrentImage);

        people.getRectangle().setX(x);
        people.getRectangle().setY(y);
        people.getRectangle().setFill(currentImagePattern);

        if (people.getRectangle().getX() == targetTile.getX() - people.getRectangle().getWidth() / 2) {
            people.getPath().remove(0);
            people.setCurrentTile(targetTile);
        }
    }

    private Tile getTargetTile() {
        Direction direction = people.getPath().get(0);
        int[] coordinates = mapJFX.getCoordinates(people.getRow(), people.getColumn());
        switch (direction) {
            case UP:
                return mapJFX.getTile(coordinates[0] + 1, coordinates[1]);
            case DOWN:
                return mapJFX.getTile(coordinates[0] - 1, coordinates[1]);
            case LEFT:
                return mapJFX.getTile(coordinates[0], coordinates[1] - 1);
            case RIGHT:
                return mapJFX.getTile(coordinates[0], coordinates[1] + 1);
            default:
                return null;
        }
    }


}