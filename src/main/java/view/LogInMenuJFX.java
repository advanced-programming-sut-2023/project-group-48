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
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.Objects;

public class LogInMenuJFX extends Application implements MenuJFX {
    private Controller controller;
    private LoginMenuController loginMenuController;
    private AnchorPane logInMenuPane;
    private TextField username, visiblePassword, captchaAnswer;
    private PasswordField password;
    private CheckBox showPassword, stayLoggedIn;
    private Label title, mainError, loginButtonText, captchaError, stayLoggedInLabel;
    private Hyperlink forgotPasswordLink, signUpLink;
    private Rectangle loginButton, captchaPicture;
    private CaptchaJFX captchaJFX;
    private ForgotPasswordJFX forgotPasswordJFX;
    private Stage stage;

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        logInMenuPane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/LogInMenu.fxml")));
        logInMenuPane.setBackground(new Background(new BackgroundImage(new Image(getClass().getResource("/backgrounds/5.png").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        Line line = new Line(logInMenuPane.getPrefWidth() / 2, 0, logInMenuPane.getPrefWidth() / 2, logInMenuPane.getPrefHeight());
//        logInMenuPane.setBackground(Background.fill(new ImagePattern(new Image(Objects.requireNonNull(getClass().getResource("/backgrounds/5.png")).toExternalForm()))));

        loginButton = (Rectangle) logInMenuPane.getChildren().get(0);
        loginButtonText = (Label) logInMenuPane.getChildren().get(1);
        setLogInButtonProperties();

        title = (Label) logInMenuPane.getChildren().get(2);

        username = (TextField) logInMenuPane.getChildren().get(3);

        password = (PasswordField) logInMenuPane.getChildren().get(4);
        visiblePassword = (TextField) logInMenuPane.getChildren().get(5);
        showPassword = (CheckBox) logInMenuPane.getChildren().get(6);
        setPasswordProperties();

        mainError = (Label) logInMenuPane.getChildren().get(7);
        setErrorProperties();

        stayLoggedIn = (CheckBox) logInMenuPane.getChildren().get(8);
        stayLoggedInLabel = (Label) logInMenuPane.getChildren().get(9);

        forgotPasswordLink = (Hyperlink) logInMenuPane.getChildren().get(10);
        setForgotPasswordLinkProperties();

        signUpLink = (Hyperlink) logInMenuPane.getChildren().get(11);
        setSignUpLinkProperties();

        captchaJFX = new CaptchaJFX(controller, logInMenuPane);
        setCaptchaPaneProperties();

        forgotPasswordJFX = new ForgotPasswordJFX(logInMenuPane);
        setForgotPasswordProperties();

        adjustWithScene();
        Scene scene = new Scene(logInMenuPane);
        stage.setScene(scene);
        scene.heightProperty().addListener((observable, oldValue, newValue) -> {
            adjustWithScene();
        });
        scene.widthProperty().addListener((observable, oldValue, newValue) -> {
            adjustWithScene();
        });
        stage.show();
    }

    @Override
    public void adjust(double ratioX, double ratioY) {
        Adjust.adjustPane(logInMenuPane, ratioX, ratioY);
        Adjust.adjustTextField(username, ratioX, ratioY);
        Adjust.adjustTextField(visiblePassword, ratioX, ratioY);
        Adjust.adjustPasswordField(password, ratioX, ratioY);
        Adjust.adjustCheckBox(showPassword, ratioX, ratioY);
        Adjust.adjustCheckBox(stayLoggedIn, ratioX, ratioY);
        Adjust.adjustLabel(stayLoggedInLabel, ratioX, ratioY);
        Adjust.adjustLabel(title, ratioX, ratioY);
        Adjust.adjustLabel(mainError, ratioX, ratioY);
        Adjust.adjustLabel(loginButtonText, ratioX, ratioY);
        Adjust.adjustHyperlink(forgotPasswordLink, ratioX, ratioY);
        Adjust.adjustHyperlink(signUpLink, ratioX, ratioY);
        Adjust.adjustRectangle(loginButton, ratioX, ratioY);
        captchaJFX.adjust(ratioX, ratioY);
    }

    @Override
    public void adjustWithScene() {
        adjust(stage.getScene().getWidth() / logInMenuPane.getPrefWidth(), stage.getScene().getHeight() / logInMenuPane.getPrefHeight());
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
            String result = loginMenuController.forgotPassword(username.getText(), stayLoggedIn.isSelected());
            if (result.equals("user doesn't exist!")) mainError.setText(result);
            else forgotPasswordJFX.popOutForgotPasswordPane(result);
        });
    }

    private void setSignUpLinkProperties() {
        signUpLink.setOnMouseClicked((event) -> {
            try {
                controller.setSceneWidth(stage.getScene().getWidth());
                controller.setSceneHeight(stage.getScene().getHeight());
                controller.enterSignUpMenuJFX();
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
                    login();
                }
            } else {
                captchaJFX.getCaptchaError().setText("captcha answer is incorrect!");
                captchaJFX.refreshCaptcha();
            }
        });
    }

    private void setForgotPasswordProperties() {
        forgotPasswordJFX.getApplyButton().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                String result = null;
                try {
                    result = loginMenuController.answerToSecurityQuestion(forgotPasswordJFX.getAnswerTextField().getText());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                if (!result.equals("logged in successfully!")) forgotPasswordJFX.getErrorLabel().setText(result);
                else {
                    forgotPasswordJFX.popOffForgotPasswordPane();
                    login();
                }
            }
        });
    }

    private void login() {
        try {
            controller.setSceneWidth(stage.getScene().getWidth());
            controller.setSceneHeight(stage.getScene().getHeight());
            controller.enterMainMenuJFX();
            stop();
            controller.getGame().getCurrentMenuJFX().start(stage);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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
