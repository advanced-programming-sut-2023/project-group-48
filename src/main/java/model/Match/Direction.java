package model.Match;

public enum Direction {
    UP("up"), DOWN("down"), LEFT("left"), RIGHT("right");

    private static final Direction[] directions = {UP, DOWN, LEFT, RIGHT};
    private final String name;

    private Direction(String name) {
        this.name = name;
    }

    public static Direction[] getDirections() {
        return directions;
    }

    private String getName() {
        return name;
    }

    public static Direction getDirection(String name) {
        for (Direction direction : directions) {
            if (direction.getName().equals(name)) return direction;
        }
        return null;
    }
}