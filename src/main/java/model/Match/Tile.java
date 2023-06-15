package model.Match;

import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;

public class Tile extends Polygon {
    public static final double WIDTH = 44.0 * 1.5;
    public static final double HEIGHT = 24.0 * 1.5;
    private final double x;
    private final double y;
    private Rectangle rectangle;
    public Tile(double x, double y) {
        this.x = x;
        this.y = y;
        this.getPoints().addAll(x - WIDTH / 2, y, x, y - HEIGHT / 2, x + WIDTH / 2, y, x, y + HEIGHT / 2);
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}