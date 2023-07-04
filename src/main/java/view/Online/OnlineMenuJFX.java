package view.Online;

import controller.Controller;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import view.MenuJFX;

import java.util.Objects;

public class OnlineMenuJFX extends Application implements MenuJFX {
    private Controller controller;
    private AnchorPane onlineMenuPane;
    private Rectangle chatButton, mapButton, friendButton, scoreboardButton, onlineGameButton, exitButton;
    private Label chatLabel, mapLabel, friendLabel, scoreboardLabel, onlineGameLabel, exitLabel;
    private Stage stage;

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        onlineMenuPane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/OnlineMenu.fxml")));
        onlineMenuPane.setBackground(Background.fill(Paint.valueOf(getClass().getResource("/backgrounds/3.png").toExternalForm())));

        chatButton = (Rectangle) onlineMenuPane.getChildren().get(0);
        chatLabel = (Label) onlineMenuPane.getChildren().get(1);
        setChatProperties();

        mapButton = (Rectangle) onlineMenuPane.getChildren().get(2);
        mapLabel = (Label) onlineMenuPane.getChildren().get(3);
        setMapProperties();

        friendButton = (Rectangle) onlineMenuPane.getChildren().get(4);
        friendLabel = (Label) onlineMenuPane.getChildren().get(5);
        setFriendProperties();

        scoreboardButton = (Rectangle) onlineMenuPane.getChildren().get(6);
        scoreboardLabel = (Label) onlineMenuPane.getChildren().get(7);
        setScoreboardProperties();

        onlineGameButton = (Rectangle) onlineMenuPane.getChildren().get(8);
        onlineGameLabel = (Label) onlineMenuPane.getChildren().get(9);
        setOnlineGameProperties();

        exitButton = (Rectangle) onlineMenuPane.getChildren().get(10);
        exitLabel = (Label) onlineMenuPane.getChildren().get(11);
        setExitProperties();

        adjustWithScene();
        Scene scene = new Scene(onlineMenuPane);
        stage.setScene(scene);
        scene.heightProperty().addListener((observable, oldValue, newValue) -> {
            adjustWithScene();
        });
        scene.widthProperty().addListener((observable, oldValue, newValue) -> {
            adjustWithScene();
        });
        stage.show();
    }

    private void setChatProperties() {
        EventHandler<MouseEvent> profileMenuHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    controller.enterOnlineChatMenuJFX();
                    stop();
                    controller.getGame().getCurrentMenuJFX().start(stage);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        };
        chatButton.hoverProperty().addListener((event) -> {
            chatButton.setOpacity(1.5 - chatButton.getOpacity());
        });
        chatLabel.hoverProperty().addListener((event) -> {
            chatButton.setOpacity(1.5 - chatButton.getOpacity());
        });
        chatButton.setOnMouseClicked(profileMenuHandler);
        chatLabel.setOnMouseClicked(profileMenuHandler);
    }

    private void setMapProperties() {
        EventHandler<MouseEvent> profileMenuHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    controller.enterOnlineMapMenuJFX();
                    stop();
                    controller.getGame().getCurrentMenuJFX().start(stage);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        };
        mapButton.hoverProperty().addListener((event) -> {
            mapButton.setOpacity(1.5 - mapButton.getOpacity());
        });
        mapLabel.hoverProperty().addListener((event) -> {
            mapButton.setOpacity(1.5 - mapButton.getOpacity());
        });
        mapButton.setOnMouseClicked(profileMenuHandler);
        mapLabel.setOnMouseClicked(profileMenuHandler);
    }

    private void setFriendProperties() {
        EventHandler<MouseEvent> profileMenuHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    controller.enterOnlineFriendMenuJFX();
                    stop();
                    controller.getGame().getCurrentMenuJFX().start(stage);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        };
        friendButton.hoverProperty().addListener((event) -> {
            friendButton.setOpacity(1.5 - friendButton.getOpacity());
        });
        friendLabel.hoverProperty().addListener((event) -> {
            friendButton.setOpacity(1.5 - friendButton.getOpacity());
        });
        friendButton.setOnMouseClicked(profileMenuHandler);
        friendLabel.setOnMouseClicked(profileMenuHandler);
    }

    private void setScoreboardProperties() {
        EventHandler<MouseEvent> profileMenuHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    controller.enterOnlineScoreBoardJFX();
                    stop();
                    controller.getGame().getCurrentMenuJFX().start(stage);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        };
        scoreboardButton.hoverProperty().addListener((event) -> {
            scoreboardButton.setOpacity(1.5 - scoreboardButton.getOpacity());
        });
        scoreboardLabel.hoverProperty().addListener((event) -> {
            scoreboardButton.setOpacity(1.5 - scoreboardButton.getOpacity());
        });
        scoreboardButton.setOnMouseClicked(profileMenuHandler);
        scoreboardLabel.setOnMouseClicked(profileMenuHandler);
    }

    private void setOnlineGameProperties() {
        EventHandler<MouseEvent> profileMenuHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        };
        onlineGameButton.hoverProperty().addListener((event) -> {
            onlineGameButton.setOpacity(1.5 - onlineGameButton.getOpacity());
        });
        onlineGameLabel.hoverProperty().addListener((event) -> {
            onlineGameButton.setOpacity(1.5 - onlineGameButton.getOpacity());
        });
        onlineGameButton.setOnMouseClicked(profileMenuHandler);
        onlineGameLabel.setOnMouseClicked(profileMenuHandler);
    }

    private void setExitProperties() {
        EventHandler<MouseEvent> profileMenuHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    controller.exitJFX();
                    stop();
                    controller.getGame().getCurrentMenuJFX().start(stage);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        };
        exitButton.hoverProperty().addListener((event) -> {
            exitButton.setOpacity(1.5 - exitButton.getOpacity());
        });
        exitLabel.hoverProperty().addListener((event) -> {
            exitButton.setOpacity(1.5 - exitButton.getOpacity());
        });
        exitButton.setOnMouseClicked(profileMenuHandler);
        exitLabel.setOnMouseClicked(profileMenuHandler);

    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void adjust(double ratioX, double ratioY) {

    }


    @Override
    public void adjustWithScene() {

    }
}