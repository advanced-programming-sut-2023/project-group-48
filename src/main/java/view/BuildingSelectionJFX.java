package view;

import controller.Controller;
import controller.MatchMenuController;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import model.People.People;
import model.People.PeopleType;
import model.People.Troop;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class BuildingSelectionJFX {
    private final Controller controller;
    private final MatchMenuController matchMenuController;
    private final MapJFX mapJFX;
    private BuildingShape selectedBuilding;
    private Pane buildingSelectionPane;
    private ChoiceBox<String> unitTypeChoiceBox;
    private ChoiceBox<Integer> unitCountChoiceBox;
    private Button createUnitButton, repairBuildingButton;
    private Label errorLabel;

    public BuildingSelectionJFX(Controller controller, MatchMenuController matchMenuController, MapJFX mapJFX) throws IOException {
        this.controller = controller;
        this.matchMenuController = matchMenuController;
        this.mapJFX = mapJFX;
        setBuildingSelectionPane();
        setUnitNameTextField();
        setUnitCountChoiceBox();
        setCreateUnitButton();
        setRepairBuilding();
        setErrorLabel();
    }

    private void setBuildingSelectionPane() throws IOException {
        buildingSelectionPane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/BuildingSelection.fxml")));
        buildingSelectionPane.setBackground(Background.fill(Color.DARKGOLDENROD));
    }

    private void setUnitNameTextField() {
        unitTypeChoiceBox = (ChoiceBox<String>) buildingSelectionPane.getChildren().get(0);
        unitTypeChoiceBox.getItems().addAll(PeopleType.getTroopsNames());
    }

    private void setUnitCountChoiceBox() {
        unitCountChoiceBox = (ChoiceBox<Integer>) buildingSelectionPane.getChildren().get(1);
        for (int i = 0; i < 50; i++) unitCountChoiceBox.getItems().add(i + 1);
        unitCountChoiceBox.getSelectionModel().selectFirst();
    }

    private void setCreateUnitButton() {
        createUnitButton = (Button) buildingSelectionPane.getChildren().get(2);
        createUnitButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                String result = matchMenuController.createUnit(unitTypeChoiceBox.getValue(), unitCountChoiceBox.getValue());
                if (!result.contains("successfully")) errorLabel.setText(result);
                else {
                    int[] coordinate = mapJFX.getCoordinates(selectedBuilding.getBuilding().getRow(), selectedBuilding.getBuilding().getColumn() + 1);
                    ArrayList<People> people = mapJFX.getTile(coordinate[0], coordinate[1]).getCell().getPeople();
                    for (int i = 0; i < unitCountChoiceBox.getValue(); i++) {
                        People peopleToAdd = people.get(people.size() - 1 - i);
                        mapJFX.addPeopleToMap(coordinate[0], coordinate[1], PeopleType.getStandingImage(peopleToAdd.getType(),
                                controller.getGame().getCurrentMatch().getGovernances().indexOf(peopleToAdd.getGovernance())), peopleToAdd);
                    }
                }
            }
        });
    }

    private void setRepairBuilding() {
        repairBuildingButton = (Button) buildingSelectionPane.getChildren().get(3);
        repairBuildingButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                String result = matchMenuController.repair();
                if (!result.contains("successfully")) errorLabel.setText(result);
            }
        });
    }

    private void setErrorLabel() {
        errorLabel = (Label) buildingSelectionPane.getChildren().get(4);
    }

    public void adjust(double ratioX, double ratioY) {
        Adjust.adjustPane(buildingSelectionPane, ratioX, ratioY);
        Adjust.adjustChoiceBox(unitTypeChoiceBox, ratioX, ratioY);
        Adjust.adjustChoiceBox(unitCountChoiceBox, ratioX, ratioY);
        Adjust.adjustButton(createUnitButton, ratioX, ratioY);
        Adjust.adjustButton(repairBuildingButton, ratioX, ratioY);
        Adjust.adjustLabel(errorLabel, ratioX, ratioY);
    }

    public void popOut(double x, double y, BuildingShape selectedBuilding) {
        this.selectedBuilding = selectedBuilding;
        buildingSelectionPane.setLayoutX(x - buildingSelectionPane.getPrefWidth());
        buildingSelectionPane.setLayoutY(y - buildingSelectionPane.getPrefHeight());
        mapJFX.getMapPane().getChildren().add(buildingSelectionPane);
    }

    public void popOff() {
        mapJFX.getMapPane().getChildren().remove(buildingSelectionPane);
    }
}
