package view;

import client.Client;
import controller.Controller;
import controller.MainMenuController;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import server.Server;

import java.util.Objects;

public class MainMenuJFX extends Application implements MenuJFX {
    private Controller controller;
    private MainMenuController mainMenuController;
    private AnchorPane mainMenuPane;
    private Rectangle startMatch, profileMenu, logOut, exit, online, asServer;
    private Label startMatchLabel, profileMenuLabel, logOutLabel, exitLabel, onlineLabel, asServerLabel;
    private StartMatchJFX startMatchJFX;
    private Stage stage;

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        mainMenuPane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/MainMenu.fxml")));
        mainMenuPane.setBackground(new Background(new BackgroundImage(new Image(getClass().getResource("/backgrounds/3.png").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));

        startMatch = (Rectangle) mainMenuPane.getChildren().get(0);
        startMatchLabel = (Label) mainMenuPane.getChildren().get(1);
        setStartMatchProperties();

        profileMenu = (Rectangle) mainMenuPane.getChildren().get(2);
        profileMenuLabel = (Label) mainMenuPane.getChildren().get(3);
        setProfileMenuProperties();

        logOut = (Rectangle) mainMenuPane.getChildren().get(4);
        logOutLabel = (Label) mainMenuPane.getChildren().get(5);
        setLogOutProperties();

        exit = (Rectangle) mainMenuPane.getChildren().get(6);
        exitLabel = (Label) mainMenuPane.getChildren().get(7);
        setExitProperties();

        online = (Rectangle) mainMenuPane.getChildren().get(8);
        onlineLabel = (Label) mainMenuPane.getChildren().get(9);
        setOnlineProperties();

        asServer = (Rectangle) mainMenuPane.getChildren().get(10);
        asServerLabel = (Label) mainMenuPane.getChildren().get(11);
        setAsServerProperties();
        startMatchJFX = new StartMatchJFX(mainMenuPane, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                String result = mainMenuController.startMatch(startMatchJFX.getRoundsCount() , 0,
                        controller.getGame().getCurrentUser().getUsername() + " " +startMatchJFX.getUsernames());
                System.out.println(result);
                if (result.contains("match started!")) {
                    startMatchJFX.popOff();
                    try {
                        controller.enterMatchMenuJFX();
                        stop();
                        controller.getGame().getCurrentMenuJFX().start(stage);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    startMatchJFX.getErrorLabel().setText(result);
                }
            }
        });

        adjustWithScene();
        Scene scene = new Scene(mainMenuPane);
        stage.setScene(scene);
        scene.heightProperty().addListener((observable, oldValue, newValue) -> {
            adjustWithScene();
        });
        scene.widthProperty().addListener((observable, oldValue, newValue) -> {
            adjustWithScene();
        });
        stage.show();
    }

    private void setAsServerProperties() {
        EventHandler asServerHandler = new EventHandler() {
            @Override
            public void handle(Event event) {
                Server.setServer(Controller.Port);
            }
        };
        asServer.hoverProperty().addListener((event) -> {
            asServer.setOpacity(1.5 - asServer.getOpacity());
        });
        asServerLabel.hoverProperty().addListener((event) -> {
            asServer.setOpacity(1.5 - asServer.getOpacity());
        });
        asServer.setOnMouseClicked(asServerHandler);
        asServerLabel.setOnMouseClicked(asServerHandler);
    }

    private void setOnlineProperties() {
        EventHandler onlineHandler = new EventHandler() {
            @Override
            public void handle(Event event) {
                try {
                    Client.controller = controller;
                    controller.enterOnlineMenuJFX();
                    stop();
                    controller.getGame().getCurrentMenuJFX().start(stage);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        };
        online.hoverProperty().addListener((event) -> {
            online.setOpacity(1.5 - online.getOpacity());
        });
        onlineLabel.hoverProperty().addListener((event) -> {
            online.setOpacity(1.5 - online.getOpacity());
        });
        online.setOnMouseClicked(onlineHandler);
        onlineLabel.setOnMouseClicked(onlineHandler);
    }

    @Override
    public void adjust(double ratioX, double ratioY) {
        Adjust.adjustPane(mainMenuPane, ratioX, ratioY);
        Adjust.adjustRectangle(startMatch, ratioX, ratioY);
        Adjust.adjustLabel(startMatchLabel, ratioX, ratioY);
        Adjust.adjustRectangle(profileMenu, ratioX, ratioY);
        Adjust.adjustLabel(profileMenuLabel, ratioX, ratioY);
        Adjust.adjustRectangle(logOut, ratioX, ratioY);
        Adjust.adjustLabel(logOutLabel, ratioX, ratioY);
        Adjust.adjustRectangle(exit, ratioX, ratioY);
        Adjust.adjustLabel(exitLabel, ratioX, ratioY);
        Adjust.adjustRectangle(online, ratioX, ratioY);
        Adjust.adjustLabel(onlineLabel, ratioX, ratioY);
        startMatchJFX.adjust(ratioX, ratioY);
    }

    @Override
    public void adjustWithScene() {
        adjust(stage.getScene().getWidth() / mainMenuPane.getPrefWidth(), stage.getScene().getHeight() / mainMenuPane.getPrefHeight());
    }

    private void setStartMatchProperties() {
        EventHandler startMatchHandler = new EventHandler() {
            @Override
            public void handle(Event event) {
                startMatchJFX.popOut();
            }
        };
        startMatch.hoverProperty().addListener((event) -> {
            startMatch.setOpacity(1.5 - startMatch.getOpacity());
        });
        startMatchLabel.hoverProperty().addListener((event) -> {
            startMatch.setOpacity(1.5 - startMatch.getOpacity());
        });
        startMatch.setOnMouseClicked(startMatchHandler);
        startMatchLabel.setOnMouseClicked(startMatchHandler);
    }

    private void setProfileMenuProperties() {
        EventHandler profileMenuHandler = new EventHandler() {
            @Override
            public void handle(Event event) {
                try {
                    controller.enterProfileMenuJFX();
                    stop();
                    controller.getGame().getCurrentMenuJFX().start(stage);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        };
        profileMenu.hoverProperty().addListener((event) -> {
            profileMenu.setOpacity(1.5 - profileMenu.getOpacity());
        });
        profileMenuLabel.hoverProperty().addListener((event) -> {
            profileMenu.setOpacity(1.5 - profileMenu.getOpacity());
        });
        profileMenu.setOnMouseClicked(profileMenuHandler);
        profileMenuLabel.setOnMouseClicked(profileMenuHandler);
    }

    private void setLogOutProperties() {
        EventHandler logoutHandler = new EventHandler() {
            @Override
            public void handle(Event event) {
                try {
                    controller.logOutJFX();
                    stop();
                    controller.getGame().getCurrentMenuJFX().start(stage);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        };
        logOut.hoverProperty().addListener((event) -> {
            logOut.setOpacity(1.5 - logOut.getOpacity());
        });
        logOutLabel.hoverProperty().addListener((event) -> {
            logOut.setOpacity(1.5 - logOut.getOpacity());
        });
        logOut.setOnMouseClicked(logoutHandler);
        logOutLabel.setOnMouseClicked(logoutHandler);
    }

    private void setExitProperties() {
        EventHandler exitHandler = new EventHandler() {
            @Override
            public void handle(Event event) {
                try {
                    controller.exitJFX();
                    stop();
                    stage.close();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        };
        exit.hoverProperty().addListener((event) -> {
            exit.setOpacity(1.5 - exit.getOpacity());
        });
        exitLabel.hoverProperty().addListener((event) -> {
            exit.setOpacity(1.5 - exit.getOpacity());
        });
        exit.setOnMouseClicked(exitHandler);
        exitLabel.setOnMouseClicked(exitHandler);
    }

    public Controller getController() {
        return controller;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public MainMenuController getMainMenuController() {
        return mainMenuController;
    }

    public void setMainMenuController(MainMenuController mainMenuController) {
        this.mainMenuController = mainMenuController;
    }
}
