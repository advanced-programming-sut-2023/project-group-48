package view;

import controller.Controller;
import controller.ShopMenuController;
import controller.TradeMenuController;
import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.Objects;

public class TradeMenuJFX extends Application implements MenuJFX{

    private AnchorPane tradeMenuPane;
    private Controller controller;
    private TradeMenuController tradeMenuController;
    private Rectangle back, sendRequest, showRequests, tradeTitleBackground;
    private Label sendRequestText, showRequestsText ,tradeTitle;
    private Stage stage;

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        tradeMenuPane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/TradeMenu.fxml")));
        tradeMenuPane.setBackground(new Background(new BackgroundImage(new Image(getClass().getResource("/backgrounds/10.jpg").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        tradeMenuPane.setOnMouseClicked(new EventHandler() {
            @Override
            public void handle(Event event) {
                tradeMenuPane.requestFocus();
            }
        });
        back = (Rectangle) tradeMenuPane.getChildren().get(0);
        setBackButton();

        sendRequest = (Rectangle) tradeMenuPane.getChildren().get(1);
        sendRequestText = (Label) tradeMenuPane.getChildren().get(2);
        showRequests = (Rectangle) tradeMenuPane.getChildren().get(3);
        showRequestsText = (Label) tradeMenuPane.getChildren().get(4);
        setRequestButtons();

        tradeTitleBackground = (Rectangle) tradeMenuPane.getChildren().get(5);
        tradeTitleBackground.setFill(Color.WHITE);
        tradeTitle = (Label) tradeMenuPane.getChildren().get(6);

        adjustWithScene();

        Scene scene = new Scene(tradeMenuPane);
        stage.setScene(scene);
        scene.heightProperty().addListener((observable, oldValue, newValue) -> {
            adjustWithScene();
        });
        scene.widthProperty().addListener((observable, oldValue, newValue) -> {
            adjustWithScene();
        });
        stage.show();
    }

    private void setRequestButtons() {
        EventHandler clickHandlerSend = new EventHandler() {
            @Override
            public void handle(Event event) {
                controller.enterSendRequestJFX();
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
        sendRequest.hoverProperty().addListener(event -> {
            sendRequest.setOpacity(1.5 - sendRequest.getOpacity());
        });
        sendRequestText.hoverProperty().addListener(event -> {
            sendRequest.setOpacity(1.5 - sendRequest.getOpacity());
        });
        sendRequest.setOnMouseClicked(clickHandlerSend);
        sendRequestText.setOnMouseClicked(clickHandlerSend);
        EventHandler clickHandlerShow = new EventHandler() {
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
        showRequests.hoverProperty().addListener(event -> {
            showRequests.setOpacity(1.5 - showRequests.getOpacity());
        });
        showRequestsText.hoverProperty().addListener(event -> {
            showRequests.setOpacity(1.5 - showRequests.getOpacity());
        });
        showRequests.setOnMouseClicked(clickHandlerShow);
        showRequestsText.setOnMouseClicked(clickHandlerShow);
    }

    private void setBackButton() {
        back.setFill(new ImagePattern(new Image(getClass().getResource("/icons/back.png").toExternalForm())));
        EventHandler clickHandler = new EventHandler() {
            @Override
            public void handle(Event event) {
                controller.enterMatchMenuJFX();
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

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public TradeMenuController getTradeMenuController() {
        return tradeMenuController;
    }

    public void setTradeMenuController(TradeMenuController tradeMenuController) {
        this.tradeMenuController = tradeMenuController;
    }

    @Override
    public void adjust(double ratioX, double ratioY) {
        Adjust.adjustPane(tradeMenuPane, ratioX, ratioY);
        Adjust.adjustRectangle(back, ratioX, ratioY);
        Adjust.adjustRectangle(sendRequest, ratioX, ratioY);
        Adjust.adjustLabel(sendRequestText, ratioX, ratioY);
        Adjust.adjustRectangle(showRequests, ratioX, ratioY);
        Adjust.adjustLabel(showRequestsText, ratioX, ratioY);
        Adjust.adjustRectangle(tradeTitleBackground, ratioX, ratioY);
        Adjust.adjustLabel(tradeTitle, ratioX, ratioY);
    }

    @Override
    public void adjustWithScene() {
        adjust(stage.getScene().getWidth() / tradeMenuPane.getPrefWidth(),
                stage.getScene().getHeight() / tradeMenuPane.getPrefHeight());
    }
}
