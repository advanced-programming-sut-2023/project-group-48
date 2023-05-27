package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.paint.ImagePattern;
import javafx.stage.Stage;

import java.util.Objects;

public class LogInMenuJFX extends Application {
    private AnchorPane logInMenuPane;
    private TextField username;
    private PasswordField password;
    private CheckBox stayLoggedIn;
    @Override
    public void start(Stage stage) throws Exception {
        logInMenuPane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/LogInMenu.fxml")));
        logInMenuPane.setBackground(Background.fill(new ImagePattern(new Image(Objects.requireNonNull(getClass().getResource("/Backgrounds/5.png")).toExternalForm()))));

        Scene scene = new Scene(logInMenuPane);
        stage.setScene(scene);
        stage.show();
    }
}
