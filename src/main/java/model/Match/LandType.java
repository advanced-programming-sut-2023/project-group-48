package model.Match;

import model.BackGroundColor;

public enum LandType {
    LAND(BackGroundColor.BLACK), LAND_WITH_GRAVEL(BackGroundColor.RED), BOULDER(BackGroundColor.YELLOW), ROCK(BackGroundColor.BLUE), IRON(BackGroundColor.PURPLE), GRASS(BackGroundColor.GREEN), MEADOW(BackGroundColor.CYAN), DENSE_MEADOW(BackGroundColor.WHITE);
    private final BackGroundColor backGroundColor;

    LandType(BackGroundColor backGroundColor) {
        this.backGroundColor = backGroundColor;
    }

    public BackGroundColor getBackGroundColor() {
        return backGroundColor;
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
        }
        return null;
    }
}