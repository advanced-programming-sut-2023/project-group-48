package model.Match;

import model.BackGroundColor;

public enum LandType {
    LAND(BackGroundColor.BLACK), LAND_WITH_GRAVEL(BackGroundColor.RED), BOULDER(BackGroundColor.YELLOW), ROCK(BackGroundColor.BLUE), IRON(BackGroundColor.PURPLE), GRASS(BackGroundColor.GREEN), MEADOW(BackGroundColor.CYAN), DENSE_MEADOW(BackGroundColor.WHITE), OIL(BackGroundColor.RED), PLAIN(BackGroundColor.GREEN), SHALLOWWATER(BackGroundColor.BLUE), RIVER(BackGroundColor.BLUE), SMALLPOND(BackGroundColor.BLUE), BIGPOND(BackGroundColor.WHITE), BEACH(BackGroundColor.YELLOW), SEA(BackGroundColor.BLUE);
    private static final LandType[] landTypes = {LAND, LAND_WITH_GRAVEL, BOULDER, ROCK, IRON, GRASS, MEADOW, DENSE_MEADOW};
    private static final LandType[] waterTypes = {OIL, PLAIN, SHALLOWWATER, RIVER, SMALLPOND, BIGPOND, BEACH, SEA};

    private final BackGroundColor backGroundColor;

    LandType(BackGroundColor backGroundColor) {
        this.backGroundColor = backGroundColor;
    }

    public BackGroundColor getBackGroundColor() {
        return backGroundColor;
    }

    public static LandType getLandType(String name) {
        for (LandType landType : landTypes) {
            if (landType.toString().equals(name)) return landType;
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
            case SHALLOWWATER:
                return "shallow water";
            case RIVER:
                return "river";
            case SMALLPOND:
                return "small pond";
            case BIGPOND:
                return "big pond";
            case BEACH:
                return "beach";
            case SEA:
                return "sea";
        }
        return null;
    }
}