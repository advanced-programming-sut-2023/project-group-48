package view;

import controller.Controller;
import controller.TradeMenuController;
import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Match.Request;

import java.util.ArrayList;
import java.util.Objects;

public class TradeListJFX extends Application implements MenuJFX{

    private AnchorPane tradeListPane;
    private Controller controller;
    private TradeMenuController tradeMenuController;
    private Rectangle back, tradeTitleBackground, titlesBackground, accept, reject;
    private Label tradeTitle, idText, propertyText, userText, amountText, priceText, senderMessageText;
    private TextField idAnswer;
    private TextArea messageAnswer;
    private Stage stage;

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        tradeListPane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/TradeList.fxml")));
        tradeListPane.setBackground(new Background(new BackgroundImage(new Image(getClass().getResource("/backgrounds/10.jpg").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        tradeListPane.setOnMouseClicked(new EventHandler() {
            @Override
            public void handle(Event event) {
                tradeListPane.requestFocus();
            }
        });
        back = (Rectangle) tradeListPane.getChildren().get(0);
        setBackButton();

        tradeTitleBackground = (Rectangle) tradeListPane.getChildren().get(1);
        tradeTitleBackground.setFill(Color.WHITE);
        tradeTitle = (Label) tradeListPane.getChildren().get(2);

        titlesBackground = (Rectangle) tradeListPane.getChildren().get(3);
        idText = (Label) tradeListPane.getChildren().get(4);
        propertyText = (Label) tradeListPane.getChildren().get(5);
        userText = (Label) tradeListPane.getChildren().get(6);
        amountText = (Label) tradeListPane.getChildren().get(7);
        priceText = (Label) tradeListPane.getChildren().get(8);
        senderMessageText = (Label) tradeListPane.getChildren().get(9);

        setList();

        idAnswer = (TextField) tradeListPane.getChildren().get(10);
        messageAnswer = (TextArea) tradeListPane.getChildren().get(11);
        accept = (Rectangle) tradeListPane.getChildren().get(12);
        reject = (Rectangle) tradeListPane.getChildren().get(13);
        setAnswer();

        adjustWithScene();

        Scene scene = new Scene(tradeListPane);
        stage.setScene(scene);
        scene.heightProperty().addListener((observable, oldValue, newValue) -> {
            adjustWithScene();
        });
        scene.widthProperty().addListener((observable, oldValue, newValue) -> {
            adjustWithScene();
        });
        stage.show();
    }

    private void setAnswer() {
        accept.setFill(new ImagePattern(new Image(getClass().getResource("/icons/accept.png").toExternalForm())));
        EventHandler clickHandlerAccept = new EventHandler() {
            @Override
            public void handle(Event event) {
                if (idAnswer.getText().isEmpty()){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("answer operation is failed!");
                    alert.setContentText("you should enter an id!");
                    alert.showAndWait();
                }
                else {
                    String result = tradeMenuController.answerRequest(Integer.parseInt(idAnswer.getText()), messageAnswer.getText(), "yes");
                    if (!result.startsWith("Request")){
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setHeaderText("answer operation is failed!");
                        alert.setContentText(result);
                        alert.showAndWait();
                    }
                    else {
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setHeaderText("answer operation is successful!");
                        alert.setContentText(result);
                        alert.showAndWait();
                    }
                }
            }
        };
        accept.hoverProperty().addListener(event -> {
            accept.setOpacity(1.5 - accept.getOpacity());
        });
        accept.setOnMouseClicked(clickHandlerAccept);
        reject.setFill(new ImagePattern(new Image(getClass().getResource("/icons/reject.png").toExternalForm())));
        EventHandler clickHandlerReject = new EventHandler() {
            @Override
            public void handle(Event event) {
                if (idAnswer.getText().isEmpty()){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("answer operation is failed!");
                    alert.setContentText("you should enter an id!");
                    alert.showAndWait();
                }
                else {
                    String result = tradeMenuController.answerRequest(Integer.parseInt(idAnswer.getText()), messageAnswer.getText(), "no");
                    if (!result.startsWith("Request")){
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setHeaderText("answer operation is failed!");
                        alert.setContentText(result);
                        alert.showAndWait();
                    }
                    else {
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setHeaderText("answer operation is successful!");
                        alert.setContentText(result);
                        alert.showAndWait();
                    }
                }
            }
        };
        reject.hoverProperty().addListener(event -> {
            reject.setOpacity(1.5 - reject.getOpacity());
        });
        reject.setOnMouseClicked(clickHandlerReject);
    }

    private void setList() {
        ArrayList<Request> requests = tradeMenuController.tradeList();
        double i = 0;
        for (Request request : requests) {
            Rectangle theme = new Rectangle(213.0, 263.0 + 80.0 * i, 802.0, 80.0);
            theme.setFill(Color.WHITE);
            Text id = new Text(251.0, 287.0 + 80.0 * i, String.valueOf(request.getId()));
            Text property = new Text(321.0, 287.0 + 80.0 * i, request.getProperty().getPropertyInString());
            Text user = new Text(451.0, 287.0 + 80.0 * i, request.getSender().getOwner().getUsername());
            Text amount = new Text(567.0, 287.0 + 80.0 * i, String.valueOf(request.getAmount()));
            Text price = new Text(692.0, 287.0 + 80.0 * i, String.valueOf(request.getPrice()));
            Text senderMessage = new Text(804.0, 287.0 + 80.0 * i, request.getSenderMessage());
            tradeListPane.getChildren().add(theme);
            tradeListPane.getChildren().add(id);
            tradeListPane.getChildren().add(property);
            tradeListPane.getChildren().add(user);
            tradeListPane.getChildren().add(amount);
            tradeListPane.getChildren().add(price);
            tradeListPane.getChildren().add(senderMessage);
            i++;
        }
    }

    private void setBackButton() {
        back.setFill(new ImagePattern(new Image(getClass().getResource("/icons/back.png").toExternalForm())));
        EventHandler clickHandler = new EventHandler() {
            @Override
            public void handle(Event event) {
                controller.enterShowRequestsJFX();
                try {
                    stop();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    controller.getGame().getCurrentMenuJFX().start(stage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        back.hoverProperty().addListener(event -> {
            back.setOpacity(1.5 - back.getOpacity());
        });
        back.setOnMouseClicked(clickHandler);
    }

    @Override
    public void adjust(double ratioX, double ratioY) {
        Adjust.adjustPane(tradeListPane, ratioX, ratioY);
        Adjust.adjustRectangle(back, ratioX, ratioY);
        Adjust.adjustRectangle(tradeTitleBackground, ratioX, ratioY);
        Adjust.adjustLabel(tradeTitle, ratioX, ratioY);
        Adjust.adjustRectangle(titlesBackground, ratioX, ratioY);
        Adjust.adjustLabel(idText, ratioX, ratioY);
        Adjust.adjustLabel(propertyText, ratioX, ratioY);
        Adjust.adjustLabel(userText, ratioX, ratioY);
        Adjust.adjustLabel(amountText, ratioX, ratioY);
        Adjust.adjustLabel(priceText, ratioX, ratioY);
        Adjust.adjustLabel(senderMessageText, ratioX, ratioY);
        Adjust.adjustTextField(idAnswer, ratioX, ratioY);
        Adjust.adjustTextArea(messageAnswer, ratioX, ratioY);
        Adjust.adjustRectangle(accept, ratioX, ratioY);
        Adjust.adjustRectangle(reject, ratioX, ratioY);
    }

    @Override
    public void adjustWithScene() {
        adjust(stage.getScene().getWidth() / tradeListPane.getPrefWidth(),
                stage.getScene().getHeight() / tradeListPane.getPrefHeight());
    }

    public TradeMenuController getTradeMenuController() {
        return tradeMenuController;
    }

    public void setTradeMenuController(TradeMenuController tradeMenuController) {
        this.tradeMenuController = tradeMenuController;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }
}
