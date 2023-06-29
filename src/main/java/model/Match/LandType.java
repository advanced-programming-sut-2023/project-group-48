package model.Match;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import model.BackGroundColor;

import java.util.Objects;

public enum LandType {
    LAND(BackGroundColor.BLACK,
            new ImagePattern(new Image(Objects.requireNonNull(LandType.class.getResource("/tiles/land.png")).toString()))),
    LAND_WITH_GRAVEL(BackGroundColor.RED,
            new ImagePattern(new Image(Objects.requireNonNull(LandType.class.getResource("/tiles/land-with-gravel.png")).toString()))),
    BOULDER(BackGroundColor.YELLOW,
            new ImagePattern(new Image(Objects.requireNonNull(LandType.class.getResource("/tiles/boulder.png")).toString()))),
    ROCK(BackGroundColor.BLUE,
            new ImagePattern(new Image(Objects.requireNonNull(LandType.class.getResource("/tiles/rock.png")).toString()))),
    IRON(BackGroundColor.PURPLE,
            new ImagePattern(new Image(Objects.requireNonNull(LandType.class.getResource("/tiles/iron.png")).toString()))),
    GRASS(BackGroundColor.GREEN,
            new ImagePattern(new Image(Objects.requireNonNull(LandType.class.getResource("/tiles/grass.png")).toString()))),
    MEADOW(BackGroundColor.CYAN,
            new ImagePattern(new Image(Objects.requireNonNull(LandType.class.getResource("/tiles/meadow.png")).toString()))),
    DENSE_MEADOW(BackGroundColor.WHITE,
            new ImagePattern(new Image(Objects.requireNonNull(LandType.class.getResource("/tiles/dense-meadow.png")).toString()))),
    OIL(BackGroundColor.RED,
            new ImagePattern(new Image(Objects.requireNonNull(LandType.class.getResource("/tiles/oil.png")).toString()))),
    PLAIN(BackGroundColor.GREEN,
            new ImagePattern(new Image(Objects.requireNonNull(LandType.class.getResource("/tiles/plain.png")).toString()))),
    SHALLOW_WATER(BackGroundColor.BLUE,
            new ImagePattern(new Image(Objects.requireNonNull(LandType.class.getResource("/tiles/shallow-water.png")).toString()))),
    RIVER(BackGroundColor.BLUE,
            new ImagePattern(new Image(Objects.requireNonNull(LandType.class.getResource("/tiles/river.png")).toString()))),
    SMALL_POND(BackGroundColor.BLUE,
            new ImagePattern(new Image(Objects.requireNonNull(LandType.class.getResource("/tiles/small-pond.png")).toString()))),
    BIG_POND(BackGroundColor.WHITE,
            new ImagePattern(new Image(Objects.requireNonNull(LandType.class.getResource("/tiles/big-pond.png")).toString()))),
    BEACH(BackGroundColor.YELLOW,
            new ImagePattern(new Image(Objects.requireNonNull(LandType.class.getResource("/tiles/beach.png")).toString()))),
    SEA(BackGroundColor.BLUE,
            new ImagePattern(new Image(Objects.requireNonNull(LandType.class.getResource("/tiles/sea.png")).toString())));
    private static final LandType[] landTypes = {LAND, LAND_WITH_GRAVEL, BOULDER, ROCK, IRON, GRASS, MEADOW, DENSE_MEADOW};
    private static final LandType[] waterTypes = {OIL, PLAIN, SHALLOW_WATER, RIVER, SMALL_POND, BIG_POND, BEACH, SEA};

    private final BackGroundColor backGroundColor;
    private final ImagePattern imagePattern;

    LandType(BackGroundColor backGroundColor, ImagePattern imagePattern) {
        this.backGroundColor = backGroundColor;
        this.imagePattern = imagePattern;
    }

    public BackGroundColor getBackGroundColor() {
        return backGroundColor;
    }

    public ImagePattern getImagePattern() {
        return imagePattern;
    }

    public static LandType getLandType(String name) {
        for (LandType landType : landTypes) {
            if (landType.toString().equals(name)) return landType;
        }
        for (LandType waterType : waterTypes) {
            if (waterType.toString().equals(name)) return waterType;
        }
        return null;
    }

    @Override
    public String toString() {
        switch (this) {
            case LAND:
                return "land";
            case LAND_WITH_GRAVEL:
                return "Land with gravel";
            case BOULDER:
                return "boulder";
            case ROCK:
                return "rock";
            case IRON:
                return "iron";
            case GRASS:
                return "grass";
            case MEADOW:
                return "meadow";
            case DENSE_MEADOW:
                return "dense meadow";
            case OIL:
                return "oil";
            case PLAIN:
                return "plain";
            case SHALLOW_WATER:
                return "shallow water";
            case RIVER:
                return "river";
            case SMALL_POND:
                return "small pond";
            case BIG_POND:
                return "big pond";
            case BEACH:
                return "beach";
            case SEA:
                return "sea";
        }
        return null;
    }
}