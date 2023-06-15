package view;

import controller.Controller;
import controller.LoginMenuController;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.BoxBlur;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.Objects;

public class LogInMenuJFX extends Application {
    private Controller controller;
    private LoginMenuController loginMenuController;
    private AnchorPane logInMenuPane;
    private TextField username, visiblePassword, captchaAnswer;
    private PasswordField password;
    private CheckBox showPassword, stayLoggedIn;
    private Label mainError, loginButtonText, captchaError;
    private Hyperlink forgotPasswordLink, signUpLink;
    private Rectangle loginButton, captchaPicture;
    private Circle refreshCaptchaButton;
    private Button captchaAnswerButton;
    private CaptchaJFX captchaJFX;
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
        visiblePassword = (TextField) logInMenuPane.getChildren().get(5);
        showPassword = (CheckBox) logInMenuPane.getChildren().get(6);
        setPasswordProperties();

        mainError = (Label) logInMenuPane.getChildren().get(7);
        setErrorProperties();

        stayLoggedIn = (CheckBox) logInMenuPane.getChildren().get(8);

        forgotPasswordLink = (Hyperlink) logInMenuPane.getChildren().get(9);
        setForgotPasswordLinkProperties();

        signUpLink = (Hyperlink) logInMenuPane.getChildren().get(10);
        setSignUpLinkProperties();

        captchaJFX = new CaptchaJFX(controller, logInMenuPane);
        setCaptchaPaneProperties();


        Scene scene = new Scene(logInMenuPane);
        stage.setScene(scene);
        stage.show();
    }

    private void setPasswordProperties() {
        showPassword.setOnMouseClicked(event -> {
            if (showPassword.isSelected()) {
                password.setVisible(false);
                visiblePassword.setVisible(true);
            } else {
                password.setVisible(true);
                visiblePassword.setVisible(false);
            }
        });
        password.textProperty().addListener((observable, oldValue, newValue) -> {
            visiblePassword.setText(password.getText());
        });
        visiblePassword.textProperty().addListener((observable, oldValue, newValue) -> {
            password.setText(visiblePassword.getText());
        });
    }

    private void setErrorProperties() {
    }

    private void setForgotPasswordLinkProperties() {
        forgotPasswordLink.setOnMouseClicked((event) -> {
            try {
                // TODO Auto-generated method stub
            } catch (Exception e) {

            }
        });
    }

    private void setSignUpLinkProperties() {
        signUpLink.setOnMouseClicked((event) -> {
            try {
                controller.enterSignUpMenu();
                stop();
                controller.getGame().getCurrentMenuJFX().start(stage);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void setLogInButtonProperties() {
        EventHandler<MouseEvent> logInHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    captchaJFX.popOutCaptchaPane();
                } catch (MalformedURLException | URISyntaxException e) {
                    throw new RuntimeException(e);
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
        captchaJFX.getCaptchaAnswerButton().setOnMouseClicked((event) -> {
            if (captchaJFX.getCaptchaAnswer().getText().equals(controller.getCaptchaAnswer())) {
                String result = null;
                try {
                    result = loginMenuController.login(username.getText(), password.getText(), stayLoggedIn.isSelected());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                if (!result.isEmpty()) {
                    mainError.setText(result);
                    captchaJFX.popOffCaptchaPane();
                } else {
                    try {
                        controller.enterMainMenuJFX();
                        stop();
                        controller.getGame().getCurrentMenuJFX().start(stage);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            } else {
                captchaJFX.getCaptchaError().setText("captcha answer is incorrect!");
                captchaJFX.refreshCaptcha();
            }
        });
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
