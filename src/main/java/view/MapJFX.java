package view;

import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import model.Match.Tile;

import java.util.Objects;

public class MapJFX {
    private final AnchorPane viewPane;
    private final Pane mapPane;
    private Tile[][] map;
    private int firstI = 199, lastI = 0, firstJ = 199, lastJ = 0;

    public MapJFX(AnchorPane viewPane) {
        this.viewPane = viewPane;
        mapPane = new Pane();
        setMapPane();
        setMap();
    }

    public Pane getMapPane() {
        return mapPane;
    }

    public void addBuildingToMap(int i, int j, ImagePattern imagePattern) {
        System.out.println(imagePattern.getImage().getWidth() + " " + imagePattern.getImage().getHeight());
        Rectangle rectangle = new Rectangle(Tile.WIDTH, imagePattern.getImage().getHeight() / imagePattern.getImage().getWidth() * Tile.WIDTH);
        rectangle.setLayoutX(map[i][j].getX() - rectangle.getWidth() / 2);
        rectangle.setLayoutY(map[i][j].getY() + Tile.HEIGHT / 2 - rectangle.getHeight());
        rectangle.setFill(imagePattern);
        map[i][j].setRectangle(rectangle);
        mapPane.getChildren().add(rectangle);
        rectangle.toFront();
    }

    private void setMapPane() {
        mapPane.setPrefWidth(200 * Tile.WIDTH + 20);
        mapPane.setPrefHeight(200 * Tile.HEIGHT / 2 + 20);
        mapPane.setLayoutX(viewPane.getPrefWidth() / 2 - mapPane.getPrefWidth() / 2);
        mapPane.setLayoutY(viewPane.getPrefHeight() / 2 - mapPane.getPrefHeight() / 2);
        Rectangle rectangle = new Rectangle(0, 0, mapPane.getPrefWidth(), mapPane.getPrefHeight());
        rectangle.setStroke(Color.BLACK);
        mapPane.getChildren().add(rectangle);
        viewPane.getChildren().add(mapPane);

        mapPane.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                System.out.println(keyEvent.getCode().toString());
                switch (keyEvent.getCode()) {
                    case RIGHT:
                        mapPane.setLayoutX(mapPane.getLayoutX() - (2 * Tile.WIDTH));
                        break;
                    case LEFT:
                        mapPane.setLayoutX(mapPane.getLayoutX() + (2 * Tile.WIDTH));
                        break;
                    case UP:
                        mapPane.setLayoutY(mapPane.getLayoutY() + (2 * Tile.HEIGHT / 2));
                        break;
                    case DOWN:
                        mapPane.setLayoutY(mapPane.getLayoutY() - (2 * Tile.HEIGHT / 2));
                        break;
                    case Z:
                        mapPane.setScaleX(0.9 * mapPane.getScaleX());
                        mapPane.setScaleY(0.9 * mapPane.getScaleY());
                        break;
                    case X:
                        mapPane.setScaleX(1 / 0.9 * mapPane.getScaleX());
                        mapPane.setScaleY(1 / 0.9 * mapPane.getScaleY());
                        break;
                }
                if (keyEvent.getCode().equals(KeyCode.RIGHT) || keyEvent.getCode().equals(KeyCode.DOWN)
                        || keyEvent.getCode().equals(KeyCode.LEFT) || keyEvent.getCode().equals(KeyCode.UP)) {
                    addNewCells(keyEvent);
                    removeOldCells(keyEvent);
                    bringBuildingsToFront();
                    System.out.println("firstI = " + firstI + " lastI = " + lastI + " firstJ = " + firstJ + " lastJ = " + lastJ);
                }
            }
        });
    }

    private void bringBuildingsToFront() {
        for (int i = firstI; i <= lastI; i++) {
            for (int j = firstJ; j <= lastJ; j++) {
                if (map[i][j].getRectangle() != null) {
                    map[i][j].getRectangle().toFront();
                }
            }
        }
    }

    private void setMap() {
        ImagePattern defaultTileImage = new ImagePattern(new Image(Objects.requireNonNull(getClass().getResource("/tiles/desert_tile.jpg")).toExternalForm()));
        map = new Tile[200][200];
        for (int i = 0; i < 200; i++) {
            for (int j = 0; j < 200; j++) {
                map[i][j] = new Tile(Tile.WIDTH / 2 + j * Tile.WIDTH + ((i % 2 == 0) ? 0 : Tile.WIDTH / 2),
                        mapPane.getPrefHeight() - Tile.HEIGHT / 2 - i * (Tile.HEIGHT / 2));
                map[i][j].setFill(defaultTileImage);
                int finalJ = j;
                int finalI = i;
                int finalJ1 = j;
                int finalI1 = i;
                map[i][j].setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        System.out.println("i " + finalI1 + " j " + finalJ1);
                        map[finalI][finalJ].setOpacity(1.5 - map[finalI][finalJ].getOpacity());
                    }
                });
                if (isInBorder(map[i][j])) {
                    mapPane.getChildren().add(map[i][j]);
                    firstI = Math.min(firstI, i);
                    firstJ = Math.min(firstJ, j);
                    lastI = Math.max(lastI, i);
                    lastJ = Math.max(lastJ, j);
                }
            }
        }
        System.out.println("firstI = " + firstI + " lastI = " + lastI + " firstJ = " + firstJ + " lastJ = " + lastJ);
    }

    private void addNewCells(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.RIGHT) || keyEvent.getCode().equals(KeyCode.LEFT)) {
            boolean isRight = keyEvent.getCode().equals(KeyCode.RIGHT);
            for (int j = isRight ? lastJ : firstJ; j != (isRight ? 200 : -1); j += isRight ? 1 : -1) {
                for (int i = firstI; i <= lastI; i++) {
                    if (isInBorder(map[i][j])) {
                        if (!mapPane.getChildren().contains(map[i][j])) addCellToPane(map[i][j]);
                    }
                    else {
                        if (isRight) lastJ = j - 1;
                        else firstJ = j + 1;
                        return;
                    }
                }
            }
        } else {
            boolean isUp = keyEvent.getCode().equals(KeyCode.UP);
            for (int i = isUp ? lastI : firstI; i != (isUp ? 200 : -1); i += isUp ? 1 : -1) {
                for (int j = firstJ; j <= lastJ; j++) {
                    if (isInBorder(map[i][j])) {
                        if (!mapPane.getChildren().contains(map[i][j])) addCellToPane(map[i][j]);
                    }
                    else {
                        System.out.println("i = " + i + " j = " + j);
                        System.out.println(isInBorder(map[i][j]));
                        System.out.println("x = " + (map[i][j].getX() + mapPane.getLayoutX())  + " y = " + (map[i][j].getY() + mapPane.getLayoutY()));
                        if (isUp) lastI = i - 1;
                        else firstI = i + 1;
                        return;
                    }
                }
            }
        }
    }

    private void addCellToPane(Tile tile) {
        mapPane.getChildren().add(tile);
        if (tile.getRectangle() != null) {
            mapPane.getChildren().add(tile.getRectangle());
        }
    }

    private void removeOldCells(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.RIGHT) || keyEvent.getCode().equals(KeyCode.LEFT)) {
            boolean isRight = keyEvent.getCode().equals(KeyCode.RIGHT);
            for (int j = isRight ? firstJ : lastJ; j != (isRight ? 200 : -1); j += isRight ? 1 : -1) {
                for (int i = 0; i < 200; i++) {
                    if (!isInBorder(map[i][j])) removeCellFromPane(map[i][j]);
                    else {
                        if (isRight) firstJ = j;
                        else lastJ = j;
                        return;
                    }
                }
            }
        } else {
            boolean isUp = keyEvent.getCode().equals(KeyCode.UP);
            for (int i = isUp ? firstI : lastI; i != (isUp ? 200 : -1); i += isUp ? 1 : -1) {
                for (int j = 0; j < 200; j++) {
                    if (!isInBorder(map[i][j])) removeCellFromPane(map[i][j]);
                    else {
                        if (isUp) firstI = i;
                        else lastI = i;
                        return;
                    }
                }
            }
        }
    }

    private void removeCellFromPane(Tile tile) {
        mapPane.getChildren().remove(tile);
        if (tile.getRectangle() != null) {
            mapPane.getChildren().remove(tile.getRectangle());
            if (tile.getRectangle() != null) mapPane.getChildren().remove(tile);
        }
    }

    private boolean isInBorder(Tile tile) {
        return (tile.getX() + mapPane.getLayoutX() >= -Tile.WIDTH) && (tile.getX() + mapPane.getLayoutX() - viewPane.getPrefWidth() <= Tile.WIDTH)
                && (tile.getY() + mapPane.getLayoutY() >= -Tile.HEIGHT) && (tile.getY() + mapPane.getLayoutY() - viewPane.getPrefHeight() <= Tile.HEIGHT);
    }
}
