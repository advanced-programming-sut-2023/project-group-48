package view;

import controller.Controller;
import controller.TradeMenuController;
import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
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

public class TradeHistoryJFX extends Application implements MenuJFX {

    private AnchorPane tradeHistoryPane;
    private Controller controller;
    private TradeMenuController tradeMenuController;
    private Rectangle back, tradeTitleBackground, titlesBackground;
    private Label tradeTitle, idText, propertyText, userText, amountText, priceText, receiverMessageText, senderMessageText, stateText;
    private Stage stage;

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        tradeHistoryPane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/TradeHistory.fxml")));
        tradeHistoryPane.setBackground(new Background(new BackgroundImage(new Image(getClass().getResource("/backgrounds/10.jpg").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        tradeHistoryPane.setOnMouseClicked(new EventHandler() {
            @Override
            public void handle(Event event) {
                tradeHistoryPane.requestFocus();
            }
        });
        back = (Rectangle) tradeHistoryPane.getChildren().get(0);
        setBackButton();

        tradeTitleBackground = (Rectangle) tradeHistoryPane.getChildren().get(1);
        tradeTitleBackground.setFill(Color.WHITE);
        tradeTitle = (Label) tradeHistoryPane.getChildren().get(2);

        titlesBackground = (Rectangle) tradeHistoryPane.getChildren().get(3);
        idText = (Label) tradeHistoryPane.getChildren().get(4);
        propertyText = (Label) tradeHistoryPane.getChildren().get(5);
        userText = (Label) tradeHistoryPane.getChildren().get(6);
        amountText = (Label) tradeHistoryPane.getChildren().get(7);
        priceText = (Label) tradeHistoryPane.getChildren().get(8);
        receiverMessageText = (Label) tradeHistoryPane.getChildren().get(9);
        senderMessageText = (Label) tradeHistoryPane.getChildren().get(10);
        stateText = (Label) tradeHistoryPane.getChildren().get(11);

        setHistory();

        adjustWithScene();

        Scene scene = new Scene(tradeHistoryPane);
        stage.setScene(scene);
        scene.heightProperty().addListener((observable, oldValue, newValue) -> {
            adjustWithScene();
        });
        scene.widthProperty().addListener((observable, oldValue, newValue) -> {
            adjustWithScene();
        });
        stage.show();
    }

    private void setHistory() {
        ArrayList<Request> requests = tradeMenuController.tradeHistory();
        double i = 0;
        for (Request request : requests) {
            Rectangle theme = new Rectangle(213.0, 263.0 + 80.0 * i, 1137.0, 80.0);
            theme.setFill(Color.WHITE);
            Text id = new Text(251.0, 287.0 + 80.0 * i, String.valueOf(request.getId()));
            Text property = new Text(321.0, 287.0 + 80.0 * i, request.getProperty().getPropertyInString());
            Text user = new Text(451.0, 287.0 + 80.0 * i, request.getSender().getOwner().getUsername());
            Text amount = new Text(540.0, 287.0 + 80.0 * i, String.valueOf(request.getAmount()));
            Text price = new Text(662.0, 287.0 + 80.0 * i, String.valueOf(request.getPrice()));
            Text receiverMessage = new Text(745.0, 287.0 + 80.0 * i, request.getReceiverMessage());
            Text senderMessage = new Text(1013.0, 287.0 + 80.0 * i, request.getSenderMessage());
            Text state = new Text(1275.0, 287.0 + 80.0 * i, request.isAccepted() ? "Yes" : "No");
            tradeHistoryPane.getChildren().add(theme);
            tradeHistoryPane.getChildren().add(id);
            tradeHistoryPane.getChildren().add(property);
            tradeHistoryPane.getChildren().add(user);
            tradeHistoryPane.getChildren().add(amount);
            tradeHistoryPane.getChildren().add(price);
            tradeHistoryPane.getChildren().add(receiverMessage);
            tradeHistoryPane.getChildren().add(senderMessage);
            tradeHistoryPane.getChildren().add(state);
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
        Adjust.adjustPane(tradeHistoryPane, ratioX, ratioY);
        Adjust.adjustRectangle(back, ratioX, ratioY);
        Adjust.adjustRectangle(tradeTitleBackground, ratioX, ratioY);
        Adjust.adjustLabel(tradeTitle, ratioX, ratioY);
        Adjust.adjustRectangle(titlesBackground, ratioX, ratioY);
        Adjust.adjustLabel(idText, ratioX, ratioY);
        Adjust.adjustLabel(propertyText, ratioX, ratioY);
        Adjust.adjustLabel(userText, ratioX, ratioY);
        Adjust.adjustLabel(amountText, ratioX, ratioY);
        Adjust.adjustLabel(priceText, ratioX, ratioY);
        Adjust.adjustLabel(receiverMessageText, ratioX, ratioY);
        Adjust.adjustLabel(senderMessageText, ratioX, ratioY);
        Adjust.adjustLabel(stateText, ratioX, ratioY);
    }

    @Override
    public void adjustWithScene() {
        adjust(stage.getScene().getWidth() / tradeHistoryPane.getPrefWidth(),
                stage.getScene().getHeight() / tradeHistoryPane.getPrefHeight());
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
