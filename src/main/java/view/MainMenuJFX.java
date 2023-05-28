package view;

import controller.Controller;
import controller.MainMenuController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Objects;

public class MainMenuJFX extends Application {
    private Controller controller;
    private MainMenuController mainMenuController;
    private AnchorPane mainMenuPane;
    private Rectangle startMatch, profileMenu, logout, exit;
    private Text startMatchText, profileMenuText, logoutText, exitText;

    @Override
    public void start(Stage stage) throws Exception {
        mainMenuPane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/MainMenu.fxml")));
        mainMenuPane.setBackground(Background.fill(new ImagePattern(new Image(Objects.requireNonNull(getClass().getResource("/backgrounds/3.png")).toExternalForm()))));

        startMatch = (Rectangle) mainMenuPane.getChildren().get(0);
        startMatch.hoverProperty().addListener((event) -> {
            startMatch.setOpacity(1.5 - startMatch.getOpacity());
        });
        startMatch.setOnMouseClicked((event) -> {
            try {
                // TODO: 7/2/2021
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        startMatchText = (Text) mainMenuPane.getChildren().get(1);
        startMatchText.hoverProperty().addListener((event) -> {
            startMatch.setOpacity(1.5 - startMatch.getOpacity());
        });

        profileMenu = (Rectangle) mainMenuPane.getChildren().get(2);
        profileMenu.hoverProperty().addListener((event) -> {
            profileMenu.setOpacity(1.5 - profileMenu.getOpacity());
        });
        profileMenu.setOnMouseClicked((event) -> {
            try {
                // TODO: 7/2/2021
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        profileMenuText = (Text) mainMenuPane.getChildren().get(3);
        profileMenuText.hoverProperty().addListener((event) -> {
            profileMenu.setOpacity(1.5 - profileMenu.getOpacity());
        });

        logout = (Rectangle) mainMenuPane.getChildren().get(4);
        logout.hoverProperty().addListener((event) -> {
            logout.setOpacity(1.5 - logout.getOpacity());
        });
        logout.setOnMouseClicked((event) -> {
            try {
                // TODO: 7/2/2021
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        logoutText = (Text) mainMenuPane.getChildren().get(5);
        logoutText.hoverProperty().addListener((event) -> {
            logout.setOpacity(1.5 - logout.getOpacity());
        });

        exit = (Rectangle) mainMenuPane.getChildren().get(6);
        exit.hoverProperty().addListener((event) -> {
            exit.setOpacity(1.5 - exit.getOpacity());
        });
        exit.setOnMouseClicked((event) -> {
            try {
                new SignUpMenuJFX().start(stage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        exitText = (Text) mainMenuPane.getChildren().get(7);
        exitText.hoverProperty().addListener((event) -> {
            exit.setOpacity(1.5 - exit.getOpacity());
        });
        exitText.setOnMouseClicked((event) -> {
            try {
                new SignUpMenuJFX().start(stage);
            } catch (Exception e) {

            }
        });


        Scene scene = new Scene(mainMenuPane);
        stage.setScene(scene);
        stage.show();

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
