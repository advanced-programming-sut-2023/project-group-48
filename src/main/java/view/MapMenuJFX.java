package view;

import controller.Controller;
import controller.MapMenuController;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;
import model.Match.Tile;

import java.util.Objects;

public class MapMenuJFX extends Application {
    private Controller controller;
    private MapMenuController mapMenuController;
    private AnchorPane viewPane;
    private Pane mapPane;
    private Tile[][] map;
    private int firstI = 199, lastI = 0, firstJ = 199, lastJ = 0;

    private Stage stage;

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        viewPane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/MapMenu.fxml")));
        mapPane = (Pane) viewPane.getChildren().get(0);
        setMapPaneMovable();
        setMap();
        Scene scene = new Scene(viewPane);
        stage.setScene(scene);
        mapPane.requestFocus();
        stage.show();
    }

    private void setMapPaneMovable() {
        mapPane.setPrefWidth(200 * Tile.WIDTH + 20);
        mapPane.setPrefHeight(200 * Tile.HEIGHT / 2 + 20);
        mapPane.setLayoutX(viewPane.getPrefWidth() / 2 - mapPane.getPrefWidth() / 2);
        mapPane.setLayoutY(viewPane.getPrefHeight() / 2 - mapPane.getPrefHeight() / 2);
        mapPane.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                System.out.println(keyEvent.getCode().toString());
                switch (keyEvent.getCode()) {
                    case RIGHT:
                        mapPane.setLayoutX(mapPane.getLayoutX() - 100);
                        break;
                    case LEFT:
                        mapPane.setLayoutX(mapPane.getLayoutX() + 100);
                        break;
                    case UP:
                        mapPane.setLayoutY(mapPane.getLayoutY() + 100);
                        break;
                    case DOWN:
                        mapPane.setLayoutY(mapPane.getLayoutY() - 100);
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
                }
                mapPane.requestFocus();
            }
        });
    }

    private void setMap() {
        ImagePattern defaultTileImage = new ImagePattern(new Image(Objects.requireNonNull(getClass().getResource("/tile/desert_tile.jpg")).toExternalForm()));
        map = new Tile[200][200];
        for (int i = 0; i < 200; i++) {
            for (int j = 0; j < 200; j++) {
                map[i][j] = new Tile(Tile.WIDTH / 2 + j * Tile.WIDTH + ((i % 2 == 0) ? 0 : Tile.WIDTH / 2),
                        mapPane.getPrefHeight() - Tile.HEIGHT / 2 - i * (Tile.HEIGHT / 2));
                map[i][j].setFill(defaultTileImage);
                int finalJ = j;
                int finalI = i;
                map[i][j].setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
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
            for (int j = isRight ? lastJ : firstJ; j != (isRight ? 200 : 0); j += isRight ? 1 : -1) {
                for (int i = 0; i < 200; i++) {
                    if (isInBorder(map[i][j])) {
                        mapPane.getChildren().add(map[i][j]);
                        System.out.println("added");
                    }
                    else {
                        if (isRight) lastJ = j;
                        else firstJ = j;
                        return;
                    }
                }
            }
        } else {
            boolean isUp = keyEvent.getCode().equals(KeyCode.UP);
            for (int i = isUp ? lastI : firstI; i != (isUp ? 200 : 0); i += isUp ? 1 : -1) {
                for (int j = 0; j < 200; j++) {
                    if (isInBorder(map[i][j])) mapPane.getChildren().add(map[i][j]);
                    else {
                        if (isUp) lastI = i;
                        else firstI = i;
                        return;
                    }
                }
            }
        }
    }

    public void removeOldCells(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.RIGHT) || keyEvent.getCode().equals(KeyCode.LEFT)) {
            boolean isRight = keyEvent.getCode().equals(KeyCode.RIGHT);
            for (int j = isRight ? firstJ : lastJ; j != (isRight ? 200 : 0); j += isRight ? 1 : -1) {
                for (int i = 0; i < 200; i++) {
                    if (!isInBorder(map[i][j])) mapPane.getChildren().remove(map[i][j]);
                    else {
                        if (isRight) firstJ = j;
                        else lastJ = j;
                        return;
                    }
                }
            }
        } else {
            boolean isUp = keyEvent.getCode().equals(KeyCode.UP);
            for (int i = isUp ? firstI : lastI; i != (isUp ? 200 : 0); i += isUp ? 1 : -1) {
                for (int j = 0; j < 200; j++) {
                    if (!isInBorder(map[i][j])) mapPane.getChildren().remove(map[i][j]);
                    else {
                        if (isUp) firstI = i;
                        else lastI = i;
                        return;
                    }
                }
            }
        }
    }

    private boolean isInBorder(Tile tile) {
        return (tile.getX() + mapPane.getLayoutX() >= -Tile.WIDTH) && (tile.getX() + mapPane.getLayoutX() - viewPane.getPrefWidth() <= Tile.WIDTH)
                && (tile.getY() + mapPane.getLayoutY() >= -Tile.HEIGHT) && (tile.getY() + mapPane.getLayoutY() - viewPane.getPrefHeight() <= Tile.HEIGHT);
    }
}