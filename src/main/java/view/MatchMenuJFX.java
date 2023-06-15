package view;

import controller.Controller;
import controller.MapMenuController;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import model.Match.Tile;

import java.util.Objects;

public class MatchMenuJFX extends Application {
    private Controller controller;
    private MapMenuController mapMenuController;
    private AnchorPane viewPane;
    private MapJFX mapJFX;

    private Stage stage;

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        viewPane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/MapMenu.fxml")));
        setViePane();
        mapJFX = new MapJFX(viewPane);
        Scene scene = new Scene(viewPane);
        stage.setScene(scene);
        mapJFX.getMapPane().requestFocus();
        stage.show();
    }

    private void setViePane() {
        viewPane.setPrefWidth(Math.ceil(viewPane.getPrefWidth() / Tile.WIDTH) * Tile.WIDTH);
        viewPane.setPrefHeight(Math.ceil(viewPane.getPrefHeight() / Tile.HEIGHT) * Tile.HEIGHT);
    }
}