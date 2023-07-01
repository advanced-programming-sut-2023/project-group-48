package view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.util.Objects;

public class ForgotPasswordJFX implements MenuJFX {
    private final AnchorPane loginMenuPane;
    private final Pane forgotPasswordPane;
    private final Label errorLabel, questionLabel;
    private final TextField answerTextField;
    private final Button applyButton;

    public ForgotPasswordJFX(AnchorPane loginMenuPane) throws IOException {
        this.loginMenuPane = loginMenuPane;

        this.forgotPasswordPane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/ForgotPassword.fxml")));
        setForgotPasswordPane();

        this.questionLabel = (Label) forgotPasswordPane.getChildren().get(0);

        this.errorLabel = (Label) forgotPasswordPane.getChildren().get(1);

        this.answerTextField = (TextField) forgotPasswordPane.getChildren().get(2);
        setErrorLabel();

        this.applyButton = (Button) forgotPasswordPane.getChildren().get(3);
    }

    private void setForgotPasswordPane() {
        forgotPasswordPane.setBackground(Background.fill(Color.WHITE));
        forgotPasswordPane.setLayoutX(loginMenuPane.getPrefWidth() / 2 - forgotPasswordPane.getPrefWidth() / 2);
        forgotPasswordPane.setLayoutY(loginMenuPane.getPrefHeight() / 2 - forgotPasswordPane.getPrefHeight() / 2);
    }

    private void setErrorLabel() {
        answerTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                errorLabel.setText("");
            }
        });
    }

    public TextField getAnswerTextField() {
        return answerTextField;
    }

    public Button getApplyButton() {
        return applyButton;
    }

    public void popOutForgotPasswordPane(String question) {
        questionLabel.setText(question);
        forgotPasswordPane.setVisible(true);
        forgotPasswordPane.toFront();
    }

    public void popOffForgotPasswordPane() {
        questionLabel.setText("");
        errorLabel.setText("");
        forgotPasswordPane.setVisible(false);
        forgotPasswordPane.toBack();
    }

    public Label getErrorLabel() {
        return errorLabel;
    }

    @Override
    public void adjust(double ratioX, double ratioY) {
        Adjust.adjustPane(forgotPasswordPane, ratioX, ratioY);
        Adjust.adjustLabel(questionLabel, ratioX, ratioY);
        Adjust.adjustLabel(errorLabel, ratioX, ratioY);
        Adjust.adjustTextField(answerTextField, ratioX, ratioY);
        Adjust.adjustButton(applyButton, ratioX, ratioY);
    }

    @Override
    public void adjustWithScene() {

    }
}
