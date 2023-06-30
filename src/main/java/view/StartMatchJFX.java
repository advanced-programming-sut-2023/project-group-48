package view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.io.IOException;
import java.util.Objects;

public class StartMatchJFX {
    private final AnchorPane startMatchPane;
    private final ChoiceBox<Integer> choiceBox;
    private final TextField[] usernames;
    private final Rectangle startButton;
    private final Label startButtonLabel;

    public StartMatchJFX() throws IOException {
        this.startMatchPane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/TileStatus.fxml")));
        startMatchPane.setBackground(Background.fill(Color.BLUE));
        choiceBox = (ChoiceBox<Integer>) startMatchPane.getChildren().get(0);
        setChoiceBoxProperties();
        usernames = new TextField[8];
        for (int i = 0; i < usernames.length; i++) {
            usernames[i] = (TextField) startMatchPane.getChildren().get(i + 1);
        }
        startButton = (Rectangle) startMatchPane.getChildren().get(9);
        startButtonLabel = (Label) startMatchPane.getChildren().get(10);
        setStartButtonProperties();
    }

    private void setChoiceBoxProperties() {
        choiceBox.getItems().addAll(2, 3, 4, 5, 6, 7, 8);
        choiceBox.getSelectionModel().selectFirst();
        choiceBox.selectionModelProperty().addListener(new ChangeListener<SingleSelectionModel<Integer>>() {
            @Override
            public void changed(ObservableValue<? extends SingleSelectionModel<Integer>> observableValue, SingleSelectionModel<Integer> integerSingleSelectionModel, SingleSelectionModel<Integer> t1) {
                for (TextField username : usernames) {
                    username.setVisible(false);
                }
                for (int i = 0; i < choiceBox.getSelectionModel().getSelectedItem(); i++) {
                    usernames[i].setVisible(true);
                }
            }
        });
    }

    private void setStartButtonProperties() {
        EventHandler<MouseEvent> startMatchHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                // TODO
            }
        };
        startButton.setOnMouseClicked(startMatchHandler);
        startButtonLabel.setOnMouseClicked(startMatchHandler);
    }

}