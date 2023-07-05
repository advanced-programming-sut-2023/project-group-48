package view;

import controller.Controller;
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

public class ShowRequestsJFX extends Application implements MenuJFX{

    private AnchorPane showRequestsPane;
    private Controller controller;
    private TradeMenuController tradeMenuController;
    private Rectangle back, tradeList, tradeHistory, showRequestsTitleBackground;
    private Label tradeListText, tradeHistoryText, showRequestsTitle;
    private Stage stage;

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        showRequestsPane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/ShowRequests.fxml")));
        showRequestsPane.setBackground(new Background(new BackgroundImage(new Image(getClass().getResource("/backgrounds/10.jpg").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        showRequestsPane.setOnMouseClicked(new EventHandler() {
            @Override
            public void handle(Event event) {
                showRequestsPane.requestFocus();
            }
        });
        back = (Rectangle) showRequestsPane.getChildren().get(0);
        setBackButton();

        tradeList = (Rectangle) showRequestsPane.getChildren().get(1);
        tradeListText = (Label) showRequestsPane.getChildren().get(2);
        tradeHistory = (Rectangle) showRequestsPane.getChildren().get(3);
        tradeHistoryText = (Label) showRequestsPane.getChildren().get(4);
        setTradeButtons();

        showRequestsTitleBackground = (Rectangle) showRequestsPane.getChildren().get(5);
        showRequestsTitleBackground.setFill(Color.WHITE);
        showRequestsTitle = (Label) showRequestsPane.getChildren().get(6);

        adjustWithScene();

        Scene scene = new Scene(showRequestsPane);
        stage.setScene(scene);
        scene.heightProperty().addListener((observable, oldValue, newValue) -> {
            adjustWithScene();
        });
        scene.widthProperty().addListener((observable, oldValue, newValue) -> {
            adjustWithScene();
        });
        stage.show();
    }

    private void setTradeButtons() {
        EventHandler clickHandlerList = new EventHandler() {
            @Override
            public void handle(Event event) {
                controller.enterTradeListJFX();
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
        tradeList.hoverProperty().addListener(event -> {
            tradeList.setOpacity(1.5 - tradeList.getOpacity());
        });
        tradeListText.hoverProperty().addListener(event -> {
            tradeList.setOpacity(1.5 - tradeList.getOpacity());
        });
        tradeList.setOnMouseClicked(clickHandlerList);
        tradeListText.setOnMouseClicked(clickHandlerList);
        EventHandler clickHandlerHistory = new EventHandler() {
            @Override
            public void handle(Event event) {
                controller.enterTradeHistoryJFX();
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
        tradeHistory.hoverProperty().addListener(event -> {
            tradeHistory.setOpacity(1.5 - tradeHistory.getOpacity());
        });
        tradeHistoryText.hoverProperty().addListener(event -> {
            tradeHistory.setOpacity(1.5 - tradeHistory.getOpacity());
        });
        tradeHistory.setOnMouseClicked(clickHandlerHistory);
        tradeHistoryText.setOnMouseClicked(clickHandlerHistory);
    }

    private void setBackButton() {
        back.setFill(new ImagePattern(new Image(getClass().getResource("/icons/back.png").toExternalForm())));
        EventHandler clickHandler = new EventHandler() {
            @Override
            public void handle(Event event) {
                controller.enterTradeMenuJFX();
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
        Adjust.adjustPane(showRequestsPane, ratioX, ratioY);
        Adjust.adjustRectangle(back, ratioX, ratioY);
        Adjust.adjustRectangle(tradeList, ratioX, ratioY);
        Adjust.adjustLabel(tradeListText, ratioX, ratioY);
        Adjust.adjustRectangle(tradeHistory, ratioX, ratioY);
        Adjust.adjustLabel(tradeHistoryText, ratioX, ratioY);
        Adjust.adjustRectangle(showRequestsTitleBackground, ratioX, ratioY);
        Adjust.adjustLabel(showRequestsTitle, ratioX, ratioY);
    }

    @Override
    public void adjustWithScene() {
        adjust(stage.getScene().getWidth() / showRequestsPane.getPrefWidth(),
                stage.getScene().getHeight() / showRequestsPane.getPrefHeight());
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
}
