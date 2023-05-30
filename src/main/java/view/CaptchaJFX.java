package view;

import controller.Controller;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import model.Captcha;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.Objects;

public class CaptchaJFX {
    private final Controller controller;
    private final Pane captchaPane;
    private final Rectangle captchaPicture;
    private Circle refreshCaptchaButton;
    private final Label captchaError;
    private final TextField captchaAnswer;
    private Button captchaAnswerButton;

    public CaptchaJFX(Controller controller, AnchorPane anchorPane) throws IOException {
        this.controller = controller;
        captchaPane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/CaptchaPane.fxml")));
        anchorPane.getChildren().add(captchaPane);
        captchaPane.setLayoutX(anchorPane.getPrefWidth() / 2 - captchaPane.getPrefWidth() / 2);
        captchaPane.setLayoutY(anchorPane.getPrefHeight() / 2 - captchaPane.getPrefHeight() / 2);

        captchaPicture = (Rectangle) captchaPane.getChildren().get(0);

        refreshCaptchaButton = (Circle) captchaPane.getChildren().get(1);
        refreshCaptchaButton.setFill(new ImagePattern(new Image(Objects.requireNonNull(getClass().getResource("/icons/refresh.png")).toExternalForm())));
        refreshCaptchaButton.setOnMouseClicked((event) -> {
            refreshCaptcha();
        });

        captchaError = (Label) captchaPane.getChildren().get(2);

        captchaAnswer = (TextField) captchaPane.getChildren().get(3);

        captchaAnswerButton = (Button) captchaPane.getChildren().get(4);

        refreshCaptcha();
    }

    public Pane getCaptchaPane() {
        return captchaPane;
    }

    public Button getCaptchaAnswerButton() {
        return captchaAnswerButton;
    }

    public TextField getCaptchaAnswer() {
        return captchaAnswer;
    }

    public Label getCaptchaError() {
        return captchaError;
    }

    public void popOutCaptchaPane() throws MalformedURLException, URISyntaxException {
        captchaPane.setVisible(true);
        captchaPane.toFront();
    }

    public void popOffCaptchaPane() {
        this.captchaAnswer.setText("");
        controller.setCaptchaAnswer(null);
        this.captchaError.setText("");
        captchaPane.setVisible(false);
        captchaPane.toBack();
    }

    public void refreshCaptcha() {
        String captchaAns = Captcha.getRandomCaptcha();
        System.out.println(captchaAns);
        captchaPicture.setFill(new ImagePattern(new Image(Objects.requireNonNull(getClass().getResource("/captcha/" + captchaAns + ".png")).toExternalForm())));
        controller.setCaptchaAnswer(captchaAns);
        this.captchaAnswer.setText("");
    }
}
