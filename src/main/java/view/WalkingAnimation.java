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
    private final PeopleShape peopleShape;
    private final MapJFX mapJFX;
    private final int imagesCount;
    private ArrayList<ImagePattern> moves;

    private ImagePattern currentImagePattern;
    private int lastPathSize, indexOfCurrentImage;
    private Tile targetTile;

    public WalkingAnimation(Match match, PeopleShape peopleShape, MapJFX mapJFX) {
        System.out.println("pathsize " + peopleShape.getPeople().getPath().size());
        this.match = match;
        this.peopleShape = peopleShape;
        this.mapJFX = mapJFX;
        this.imagesCount = PeopleType.WALKIG_IMAGE_COUNT;
        this.lastPathSize = 0;
        setCycleDuration(Duration.millis(10000));
        setCycleCount(INDEFINITE);
    }

    @Override
    protected void interpolate(double v) {
        if (peopleShape.getPeople().getPath().size() == 0) {
            this.stop();
        }
        if (peopleShape.getPeople().getPath().size() != lastPathSize) {
            targetTile = getTargetTile();
            moves = PeopleType.getMovingImages(peopleShape.getPeople().getType(),
                    match.getGovernances().indexOf(peopleShape.getPeople().getGovernance()) + 1, getDirectionNumber());
            indexOfCurrentImage = 0;
            lastPathSize = peopleShape.getPeople().getPath().size();
        }
        double x = peopleShape.getX() + (targetTile.getX() - peopleShape.getCurrentTile().getX()) / imagesCount;
        double y = peopleShape.getY() + (targetTile.getY() - peopleShape.getCurrentTile().getY()) / imagesCount;
        currentImagePattern = moves.get(indexOfCurrentImage);
        indexOfCurrentImage = (indexOfCurrentImage + 1) % imagesCount;

        peopleShape.setX(x);
        peopleShape.setY(y);
        peopleShape.setFill(currentImagePattern);

        if (peopleShape.getX() - targetTile.getX() + peopleShape.getWidth() / 2 < 2) {
            System.out.println(peopleShape.getPeople().getPath().get(0).toString());
            peopleShape.getPeople().getPath().remove(0);
            peopleShape.setCurrentTile(targetTile);
            peopleShape.getPeople().setRow(targetTile.getCell().getRow());
            peopleShape.getPeople().setColumn(targetTile.getCell().getColumn());
        }
    }

    private int getDirectionNumber() {
        switch (peopleShape.getPeople().getPath().get(0)) {
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
        Direction direction = peopleShape.getPeople().getPath().get(0);
        int[] coordinates;
        switch (direction) {
            case UP:
                coordinates = mapJFX.getCoordinates(peopleShape.getPeople().getRow() + 1, peopleShape.getPeople().getColumn());
                return mapJFX.getTile(coordinates[0] + 1, coordinates[1]);
            case DOWN:
                coordinates = mapJFX.getCoordinates(peopleShape.getPeople().getRow() - 1, peopleShape.getPeople().getColumn());
                return mapJFX.getTile(coordinates[0] - 1, coordinates[1]);
            case LEFT:
                coordinates = mapJFX.getCoordinates(peopleShape.getPeople().getRow(), peopleShape.getPeople().getColumn() - 1);
                return mapJFX.getTile(coordinates[0], coordinates[1] - 1);
            case RIGHT:
                coordinates = mapJFX.getCoordinates(peopleShape.getPeople().getRow(), peopleShape.getPeople().getColumn() + 1);
                return mapJFX.getTile(coordinates[0], coordinates[1] + 1);
            default:
                return null;
        }
    }
}