package view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.shape.Rectangle;

import java.io.IOException;
import java.util.Objects;

public class StartMatchJFX implements MenuJFX {
    private final AnchorPane mainMenuPane, startMatchPane;
    private final EventHandler<MouseEvent> startMatchHandler, errorHandler;
    private final ChoiceBox<Integer> roundsCountChoiceBox, playersCountChoiceBox;
    private final TextField[] usernames;
    private final Rectangle startButton, backButton;
    private final Label startButtonLabel, backButtonLabel, errorLabel;

    public StartMatchJFX(AnchorPane mainMenuPane, EventHandler<MouseEvent> startMatchHandler) throws IOException {
        this.mainMenuPane = mainMenuPane;
        this.startMatchHandler = startMatchHandler;
        this.errorHandler = new EventHandler<>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        for (TextField username : usernames) {
                            if (username.getText().isEmpty() && username.isVisible()) {
                                errorLabel.setText("empty username!");
                                break;
                            }
                        }
                    }
                };
        this.startMatchPane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/StartMatch.fxml")));
        setStartMatchPane();

        roundsCountChoiceBox = (ChoiceBox<Integer>) startMatchPane.getChildren().get(0);
        playersCountChoiceBox = (ChoiceBox<Integer>) startMatchPane.getChildren().get(1);
        setChoiceBoxProperties();

        usernames = new TextField[7];
        for (int i = 0; i < usernames.length; i++) {
            usernames[i] = (TextField) startMatchPane.getChildren().get(i + 2);
            usernames[i].setAlignment(Pos.CENTER);
            usernames[i].textProperty().addListener(new ChangeListener<>() {
                @Override
                public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                    for (TextField username : usernames) {
                        if (username.getText().isEmpty() && username.isVisible()) {
                            errorLabel.setText("empty username!");
                            return;
                        }
                    }
                    errorLabel.setText("");
                }
            });
        }

        startButton = (Rectangle) startMatchPane.getChildren().get(9);
        startButtonLabel = (Label) startMatchPane.getChildren().get(10);
        setStartButtonProperties();

        backButton = (Rectangle) startMatchPane.getChildren().get(11);
        backButtonLabel = (Label) startMatchPane.getChildren().get(12);
        setBackButtonProperties();

        errorLabel = (Label) startMatchPane.getChildren().get(13);
    }

    private void setStartMatchPane() {
        startMatchPane.setLayoutX(0);
        startMatchPane.setLayoutY(0);
        mainMenuPane.getChildren().add(startMatchPane);
        startMatchPane.setVisible(false);
    }

    private void setChoiceBoxProperties() {
        roundsCountChoiceBox.getItems().addAll(5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25);
        roundsCountChoiceBox.getSelectionModel().selectFirst();
        playersCountChoiceBox.getItems().addAll(2, 3, 4, 5, 6, 7, 8);
        playersCountChoiceBox.getSelectionModel().selectFirst();
        playersCountChoiceBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Integer>() {
            @Override
            public void changed(ObservableValue<? extends Integer> observableValue, Integer integer, Integer t1) {
                for (TextField username : usernames) {
                    username.setVisible(false);
                }
                for (int i = 0; i < playersCountChoiceBox.getValue() - 1; i++) {
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
        for (int i = 0; i < playersCountChoiceBox.getValue() - 1; i++) {
            result.append(usernames[i].getText()).append(" ");
        }
        return result.toString();
    }

    public Label getErrorLabel() {
        return errorLabel;
    }

    private void setStartButtonProperties() {
        startButton.setOnMouseClicked(errorHandler);
        startButtonLabel.setOnMouseClicked(errorHandler);
        startButton.setOnMouseClicked(startMatchHandler);
        startButtonLabel.setOnMouseClicked(startMatchHandler);
        startButton.hoverProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
                startButton.setOpacity(1.5 - startButton.getOpacity());
            }
        });
        startButtonLabel.hoverProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
                startButton.setOpacity(1.5 - startButton.getOpacity());
            }
        });
    }

    private void setBackButtonProperties() {
        EventHandler<MouseEvent> backHandler = new EventHandler<>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                popOff();
            }
        };
        backButton.setOnMouseClicked(backHandler);
        backButtonLabel.setOnMouseClicked(backHandler);
        backButton.hoverProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
                backButton.setOpacity(1.5 - backButton.getOpacity());
            }
        });
        backButtonLabel.hoverProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
                backButton.setOpacity(1.5 - backButton.getOpacity());
            }
        });
    }

    public void popOut() {
        for (Node child : mainMenuPane.getChildren()) {
            child.setVisible(false);
        }
        startMatchPane.setVisible(true);
    }

    public void popOff() {
        for (TextField username : usernames) {
            username.setText("");
        }
        roundsCountChoiceBox.getSelectionModel().selectFirst();
        playersCountChoiceBox.getSelectionModel().selectFirst();
        errorLabel.setText("");
        for (Node child : mainMenuPane.getChildren()) {
            child.setVisible(true);
        }
        startMatchPane.setVisible(false);
    }

    @Override
    public void adjust(double ratioX, double ratioY) {
        Adjust.adjustPane(startMatchPane, ratioX, ratioY);
        Adjust.adjustChoiceBox(roundsCountChoiceBox, ratioX, ratioY);
        Adjust.adjustChoiceBox(playersCountChoiceBox, ratioX, ratioY);
        for (TextField username : usernames) {
            Adjust.adjustTextField(username, ratioX, ratioY);
        }
        Adjust.adjustRectangle(startButton, ratioX, ratioY);
        Adjust.adjustLabel(startButtonLabel, ratioX, ratioY);
        Adjust.adjustRectangle(backButton, ratioX, ratioY);
        Adjust.adjustLabel(backButtonLabel, ratioX, ratioY);
        Adjust.adjustLabel(errorLabel, ratioX, ratioY);
    }

    @Override
    public void adjustWithScene() {
    }
}