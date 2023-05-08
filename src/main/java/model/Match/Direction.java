package model.Match;

public enum Direction {
    UP, DOWN, LEFT, RIGHT;

    private static final Direction[] directions = {UP, DOWN, LEFT, RIGHT};

    public static Direction[] getDirections() {
        return directions;
    }
}