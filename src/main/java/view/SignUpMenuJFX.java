package view;

import controller.Controller;
import controller.SignUpMenuController;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.Objects;

public class SignUpMenuJFX extends Application implements MenuJFX {
    private Controller controller;
    private SignUpMenuController signUpMenuController;
    private AnchorPane signUpMenuPane;
    private TextField username, visiblePassword, visiblePasswordConfirmation, nickname, email, passwordRecoveryAnswer, slogan;
    private Label title, recoveryTitle, usernameError, nicknameError, emailError, passwordError, passwordConfirmationError, sloganError,
            passwordRecoveryAnswerError, signUpButtonText, mainError;
    private PasswordField password, passwordConfirmation;
    private ChoiceBox sloganChoiceBox, passwordRecoveryQuestion;
    private CheckBox showPassword, customSlogan;
    private Circle randomPassword;
    private Hyperlink logInLink;
    private Rectangle signUpButton;
    private CaptchaJFX captchaJFX;
    private Stage stage;


    @Override
    public void start(Stage stage) throws Exception {
        System.out.println("started");
        this.stage = stage;
        signUpMenuPane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/SignUpMenu.fxml")));
        signUpMenuPane.setBackground(new Background(new BackgroundImage(new Image(getClass().getResource("/backgrounds/4.jpg").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
//        signUpMenuPane.setBackground(Background.fill(new ImagePattern(new Image(Objects.requireNonNull(getClass().getResource("/backgrounds/4.jpg")).toExternalForm()))));
        signUpMenuPane.setOnMouseClicked(new EventHandler() {
            @Override
            public void handle(Event event) {
                signUpMenuPane.requestFocus();
            }
        });

        signUpButton = (Rectangle) signUpMenuPane.getChildren().get(0);
        signUpButtonText = (Label) signUpMenuPane.getChildren().get(1);
        setSignUpProperties();

        title = (Label) signUpMenuPane.getChildren().get(2);

        username = (TextField) signUpMenuPane.getChildren().get(3);
        usernameError = (Label) signUpMenuPane.getChildren().get(4);
        setUsernameProperties();

        password = (PasswordField) signUpMenuPane.getChildren().get(5);
        visiblePassword = (TextField) signUpMenuPane.getChildren().get(6);
        showPassword = (CheckBox) signUpMenuPane.getChildren().get(7);
        randomPassword = (Circle) signUpMenuPane.getChildren().get(8);
        passwordError = (Label) signUpMenuPane.getChildren().get(9);
        passwordConfirmation = (PasswordField) signUpMenuPane.getChildren().get(10);
        visiblePasswordConfirmation = (TextField) signUpMenuPane.getChildren().get(11);

        passwordConfirmationError = (Label) signUpMenuPane.getChildren().get(12);
        setPasswordProperties();

        nickname = (TextField) signUpMenuPane.getChildren().get(13);
        nicknameError = (Label) signUpMenuPane.getChildren().get(14);
        setNickNameProperties();

        email = (TextField) signUpMenuPane.getChildren().get(15);
        emailError = (Label) signUpMenuPane.getChildren().get(16);
        setEmailProperties();

        slogan = (TextField) signUpMenuPane.getChildren().get(17);
        sloganChoiceBox = (ChoiceBox) signUpMenuPane.getChildren().get(18);
        customSlogan = (CheckBox) signUpMenuPane.getChildren().get(19);
        sloganError = (Label) signUpMenuPane.getChildren().get(20);
        setSloganProperties();

        recoveryTitle = (Label) signUpMenuPane.getChildren().get(21);

        passwordRecoveryQuestion = (ChoiceBox) signUpMenuPane.getChildren().get(22);
        passwordRecoveryAnswer = (TextField) signUpMenuPane.getChildren().get(23);
        passwordRecoveryAnswerError = (Label) signUpMenuPane.getChildren().get(24);
        setPasswordRecoveryProperties();

        logInLink = (Hyperlink) signUpMenuPane.getChildren().get(25);
        setLogInLinkProperties();

        mainError = (Label) signUpMenuPane.getChildren().get(26);

        captchaJFX = new CaptchaJFX(controller, signUpMenuPane);
        setCaptchaPaneProperties();

        adjustWithScene();
        Scene scene = new Scene(signUpMenuPane);
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
        Adjust.adjustPane(signUpMenuPane, ratioX, ratioY);
        Adjust.adjustTextField(username, ratioX, ratioY);
        Adjust.adjustTextField(visiblePassword, ratioX, ratioY);
        Adjust.adjustTextField(visiblePasswordConfirmation, ratioX, ratioY);
        Adjust.adjustTextField(nickname, ratioX, ratioY);
        Adjust.adjustTextField(email, ratioX, ratioY);
        Adjust.adjustTextField(slogan, ratioX, ratioY);
        Adjust.adjustTextField(passwordRecoveryAnswer, ratioX, ratioY);
        Adjust.adjustLabel(title, ratioX, ratioY);
        Adjust.adjustLabel(recoveryTitle, ratioX, ratioY);
        Adjust.adjustLabel(usernameError, ratioX, ratioY);
        Adjust.adjustLabel(nicknameError, ratioX, ratioY);
        Adjust.adjustLabel(emailError, ratioX, ratioY);
        Adjust.adjustLabel(passwordError, ratioX, ratioY);
        Adjust.adjustLabel(passwordConfirmationError, ratioX, ratioY);
        Adjust.adjustLabel(sloganError, ratioX, ratioY);
        Adjust.adjustLabel(passwordRecoveryAnswerError, ratioX, ratioY);
        Adjust.adjustLabel(signUpButtonText, ratioX, ratioY);
        Adjust.adjustLabel(mainError, ratioX, ratioY);
        Adjust.adjustPasswordField(password, ratioX, ratioY);
        Adjust.adjustPasswordField(passwordConfirmation, ratioX, ratioY);
        Adjust.adjustChoiceBox(sloganChoiceBox, ratioX, ratioY);
        Adjust.adjustChoiceBox(passwordRecoveryQuestion, ratioX, ratioY);
        Adjust.adjustCheckBox(showPassword, ratioX, ratioY);
        Adjust.adjustCheckBox(customSlogan, ratioX, ratioY);
        Adjust.adjustHyperlink(logInLink, ratioX, ratioY);
        Adjust.adjustRectangle(signUpButton, ratioX, ratioY);
        Adjust.adjustCircle(randomPassword, ratioX, ratioY);
        captchaJFX.adjust(ratioX, ratioY);
    }

    @Override
    public void adjustWithScene() {
        adjust(stage.getScene().getWidth() / signUpMenuPane.getPrefWidth(), stage.getScene().getHeight() / signUpMenuPane.getPrefHeight());
    }

    private void setSignUpProperties() {
        EventHandler clickHandler = new EventHandler() {
            @Override
            public void handle(Event event) {
                if (!isAFieldEmpty() && areErrorsEmpty()) {
                    try {
                        captchaJFX.popOutCaptchaPane();
                    } catch (URISyntaxException e) {
                        throw new RuntimeException(e);
                    } catch (MalformedURLException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        };
        signUpButton.hoverProperty().addListener(event -> {
            signUpButton.setOpacity(1.5 - signUpButton.getOpacity());
        });
        signUpButtonText.hoverProperty().addListener(event -> {
            signUpButton.setOpacity(1.5 - signUpButton.getOpacity());
        });
        signUpButton.setOnMouseClicked(clickHandler);
        signUpButtonText.setOnMouseClicked(clickHandler);
    }

    private void setUsernameProperties() {
        username.textProperty().addListener((observable, oldValue, newValue) -> {
            if (username.getText().isEmpty()) usernameError.setText("");
            else usernameError.setText(signUpMenuController.checkUsername(username.getText()));
        });
    }

    private void setPasswordProperties() {
        password.textProperty().addListener((observable, oldValue, newValue) -> {
            visiblePassword.setText(password.getText());
            if (password.getText().isEmpty()) passwordError.setText("");
            else passwordError.setText(signUpMenuController.checkPassword(password.getText()));
            if (!password.getText().equals(passwordConfirmation.getText())) {
                passwordConfirmationError.setText("Passwords don't match!");
            } else {
                passwordConfirmationError.setText("");
            }
        });
        visiblePassword.textProperty().addListener((observable, oldValue, newValue) -> {
            password.setText(visiblePassword.getText());
        });
        passwordConfirmation.textProperty().addListener((observable, oldValue, newValue) -> {
            visiblePasswordConfirmation.setText(passwordConfirmation.getText());
            if (!password.getText().equals(passwordConfirmation.getText())) {
                passwordConfirmationError.setText("Passwords don't match!");
            } else {
                passwordConfirmationError.setText("");
            }
        });
        visiblePasswordConfirmation.textProperty().addListener((observable, oldValue, newValue) -> {
            passwordConfirmation.setText(visiblePasswordConfirmation.getText());
        });
        showPassword.setOnMouseClicked(event -> {
            if (showPassword.isSelected()) {
                password.setVisible(false);
                passwordConfirmation.setVisible(false);
                visiblePassword.setVisible(true);
                visiblePasswordConfirmation.setVisible(true);
            } else {
                password.setVisible(true);
                passwordConfirmation.setVisible(true);
                visiblePassword.setVisible(false);
                visiblePasswordConfirmation.setVisible(false);
            }
        });
        randomPassword.setFill(new ImagePattern(new Image(Objects.requireNonNull(getClass().getResource("/icons/refresh.png")).toExternalForm())));
        randomPassword.setOnMouseClicked(event -> {
            password.setText(signUpMenuController.getRandomPassword());
        });
    }

    private void setNickNameProperties() {
        nickname.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!nickname.getText().isEmpty()) nicknameError.setText("");
        });
    }

    private void setEmailProperties() {
        email.textProperty().addListener((observable, oldValue, newValue) -> {
            emailError.setText(signUpMenuController.checkEmail(email.getText()));
        });
    }

    private void setSloganProperties() {
        sloganChoiceBox.getItems().add("Slogan : None");
        sloganChoiceBox.getItems().addAll(signUpMenuController.getDefaultSlogans());
        sloganChoiceBox.getSelectionModel().selectFirst();
        customSlogan.setOnMouseClicked(event -> {
            if (customSlogan.isSelected()) {
                sloganChoiceBox.getSelectionModel().selectFirst();
                sloganChoiceBox.setVisible(false);
                slogan.setVisible(true);
            } else {
                sloganChoiceBox.setVisible(true);
                slogan.setVisible(false);
                slogan.setText("");
            }
        });
    }

    private void setPasswordRecoveryProperties() {
        passwordRecoveryQuestion.getItems().addAll(signUpMenuController.getDefaultRecoveryQuestions());
        passwordRecoveryQuestion.getSelectionModel().selectFirst();
        passwordRecoveryAnswer.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                passwordRecoveryAnswerError.setText("");
            }
        });
    }

    private void setLogInLinkProperties() {
        logInLink.setOnMouseClicked(event -> {
            try {
                controller.setSceneWidth(stage.getScene().getWidth());
                controller.setSceneHeight(stage.getScene().getHeight());
                System.out.println("exited sign up menu " + stage.getScene().getWidth() + " " + stage.getScene().getHeight());
                controller.enterLogInMenuJFX();
                stop();
                controller.getGame().getCurrentMenuJFX().start(stage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private void setCaptchaPaneProperties() {
        captchaJFX.getCaptchaAnswerButton().setOnMouseClicked((event) -> {
            if (captchaJFX.getCaptchaAnswer().getText().equals(controller.getCaptchaAnswer())) {
                try {
                    createUser();
                    mainError.setText("user created successfully!");
                    captchaJFX.popOffCaptchaPane();
                    Timeline timeline = new Timeline(new KeyFrame(Duration.millis(3000), event2 -> {
                        mainError.setText("");
                    }));
                    timeline.setCycleCount(1);
                    timeline.play();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else {
                captchaJFX.getCaptchaError().setText("captcha answer is incorrect!");
                captchaJFX.refreshCaptcha();
            }
        });
    }

    private boolean isAFieldEmpty() {
        boolean result = false;
        if (username.getText().isEmpty()) {
            usernameError.setText("empty field!");
            result = true;
        }
        if (password.getText().isEmpty()) {
            passwordError.setText("empty field!");
            result = true;
        }
        if (nickname.getText().isEmpty()) {
            nicknameError.setText("empty field!");
            result = true;
        }
        if (email.getText().isEmpty()) {
            emailError.setText("empty field!");
            result = true;
        }
        if (passwordRecoveryAnswer.getText().isEmpty()) {
            passwordRecoveryAnswerError.setText("empty field!");
            result = true;
        }
        if (passwordConfirmation.getText().isEmpty()) {
            passwordConfirmationError.setText("empty field!");
            result = true;
        }
        return result;
    }

    private boolean areErrorsEmpty() {
        return usernameError.getText().isEmpty() && passwordError.getText().isEmpty() && emailError.getText().isEmpty() &&
                passwordRecoveryAnswerError.getText().isEmpty() && sloganError.getText().isEmpty() &&
                passwordConfirmationError.getText().isEmpty();
    }

    private void createUser() throws IOException {
        String slogan = sloganChoiceBox.getSelectionModel().getSelectedItem().equals("Slogan : None") ? customSlogan.getText() : (String) sloganChoiceBox.getSelectionModel().getSelectedItem();
        if (slogan.isEmpty()) slogan = null;
        signUpMenuController.createUserJFX(username.getText(), password.getText(), nickname.getText(), email.getText(),
                (String) passwordRecoveryQuestion.getSelectionModel().getSelectedItem(),
                passwordRecoveryAnswer.getText(), slogan);
    }

    public Controller getController() {
        return controller;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public SignUpMenuController getSignUpMenuController() {
        return signUpMenuController;
    }

    public void setSignUpMenuController(SignUpMenuController signUpMenuController) {
        this.signUpMenuController = signUpMenuController;
    }
}