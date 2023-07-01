package view;

import controller.MapMenuController;
import controller.MatchMenuController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import model.Match.LandType;

import java.io.*;
import java.util.ArrayList;
import java.util.Objects;

public class SetTextureJFX {
    private final MatchMenuController matchMenuController;
    private final Pane mapPane;
    private final ScrollPane scrollPane;
    private final AnchorPane viewPane, anchorPane;
    private ArrayList<Tile> selectedTiles;

    public SetTextureJFX(MatchMenuController matchMenuController, AnchorPane viewPane, Pane mapPane) throws IOException {
        this.matchMenuController = matchMenuController;
        this.viewPane = viewPane;
        this.mapPane = mapPane;
        this.scrollPane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/SetTexture.fxml")));
        this.anchorPane = (AnchorPane) scrollPane.getContent();
        setScrollPane();
    }

    private void setScrollPane() {
        anchorPane.setBackground(Background.fill(Color.SEAGREEN));
        scrollPane.setBackground(Background.fill(Color.SEAGREEN));
        Tile[] sampleLandTypes = new Tile[LandType.getAllLandTypes().length];
        scrollPane.setPrefHeight(Tile.HEIGHT * 2);
        anchorPane.setPrefHeight(Tile.HEIGHT * 2);
        anchorPane.setPrefWidth(sampleLandTypes.length * Tile.WIDTH * 1.5 + Tile.WIDTH / 2);
        for (int i = 0; i < sampleLandTypes.length; i++) {
            sampleLandTypes[i] = new Tile((i * 1.5 + 1) * Tile.WIDTH, Tile.HEIGHT);
            sampleLandTypes[i].setFill(LandType.getAllLandTypes()[i].getImagePattern());
            sampleLandTypes[i].setStroke(Color.BLACK);
            anchorPane.getChildren().add(sampleLandTypes[i]);
            int finalI = i;
            sampleLandTypes[i].hoverProperty().addListener(new ChangeListener<Boolean>() {
                @Override
                public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
                    sampleLandTypes[finalI].setStrokeWidth(1 - sampleLandTypes[finalI].getStrokeWidth());
                }
            });
            sampleLandTypes[i].setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    for (Tile selectedTile : selectedTiles) {
                        matchMenuController.setTexture(selectedTile.getCell().getRow(), selectedTile.getCell().getColumn(),
                                LandType.getAllLandTypes()[finalI].toString());
                        selectedTile.setFill(sampleLandTypes[finalI].getFill());
                    }
                    popOffSetTexture();
                }
            });
        }
    }

    public void setSelectedTiles(ArrayList<Tile> selectedTiles) {
        this.selectedTiles = selectedTiles;
    }

    public void popOutSetTexture() {
        scrollPane.setLayoutX(viewPane.getPrefWidth() / 2 - mapPane.getLayoutX() - scrollPane.getPrefWidth() / 2);
        scrollPane.setLayoutY(viewPane.getPrefHeight() / 2 - mapPane.getLayoutY() - scrollPane.getPrefHeight() / 2);
        mapPane.getChildren().add(scrollPane);
        scrollPane.setVisible(true);
    }

    private void popOffSetTexture() {
        mapPane.getChildren().remove(scrollPane);
        mapPane.requestFocus();
    }
}
