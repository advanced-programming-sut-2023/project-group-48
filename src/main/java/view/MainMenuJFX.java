package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.paint.ImagePattern;
import javafx.stage.Stage;

import java.util.Objects;

public class MainMenuJFX extends Application {
    private AnchorPane mainMenuPane;
    @Override
    public void start(Stage stage) throws Exception {
        mainMenuPane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/MainMenu.fxml")));
        mainMenuPane.setBackground(Background.fill(new ImagePattern(new Image(Objects.requireNonNull(getClass().getResource("/Backgrounds/3.png")).toExternalForm()))));

        Scene scene = new Scene(mainMenuPane);
        stage.setScene(scene);
        stage.show();

    }
}
