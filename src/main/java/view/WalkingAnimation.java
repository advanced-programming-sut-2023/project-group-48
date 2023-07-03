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
        this.imagesCount = PeopleType.WALKIG_IMAGE_COUNT;
        setCycleDuration(Duration.millis((double) 1000 / imagesCount));
        setCycleCount(people.getPath().size() * imagesCount);
    }

    @Override
    protected void interpolate(double v) {
        if (people.getPath().size() != lastPathSize) {
            targetTile = getTargetTile();
            moves = PeopleType.getMovingImages(people.getType(), match.getGovernances().indexOf(people.getGovernance()) + 1, getDirectionNumber());
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
            // TODO set people row and column
        }
    }

    private int getDirectionNumber() {
        switch (people.getPath().get(0)) {
            case UP:
                return 1;
            case RIGHT:
                return 3;
            case DOWN:
                return 5;
            case LEFT:
                return 7;
            default:
                return 2;
        }
    }

    private Tile getTargetTile() {
        Direction direction = people.getPath().get(0);
        int[] coordinates;
        switch (direction) {
            case UP:
                coordinates = mapJFX.getCoordinates(people.getRow() + 1, people.getColumn());
                return mapJFX.getTile(coordinates[0] + 1, coordinates[1]);
            case DOWN:
                coordinates = mapJFX.getCoordinates(people.getRow() - 1, people.getColumn());
                return mapJFX.getTile(coordinates[0] - 1, coordinates[1]);
            case LEFT:
                coordinates = mapJFX.getCoordinates(people.getRow(), people.getColumn() - 1);
                return mapJFX.getTile(coordinates[0], coordinates[1] - 1);
            case RIGHT:
                coordinates = mapJFX.getCoordinates(people.getRow(), people.getColumn() + 1);
                return mapJFX.getTile(coordinates[0], coordinates[1] + 1);
            default:
                return null;
        }
    }
}