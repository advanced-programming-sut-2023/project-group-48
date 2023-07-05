package view.Online;

import controller.Controller;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import model.MapMethods;
import model.SavableMap;
import view.Adjust;
import view.MenuJFX;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class OnlineMapMenuJFX extends Application implements MenuJFX {
    private Controller controller;
    private AnchorPane mapMenuPane, mapsContent;
    private ScrollPane mapsScrollPane;
    private Button backButton, useMap;
    private SavableMap selectedMap;
    private Stage stage;
    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        mapMenuPane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/OnlineMap.fxml")));
        mapMenuPane.setBackground(Background.fill(new ImagePattern(new Image(Objects.requireNonNull(getClass().getResource("/backgrounds/9.jpg")).toExternalForm()))));

        mapsScrollPane = (ScrollPane) mapMenuPane.getChildren().get(0);
        mapsContent = (AnchorPane) mapsScrollPane.getContent();
        setMapsContent();

        backButton = (Button) mapMenuPane.getChildren().get(1);
        setBackButton();

        useMap = (Button) mapMenuPane.getChildren().get(2);
        setUseMapButton();

        Scene scene = new Scene(mapMenuPane);
        stage.setScene(scene);
        stage.show();
    }

    private void setUseMapButton() {
        useMap.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                controller.setUsingMap(selectedMap);
            }
        });
    }

    private void setMapsContent() {
        ArrayList<SavableMap> maps = MapMethods.loadMaps();
        mapsContent.setPrefHeight(maps.size());
        for (SavableMap map : maps) {
            Rectangle rectangle = new Rectangle(0, maps.indexOf(map) * 100, 700, 50);
            Label label = new Label();
            label.setPrefWidth(100);
            label.setPrefHeight(50);
            label.setLayoutX(rectangle.getWidth() / 2 - label.getPrefWidth() / 2);
            label.setLayoutY(0);
            label.setText(map.name);
            label.setTextAlignment(TextAlignment.CENTER);
            rectangle.setFill(map.sentFromOthers ? Color.BLUE : Color.YELLOW);
            rectangle.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    selectedMap = maps.get(mapsContent.getChildren().indexOf(rectangle) / 2);
                }
            });
            label.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    selectedMap = maps.get(mapsContent.getChildren().indexOf(label) - 1 / 2);
                }
            });
            mapsContent.getChildren().addAll(rectangle, label);
        }
    }

    private void setBackButton() {
        backButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    controller.enterOnlineMenuJFX();
                    stop();
                    controller.getGame().getCurrentMenuJFX().start(stage);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void adjust(double ratioX, double ratioY) {
        Adjust.adjustPane(mapMenuPane, ratioX, ratioY);
        Adjust.adjustButton(backButton, ratioX, ratioY);
        Adjust.adjustButton(useMap, ratioX, ratioY);
        Adjust.adjustScrollPane(mapsScrollPane, ratioX, ratioY);
        Adjust.adjustPane(mapsContent, ratioX, ratioY);
        for (Node child : mapsContent.getChildren()) {
            if (child instanceof Rectangle) {
                Adjust.adjustRectangle((Rectangle) child, ratioX, ratioY);
            } else if (child instanceof Label) {
                Adjust.adjustLabel((Label) child, ratioX, ratioY);
            }
        }
    }

    @Override
    public void adjustWithScene() {
        adjust(stage.getScene().getWidth() / mapMenuPane.getPrefWidth(),
                stage.getScene().getHeight() / mapMenuPane.getPrefHeight());
    }

    public void refresh() {
        setMapsContent();
    }
}
