package view;

import controller.Controller;
import controller.MapMenuController;
import controller.MatchMenuController;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Buildings.BuildingType;

import java.util.Objects;

public class MatchMenuJFX extends Application {
    private Controller controller;
    private MatchMenuController matchMenuController;
    private MapMenuController mapMenuController;
    private AnchorPane viewPane;
    private MapJFX mapJFX;
    private MatchBarJFX matchBarJFX;
    private SetTextureJFX setTextureJFX;
    private Stage stage;

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        matchMenuController.setMatch();
        viewPane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/MapMenu.fxml")));
        setViewPane();
        mapJFX = new MapJFX(controller, mapMenuController, matchMenuController, this, viewPane);
        matchBarJFX = new MatchBarJFX(this, viewPane);
        setBar();
        Scene scene = new Scene(viewPane);
        stage.setScene(scene);
        mapJFX.getMapPane().requestFocus();
        mapJFX.refreshMiniMap();
        stage.show();
    }

    private void setBar() {
        matchBarJFX.getShopCircle().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    controller.enterShopMenuJFX();
                    controller.getGame().getCurrentMenuJFX().start(stage);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    private void setViewPane() {
        viewPane.setPrefWidth(Math.ceil(viewPane.getPrefWidth() / Tile.WIDTH) * Tile.WIDTH);
        viewPane.setPrefHeight(Math.ceil(viewPane.getPrefHeight() / Tile.HEIGHT) * Tile.HEIGHT);
    }

    public MapJFX getMapJFX() {
        return mapJFX;
    }

    public MatchBarJFX getMatchBarJFX() {
        return matchBarJFX;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void setMatchMenuController(MatchMenuController matchMenuController) {
        this.matchMenuController = matchMenuController;
    }

    public void setMapMenuController(MapMenuController mapMenuController) {
        this.mapMenuController = mapMenuController;
    }

    public Scene getScene() {
        return stage.getScene();
    }

    public MatchMenuController getMatchMenuController() {
        return matchMenuController;
    }
}