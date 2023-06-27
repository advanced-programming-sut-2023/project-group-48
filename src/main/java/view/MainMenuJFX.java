package view;

import controller.Controller;
import controller.MainMenuController;
import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class MainMenuJFX extends Application {
    private Controller controller;
    private MainMenuController mainMenuController;
    private AnchorPane mainMenuPane;
    private Rectangle startMatch, profileMenu, logOut, exit;
    private Text startMatchText, profileMenuText, logOutText, exitText;
    private Stage stage;

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        System.out.println(0);
        mainMenuPane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/MainMenu.fxml")));
        mainMenuPane.setBackground(new Background(new BackgroundImage(new Image(getClass().getResource("/backgrounds/3.png").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,  BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
//        mainMenuPane.setBackground(Background.fill(new ImagePattern(new Image(Objects.requireNonNull(getClass().getResource("/backgrounds/3.png")).toExternalForm()))));

        startMatch = (Rectangle) mainMenuPane.getChildren().get(0);
        startMatchText = (Text) mainMenuPane.getChildren().get(1);
        setStartMatchProperties();

        profileMenu = (Rectangle) mainMenuPane.getChildren().get(2);
        profileMenuText = (Text) mainMenuPane.getChildren().get(3);
        setProfileMenuProperties();

        logOut = (Rectangle) mainMenuPane.getChildren().get(4);
        logOutText = (Text) mainMenuPane.getChildren().get(5);
        setLogOutProperties();

        exit = (Rectangle) mainMenuPane.getChildren().get(6);
        exitText = (Text) mainMenuPane.getChildren().get(7);
        setExitProperties();


        Scene scene = new Scene(mainMenuPane);
        stage.setScene(scene);
        stage.show();
        System.out.println(1);
    }

    private void setStartMatchProperties() {
        EventHandler startMatchHandler = new EventHandler() {
            @Override
            public void handle(Event event) {
                // TODO
            }
        };
        startMatch.hoverProperty().addListener((event) -> {
            startMatch.setOpacity(1.5 - startMatch.getOpacity());
        });
        startMatchText.hoverProperty().addListener((event) -> {
            startMatch.setOpacity(1.5 - startMatch.getOpacity());
        });
        startMatch.setOnMouseClicked(startMatchHandler);
        startMatchText.setOnMouseClicked(startMatchHandler);
    }

    private void setProfileMenuProperties() {
        EventHandler profileMenuHandler = new EventHandler() {
            @Override
            public void handle(Event event) {
                // TODO
            }
        };
        profileMenu.hoverProperty().addListener((event) -> {
            profileMenu.setOpacity(1.5 - profileMenu.getOpacity());
        });
        profileMenuText.hoverProperty().addListener((event) -> {
            profileMenu.setOpacity(1.5 - profileMenu.getOpacity());
        });
        profileMenu.setOnMouseClicked(profileMenuHandler);
        profileMenuText.setOnMouseClicked(profileMenuHandler);
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
        logOutText.hoverProperty().addListener((event) -> {
            logOut.setOpacity(1.5 - logOut.getOpacity());
        });
        logOut.setOnMouseClicked(logoutHandler);
        logOutText.setOnMouseClicked(logoutHandler);
    }

    private void setExitProperties() {
        EventHandler exitHandler = new EventHandler() {
            @Override
            public void handle(Event event) {
                try {
                    controller.exit();
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
        exitText.hoverProperty().addListener((event) -> {
            exit.setOpacity(1.5 - exit.getOpacity());
        });
        exit.setOnMouseClicked(exitHandler);
        exitText.setOnMouseClicked(exitHandler);
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
