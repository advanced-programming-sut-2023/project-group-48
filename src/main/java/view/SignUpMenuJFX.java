package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.paint.ImagePattern;
import javafx.stage.Stage;

import java.util.Objects;

public class SignUpMenuJFX extends Application {
    private AnchorPane signUpMenuPane;
    private TextField userName, nickname, email, passwordRecoveryAnswer;
    private PasswordField password;
    private ChoiceBox passwordRecoveryQuestion;
    @Override
    public void start(Stage stage) throws Exception {
        signUpMenuPane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/SignUpMenu.fxml")));
        signUpMenuPane.setBackground(Background.fill(new ImagePattern(new Image(Objects.requireNonNull(getClass().getResource("/Backgrounds/2.jpg")).toExternalForm()))));

        Scene scene = new Scene(signUpMenuPane);
        stage.setScene(scene);
        stage.show();
    }
}