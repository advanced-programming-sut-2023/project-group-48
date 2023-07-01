package view;

import controller.Controller;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import model.Captcha;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.Objects;

public class CaptchaJFX implements MenuJFX {
    private final Controller controller;
    private final AnchorPane anchorPane;
    private final Pane captchaPane;
    private final Rectangle captchaPicture;
    private final Circle refreshCaptchaButton;
    private final Label captchaError;
    private final TextField captchaAnswer;
    private final Button captchaAnswerButton;

    public CaptchaJFX(Controller controller, AnchorPane anchorPane) throws IOException {
        this.controller = controller;
        this.anchorPane = anchorPane;
        captchaPane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/CaptchaPane.fxml")));
        setCaptchaPane();

        captchaPicture = (Rectangle) captchaPane.getChildren().get(0);

        captchaError = (Label) captchaPane.getChildren().get(1);


        refreshCaptchaButton = (Circle) captchaPane.getChildren().get(2);
        setRefreshCaptchaButton();

        captchaAnswer = (TextField) captchaPane.getChildren().get(3);
        setCaptchaError();

        captchaAnswerButton = (Button) captchaPane.getChildren().get(4);

        refreshCaptcha();
    }

    private void setCaptchaPane() {
        captchaPane.setBackground(Background.fill(Color.BLACK));
        anchorPane.getChildren().add(captchaPane);
        captchaPane.setLayoutX(anchorPane.getPrefWidth() / 2 - captchaPane.getPrefWidth() / 2);
        captchaPane.setLayoutY(anchorPane.getPrefHeight() / 2 - captchaPane.getPrefHeight() / 2);
    }

    private void setRefreshCaptchaButton() {
        refreshCaptchaButton.setFill(new ImagePattern(new Image(Objects.requireNonNull(getClass().getResource("/icons/refresh.png")).toExternalForm())));
        refreshCaptchaButton.setOnMouseClicked((event) -> {
            refreshCaptcha();
        });
    }

    private void setCaptchaError() {
        captchaAnswer.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                captchaError.setText("");
            }
        });
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

    public void adjust(double ratioX, double ratioY) {
        Adjust.adjustPane(captchaPane, ratioX, ratioY);
        Adjust.adjustRectangle(captchaPicture, ratioX, ratioY);
        Adjust.adjustCircle(refreshCaptchaButton, ratioX, ratioY);
        Adjust.adjustLabel(captchaError, ratioX, ratioY);
        Adjust.adjustTextField(captchaAnswer, ratioX, ratioY);
        Adjust.adjustButton(captchaAnswerButton, ratioX, ratioY);
    }

    @Override
    public void adjustWithScene() {

    }
}