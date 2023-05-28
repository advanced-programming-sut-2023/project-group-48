package view;

import controller.Controller;
import controller.SignUpMenuController;
import javafx.application.Application;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import model.User;

import java.util.EventListener;
import java.util.Objects;

public class SignUpMenuJFX extends Application {
    private Controller controller;
    private SignUpMenuController signUpMenuController;
    private AnchorPane signUpMenuPane;
    private TextField username, nickname, email, passwordRecoveryAnswer, slogan;
    private Label usernameError, nicknameError, emailError, passwordError, passwordConfirmationError, sloganError, passwordRecoveryAnswerError, signUpButtonText;
    private PasswordField password, passwordConfirmation;
    private ChoiceBox sloganChoiceBox, passwordRecoveryQuestion;
    private CheckBox showPassword, customSlogan;
    private Circle randomPassword, changeCaptcha;
    private Hyperlink logInLink;
    private Rectangle signUpButton;
    private Stage stage;


    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        signUpMenuPane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/SignUpMenu.fxml")));
        signUpMenuPane.setBackground(Background.fill(new ImagePattern(new Image(Objects.requireNonNull(getClass().getResource("/backgrounds/2.jpg")).toExternalForm()))));

        signUpButton = (Rectangle) signUpMenuPane.getChildren().get(0);
        signUpButtonText = (Label) signUpMenuPane.getChildren().get(1);
        setSignUpProperties();

        username = (TextField) signUpMenuPane.getChildren().get(3);
        usernameError = (Label) signUpMenuPane.getChildren().get(4);
        setUsernameProperties();

        password = (PasswordField) signUpMenuPane.getChildren().get(5);
        showPassword = (CheckBox) signUpMenuPane.getChildren().get(6);
        passwordError = (Label) signUpMenuPane.getChildren().get(7);
        passwordConfirmation = (PasswordField) signUpMenuPane.getChildren().get(8);
        passwordConfirmationError = (Label) signUpMenuPane.getChildren().get(9);
        setPasswordProperties();

        nickname = (TextField) signUpMenuPane.getChildren().get(10);
        nicknameError = (Label) signUpMenuPane.getChildren().get(11);

        email = (TextField) signUpMenuPane.getChildren().get(12);
        emailError = (Label) signUpMenuPane.getChildren().get(13);
        setEmailProperties();

        slogan = (TextField) signUpMenuPane.getChildren().get(14);
        sloganChoiceBox = (ChoiceBox) signUpMenuPane.getChildren().get(15);
        customSlogan = (CheckBox) signUpMenuPane.getChildren().get(16);
        sloganError = (Label) signUpMenuPane.getChildren().get(17);
        setSloganProperties();

        passwordRecoveryQuestion = (ChoiceBox) signUpMenuPane.getChildren().get(19);
        passwordRecoveryAnswer = (TextField) signUpMenuPane.getChildren().get(20);
        passwordRecoveryAnswerError = (Label) signUpMenuPane.getChildren().get(21);
        setPasswordRecoveryProperties();

        logInLink = (Hyperlink) signUpMenuPane.getChildren().get(22);
        logInLink.setOnMouseClicked(event -> {
            try {
                new LogInMenuJFX().start(stage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        Scene scene = new Scene(signUpMenuPane);
        stage.setScene(scene);
        stage.show();
    }

    private void setSignUpProperties() {
        EventHandler clickHandler = new EventHandler() {
            @Override
            public void handle(Event event) {
                if (!username.getText().isEmpty() && !password.getText().isEmpty() && !nickname.getText().isEmpty() &&
                        !email.getText().isEmpty() && !passwordRecoveryAnswer.getText().isEmpty() && !slogan.getText().isEmpty() &&
                        !passwordConfirmation.getText().isEmpty() && usernameError.getText().isEmpty() && passwordError.getText().isEmpty() &&
                        nicknameError.getText().isEmpty() && emailError.getText().isEmpty() && passwordRecoveryAnswerError.getText().isEmpty() &&
                        sloganError.getText().isEmpty() && passwordConfirmationError.getText().isEmpty()) {
                    try {
                        // TODO
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else if (username.getText().isEmpty() || password.getText().isEmpty() || nickname.getText().isEmpty() ||
                        email.getText().isEmpty() || passwordRecoveryAnswer.getText().isEmpty() || slogan.getText().isEmpty() ||
                        passwordConfirmation.getText().isEmpty()) {

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
            usernameError.setText(signUpMenuController.checkUsername(username.getText()));
        });
    }

    private void setPasswordProperties() {
        password.textProperty().addListener((observable, oldValue, newValue) -> {
            passwordError.setText(signUpMenuController.checkPassword(password.getText()));
            if (!password.getText().equals(passwordConfirmation.getText())) {
                passwordConfirmationError.setText("Passwords don't match!");
            }
        });
        showPassword.setOnMouseClicked(event -> {
            if (showPassword.isSelected()) {
                password.setPromptText(password.getText());
                password.setText("");
            } else {
                password.setText(password.getPromptText());
                password.setPromptText("");
            }
        });
    }

    private void setEmailProperties() {
        email.textProperty().addListener((observable, oldValue, newValue) -> {
            emailError.setText(signUpMenuController.checkEmail(email.getText()));
        });
    }

    private void setSloganProperties() {
        sloganChoiceBox.getItems().add("Slogan : None");
        sloganChoiceBox.getItems().addAll(User.getSlogans());
        sloganChoiceBox.getSelectionModel().selectFirst();
        customSlogan.setOnMouseClicked(event -> {
            if (customSlogan.isSelected()) {
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
        passwordRecoveryQuestion.getItems().addAll(User.getQuestions());
        passwordRecoveryQuestion.getSelectionModel().selectFirst();
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