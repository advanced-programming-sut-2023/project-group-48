package view;

import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import model.Match.Cell;

public class Tile extends Polygon {
    public static final double WIDTH = 44.0 * 1.5;
    public static final double HEIGHT = 24.0 * 1.5;
    private final double x, y;
    private final int i, j;
    private Cell cell;
    private Rectangle rectangle;
    public Tile(double x, double y) {
        this.x = x;
        this.y = y;
        this.i = 0;
        this.j = 0;
        this.getPoints().addAll(x - WIDTH / 2, y, x, y - HEIGHT / 2, x + WIDTH / 2, y, x, y + HEIGHT / 2);
    }
    public Tile(double x, double y, int i, int j) {
        this.x = x;
        this.y = y;
        this.i = i;
        this.j = j;
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

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }

    public Cell getCell() {
        return cell;
    }

    public void setCell(Cell cell) {
        this.cell = cell;
    }
}