package view;

import controller.MapMenuController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import model.Match.LandType;

import java.io.IOException;
import java.util.Objects;

public class MapJFX {
    private final MapMenuController mapMenuController;
    private final MatchMenuJFX matchMenuJFX;
    private final AnchorPane viewPane, tileStatusPane;
    private final Label tileStatus;
    private final Pane mapPane;
    private Tile[][] map;
    private Rectangle selectionArea;
    private final int MAP_SIZE = 200;
    private int firstI = MAP_SIZE * 2 - 2, lastI = 0, firstJ = MAP_SIZE - 1, lastJ = 0;

    public MapJFX(MapMenuController mapMenuController, MatchMenuJFX matchMenuJFX, AnchorPane viewPane) throws IOException {
        this.mapMenuController = mapMenuController;
        this.matchMenuJFX = matchMenuJFX;
        this.viewPane = viewPane;
        this.tileStatusPane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/TileStatus.fxml")));
        this.tileStatus = (Label) tileStatusPane.getChildren().get(0);
        mapPane = new Pane();
        setMapPane();
        setMap();
        setSelectionArea();
    }

    private void setMapPane() {
        mapPane.setPrefWidth((MAP_SIZE + 0.5) * Tile.WIDTH);
        mapPane.setPrefHeight((MAP_SIZE * 2) * Tile.HEIGHT / 2);
        mapPane.setLayoutX(viewPane.getPrefWidth() / 2 - mapPane.getPrefWidth() / 2);
        mapPane.setLayoutY(viewPane.getPrefHeight() / 2 - mapPane.getPrefHeight() / 2);
        Rectangle rectangle = new Rectangle(0, 0, mapPane.getPrefWidth(), mapPane.getPrefHeight());
        rectangle.setStroke(Color.BLACK);
        mapPane.getChildren().add(rectangle);
        viewPane.getChildren().add(mapPane);

        setMapMovable();
    }

    private void setMapMovable() {
        mapPane.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                System.out.println(keyEvent.getCode().toString());
                switch (keyEvent.getCode()) {
                    case RIGHT:
                        mapPane.setLayoutX(mapPane.getLayoutX() - Tile.WIDTH);
                        break;
                    case LEFT:
                        mapPane.setLayoutX(mapPane.getLayoutX() + Tile.WIDTH);
                        break;
                    case UP:
                        mapPane.setLayoutY(mapPane.getLayoutY() + Tile.HEIGHT / 2);
                        break;
                    case DOWN:
                        mapPane.setLayoutY(mapPane.getLayoutY() - Tile.HEIGHT / 2);
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
                }
            }
        });
    }

    private void setSelectionArea() {
        selectionArea = new Rectangle();
        selectionArea.setFill(Color.DARKGRAY);
        selectionArea.setOpacity(0.1);
        final MouseEvent[] pressedEvent = {null};
        mapPane.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                pressedEvent[0] = mouseEvent;
                selectionArea.setX(mouseEvent.getX());
                selectionArea.setY(mouseEvent.getY());
                mapPane.getChildren().add(selectionArea);
                selectionArea.toFront();
            }
        });
        mapPane.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                selectionArea.setWidth(Math.abs(mouseEvent.getX() - pressedEvent[0].getX()));
                selectionArea.setHeight(Math.abs(mouseEvent.getY() - pressedEvent[0].getY()));
                selectionArea.setX(Math.min(mouseEvent.getX(), pressedEvent[0].getX()));
                selectionArea.setY(Math.min(mouseEvent.getY(), pressedEvent[0].getY()));
            }
        });
        mapPane.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                selectionArea.setWidth(0);
                selectionArea.setHeight(0);
                mapPane.getChildren().remove(selectionArea);
            }
        });
    }

    private void setMap() {
        map = new Tile[MAP_SIZE * 2 - 1][MAP_SIZE];
        for (int i = 0; i < MAP_SIZE * 2 - 1; i++) {
            for (int j = 0; j < MAP_SIZE; j++) {
                double x = Tile.WIDTH / 2 + j * Tile.WIDTH + ((i % 2 == 0) ? 0 : Tile.WIDTH / 2);
                double y = mapPane.getPrefHeight() - Tile.HEIGHT / 2 - i * (Tile.HEIGHT / 2);
                map[i][j] = new Tile(x, y, i, j, null);
                setTile(map[i][j]);

                if (isInBorder(map[i][j])) {
                    mapPane.getChildren().add(map[i][j]);
                    firstI = Math.min(firstI, i);
                    firstJ = Math.min(firstJ, j);
                    lastI = Math.max(lastI, i);
                    lastJ = Math.max(lastJ, j);
                }
            }
        }
    }

    private void setTile(Tile tile) {
        tile.setFill(LandType.LAND.getImagePattern());
        tile.setStroke(Color.BLACK);
        tile.setStrokeWidth(0);
        tile.hoverProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
                if (t1) {
                    mapPane.getChildren().remove(tileStatusPane);
                } else {
                    tileStatus.setText(mapMenuController.showCellDetails(tile.getCell().getRow(), tile.getCell().getColumn()));
                    locateTileStatusPane(tile);
                    mapPane.getChildren().add(tileStatusPane);
                }
                tile.setOpacity(1.8 - tile.getOpacity());
            }
        });
        tile.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (matchMenuJFX.getMatchBarJFX().getSelectedBuildingImagePattern() != null) {
                    addBuildingToMap(tile.getI(), tile.getJ(), matchMenuJFX.getMatchBarJFX().getSelectedBuildingImagePattern());
                    // TODO add to cell
                } else {
                    tile.setStrokeWidth(1 - tile.getStrokeWidth());
                }
            }
        });
    }

    private void locateTileStatusPane(Tile tile) {
        double x = Math.max(tile.getX() - tileStatusPane.getPrefWidth(), mapPane.getLayoutX());
        x = Math.min(x, mapPane.getPrefWidth() - tileStatusPane.getPrefWidth());
        tileStatusPane.setLayoutX(x);
        double y = Math.max(tile.getY() - tileStatusPane.getPrefHeight(), mapPane.getLayoutY());
        y = Math.min(y, mapPane.getPrefHeight() - tileStatusPane.getPrefHeight());
        tileStatusPane.setLayoutY(y);
    }

    public Pane getMapPane() {
        return mapPane;
    }

    public void addBuildingToMap(int i, int j, ImagePattern imagePattern) {
        Rectangle rectangle = new Rectangle(Tile.WIDTH, imagePattern.getImage().getHeight() / imagePattern.getImage().getWidth() * Tile.WIDTH);
        rectangle.setLayoutX(map[i][j].getX() - rectangle.getWidth() / 2);
        rectangle.setLayoutY(map[i][j].getY() + Tile.HEIGHT / 2 - rectangle.getHeight());
        rectangle.setFill(imagePattern);
        map[i][j].setRectangle(rectangle);
        int index = mapPane.getChildren().size();
        if (i % 2 == 0 && map[i - 1][j - 1].getRectangle() != null)
            index = Math.min(index, mapPane.getChildren().indexOf(map[i - 1][j - 1].getRectangle()));
        if (i % 2 == 0 && map[i - 1][j].getRectangle() != null)
            index = Math.min(index, mapPane.getChildren().indexOf(map[i - 1][j].getRectangle()));
        if (i % 2 == 1 && map[i - 1][j].getRectangle() != null)
            index = Math.min(index, mapPane.getChildren().indexOf(map[i - 1][j].getRectangle()));
        if (i % 2 == 1 && map[i - 1][j + 1].getRectangle() != null)
            index = Math.min(index, mapPane.getChildren().indexOf(map[i - 1][j + 1].getRectangle()));
        if (index != mapPane.getChildren().size()) mapPane.getChildren().add(index, rectangle);
        else mapPane.getChildren().add(rectangle);
    }

    public void addPeopleToMap(int i, int j, ImagePattern imagePattern) {
        Rectangle rectangle = new Rectangle(Tile.WIDTH, imagePattern.getImage().getHeight() / imagePattern.getImage().getWidth() * Tile.WIDTH);
        rectangle.setLayoutX(map[i][j].getX() - rectangle.getWidth() / 2);
        rectangle.setLayoutY(map[i][j].getY() - rectangle.getHeight());
        rectangle.setFill(imagePattern);
        mapPane.getChildren().add(rectangle);
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

    private void addNewCells(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.RIGHT) || keyEvent.getCode().equals(KeyCode.LEFT)) {
            boolean isRight = keyEvent.getCode().equals(KeyCode.RIGHT);
            for (int j = isRight ? lastJ : firstJ; j != (isRight ? 200 : -1); j += isRight ? 1 : -1) {
                for (int i = firstI; i <= lastI; i++) {
                    if (isInBorder(map[i][j])) {
                        if (!mapPane.getChildren().contains(map[i][j])) addCellToPane(map[i][j]);
                    } else {
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
                    } else {
                        System.out.println("i = " + i + " j = " + j);
                        System.out.println(isInBorder(map[i][j]));
                        System.out.println("x = " + (map[i][j].getX() + mapPane.getLayoutX()) + " y = " + (map[i][j].getY() + mapPane.getLayoutY()));
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

    public int[] getCoordinates(int row, int column) {
        int i = MAP_SIZE - 1, j = 0;
        for (int k = 0; k < row - 1; k++) {
            i++;
            j += (i % 2 == 0) ? 1 : 0;
        }
        for (int k = 0; k < column - 1; k++) {
            i--;
            j += (i % 2 == 0) ? 0 : 1;
        }
        return new int[]{i, j};
    }

    public Tile getTile(int i, int j) {
        return map[i][j];
    }
}
