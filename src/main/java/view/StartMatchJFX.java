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
    private final AnchorPane mainMenuPane, startMatchPane;
    private final EventHandler<MouseEvent> startMatchHandler;
    private final ChoiceBox<Integer> roundsCountChoiceBox, playersCountChoiceBox;
    private final TextField[] usernames;
    private final Rectangle startButton;
    private final Label startButtonLabel, errorLabel;

    public StartMatchJFX(AnchorPane mainMenuPane, EventHandler<MouseEvent> startMatchHandler) throws IOException {
        this.mainMenuPane = mainMenuPane;
        this.startMatchHandler = startMatchHandler;
        this.startMatchPane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/StartMatch.fxml")));
        startMatchPane.setBackground(Background.fill(Color.BLUE));

        roundsCountChoiceBox = (ChoiceBox<Integer>) startMatchPane.getChildren().get(0);
        playersCountChoiceBox = (ChoiceBox<Integer>) startMatchPane.getChildren().get(1);
        setChoiceBoxProperties();

        usernames = new TextField[8];
        for (int i = 0; i < usernames.length - 1; i++) {
            usernames[i] = (TextField) startMatchPane.getChildren().get(i + 2);
            usernames[i].textProperty().addListener(new ChangeListener<>() {
                @Override
                public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                    errorLabel.setText("");
                }
            });
        }

        startButton = (Rectangle) startMatchPane.getChildren().get(9);
        startButtonLabel = (Label) startMatchPane.getChildren().get(10);
        setStartButtonProperties();

        errorLabel = (Label) startMatchPane.getChildren().get(11);
    }

    private void setChoiceBoxProperties() {
        playersCountChoiceBox.getItems().addAll(2, 3, 4, 5, 6, 7, 8);
        playersCountChoiceBox.getSelectionModel().selectFirst();
        playersCountChoiceBox.selectionModelProperty().addListener(new ChangeListener<SingleSelectionModel<Integer>>() {
            @Override
            public void changed(ObservableValue<? extends SingleSelectionModel<Integer>> observableValue, SingleSelectionModel<Integer> integerSingleSelectionModel, SingleSelectionModel<Integer> t1) {
                for (TextField username : usernames) {
                    username.setVisible(false);
                }
                for (int i = 0; i < playersCountChoiceBox.getValue(); i++) {
                    usernames[i].setVisible(true);
                }
            }
        });
    }

    public int getRoundsCount() {
        return roundsCountChoiceBox.getValue();
    }

    public String getUsernames() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < playersCountChoiceBox.getValue(); i++) {
            result.append(usernames[i].getText()).append(" ");
        }
        return result.toString();
    }

    public Label getErrorLabel() {
        return errorLabel;
    }

    private void setStartButtonProperties() {
        startButton.setOnMouseClicked(startMatchHandler);
        startButtonLabel.setOnMouseClicked(startMatchHandler);
    }
}