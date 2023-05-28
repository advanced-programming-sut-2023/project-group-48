package view;

import controller.Controller;
import controller.LoginMenuController;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import model.Game;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.Objects;
import java.util.Random;

public class LogInMenuJFX extends Application {
    private Controller controller;
    private LoginMenuController loginMenuController;
    private AnchorPane logInMenuPane;
    private TextField username, captchaAnswer;
    private PasswordField password;
    private CheckBox showPassword, stayLoggedIn;
    private Label error, loginButtonText, captchaError;
    private Hyperlink forgotPasswordLink, signUpLink;
    private Rectangle loginButton, captchaPicture;
    private Circle refreshCaptchaButton;
    private Button captchaAnswerButton;
    private Pane captchaPane;
    private int wrongPasswordCount;
    private Stage stage;

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        logInMenuPane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/LogInMenu.fxml")));
        logInMenuPane.setBackground(Background.fill(new ImagePattern(new Image(Objects.requireNonNull(getClass().getResource("/backgrounds/5.png")).toExternalForm()))));

        loginButton = (Rectangle) logInMenuPane.getChildren().get(0);
        loginButtonText = (Label) logInMenuPane.getChildren().get(1);
        setLogInButtonProperties();

        username = (TextField) logInMenuPane.getChildren().get(3);

        password = (PasswordField) logInMenuPane.getChildren().get(4);
        showPassword = (CheckBox) logInMenuPane.getChildren().get(5);
        setPasswordProperties();
        setUsernameProperties();

        error = (Label) logInMenuPane.getChildren().get(6);
        setErrorProperties();

        stayLoggedIn = (CheckBox) logInMenuPane.getChildren().get(7);
        setStayLoggedInProperties();

        forgotPasswordLink = (Hyperlink) logInMenuPane.getChildren().get(8);
        setForgotPasswordLinkProperties();

        signUpLink = (Hyperlink) logInMenuPane.getChildren().get(9);
        setSignUpLinkProperties();

        captchaPane = (Pane) logInMenuPane.getChildren().get(10);
        setCaptchaPaneProperties();


        Scene scene = new Scene(logInMenuPane);
        stage.setScene(scene);
        stage.show();
    }


    private void setUsernameProperties() {

    }

    private void setPasswordProperties() {

    }

    private void setErrorProperties() {
    }

    private void setForgotPasswordLinkProperties() {
    }

    private void setStayLoggedInProperties() {
    }

    private void setSignUpLinkProperties() {
        signUpLink.setOnMouseClicked((event) -> {
            try {
                new SignUpMenuJFX().start(stage);
            } catch (Exception e) {

            }
        });
    }

    private void setLogInButtonProperties() {
        EventHandler<MouseEvent> logInHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                String result = loginMenuController.login(username.getText(), password.getText(), stayLoggedIn.isSelected());
                if (!result.isEmpty()) {
                    error.setText(result);
                } else {
                    try {
                        popOutCaptchaPane();
                    } catch (MalformedURLException e) {
                        throw new RuntimeException(e);
                    } catch (URISyntaxException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        };
        loginButton.hoverProperty().addListener((event) -> {
            loginButton.setOpacity(1.5 - loginButton.getOpacity());
        });
        loginButtonText.hoverProperty().addListener((event) -> {
            loginButton.setOpacity(1.5 - loginButton.getOpacity());
        });
        loginButton.setOnMouseClicked(logInHandler);
        loginButtonText.setOnMouseClicked(logInHandler);
    }

    private void setCaptchaPaneProperties() {
        captchaPicture = (Rectangle) captchaPane.getChildren().get(0);
        refreshCaptchaButton = (Circle) captchaPane.getChildren().get(1);
        refreshCaptchaButton.setFill(new ImagePattern(new Image(Objects.requireNonNull(getClass().getResource("/icons/refresh.png")).toExternalForm())));
        refreshCaptchaButton.setOnMouseClicked((event) -> {
            refreshCaptcha();
        });
        captchaError = (Label) captchaPane.getChildren().get(2);
        captchaAnswer = (TextField) captchaPane.getChildren().get(3);
        captchaAnswerButton = (Button) captchaPane.getChildren().get(4);

        captchaAnswerButton.setOnMouseClicked((event) -> {
            if (captchaAnswer.getText().equals(loginMenuController.getCaptchaAnswer())) {
                try {
                    // TODO
                    new MainMenuJFX().start(stage);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            } else {
                captchaError.setText("captcha answer is incorrect!");
                refreshCaptcha();
            }
        });
    }

    private void popOutCaptchaPane() throws MalformedURLException, URISyntaxException {
        captchaPane.setVisible(true);
        captchaPane.toFront();
        refreshCaptcha();
    }

    private void popOffCaptchaPane() {
        captchaPane.setVisible(false);
    }

    private void refreshCaptcha() {
        String captchaAns = Game.getCaptcha().get(new Random().nextInt(Game.getCaptcha().size()));
        captchaPicture.setFill(new ImagePattern(new Image(Objects.requireNonNull(getClass().getResource("/captcha/" + captchaAns + ".png")).toExternalForm())));
        loginMenuController.setCaptchaAnswer(captchaAns);
    }

    public Controller getController() {
        return controller;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public LoginMenuController getLoginMenuController() {
        return loginMenuController;
    }

    public void setLoginMenuController(LoginMenuController loginMenuController) {
        this.loginMenuController = loginMenuController;
    }
}
