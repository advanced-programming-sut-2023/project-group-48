package model.Match;

import javafx.scene.shape.Polygon;

public class Tile extends Polygon {
    public static final double WIDTH = 44.0;
    public static final double HEIGHT = 24.0;
    private final double x;
    private final double y;
    public Tile(double x, double y) {
        this.x = x;
        this.y = y;
        this.getPoints().addAll(x - WIDTH / 2, y, x, y - HEIGHT / 2, x + WIDTH / 2, y, x, y + HEIGHT / 2);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}