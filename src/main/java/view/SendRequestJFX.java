package view;

import controller.Controller;
import controller.TradeMenuController;
import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import model.Match.Property;

import java.util.Objects;

public class SendRequestJFX extends Application implements MenuJFX {

    private AnchorPane sendRequestPane;
    private Controller controller;
    private TradeMenuController tradeMenuController;
    private Rectangle back, sendRequestTitleBackground, partitionBackground, requestButton, donateButton;
    private Label sendRequestTitle, propertyText, userText, amountText, priceText, messageText, amount, requestButtonText, donateButtonText;
    private TextField price;
    private TextArea message;
    private Circle addButton, reduceButton;
    private ChoiceBox property, users;
    private Stage stage;

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        sendRequestPane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/SendRequest.fxml")));
        sendRequestPane.setBackground(new Background(new BackgroundImage(new Image(getClass().getResource("/backgrounds/10.jpg").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        sendRequestPane.setOnMouseClicked(new EventHandler() {
            @Override
            public void handle(Event event) {
                sendRequestPane.requestFocus();
            }
        });

        back = (Rectangle) sendRequestPane.getChildren().get(0);
        setBackButton();

        sendRequestTitleBackground = (Rectangle) sendRequestPane.getChildren().get(1);
        sendRequestTitleBackground.setFill(Color.WHITE);
        sendRequestTitle = (Label) sendRequestPane.getChildren().get(2);

        partitionBackground = (Rectangle) sendRequestPane.getChildren().get(3);
        partitionBackground.setFill(Color.WHITE);

        propertyText = (Label) sendRequestPane.getChildren().get(4);
        userText = (Label) sendRequestPane.getChildren().get(5);
        amountText = (Label) sendRequestPane.getChildren().get(6);
        priceText = (Label) sendRequestPane.getChildren().get(7);
        messageText = (Label) sendRequestPane.getChildren().get(8);

        property = (ChoiceBox) sendRequestPane.getChildren().get(9);
        setProperty();

        users = (ChoiceBox) sendRequestPane.getChildren().get(10);
        setUsers();

        amount = (Label) sendRequestPane.getChildren().get(11);
        addButton = (Circle) sendRequestPane.getChildren().get(12);
        reduceButton = (Circle) sendRequestPane.getChildren().get(13);
        setAmount();

        price = (TextField) sendRequestPane.getChildren().get(14);
        message = (TextArea) sendRequestPane.getChildren().get(15);

        requestButton = (Rectangle) sendRequestPane.getChildren().get(16);
        requestButtonText = (Label) sendRequestPane.getChildren().get(17);
        donateButton = (Rectangle) sendRequestPane.getChildren().get(18);
        donateButtonText = (Label) sendRequestPane.getChildren().get(19);
        setRequestButton();

        adjustWithScene();

        Scene scene = new Scene(sendRequestPane);
        stage.setScene(scene);
        scene.heightProperty().addListener((observable, oldValue, newValue) -> {
            adjustWithScene();
        });
        scene.widthProperty().addListener((observable, oldValue, newValue) -> {
            adjustWithScene();
        });
        stage.show();

    }

    private void setRequestButton() {
        EventHandler clickHandlerRequest = new EventHandler() {
            @Override
            public void handle(Event event) {
                // TODO : farnam
                amount.setText("0");
            }
        };
        requestButton.hoverProperty().addListener(event -> {
            requestButton.setOpacity(1.5 - requestButton.getOpacity());
        });
        requestButtonText.hoverProperty().addListener(event -> {
            requestButton.setOpacity(1.5 - requestButton.getOpacity());
        });
        requestButton.setOnMouseClicked(clickHandlerRequest);
        requestButtonText.setOnMouseClicked(clickHandlerRequest);
        EventHandler clickHandlerDonate = new EventHandler() {
            @Override
            public void handle(Event event) {
                // TODO : farnam
                amount.setText("0");
            }
        };
        donateButton.hoverProperty().addListener(event -> {
            donateButton.setOpacity(1.5 - donateButton.getOpacity());
        });
        donateButtonText.hoverProperty().addListener(event -> {
            donateButton.setOpacity(1.5 - donateButton.getOpacity());
        });
        donateButton.setOnMouseClicked(clickHandlerDonate);
        donateButtonText.setOnMouseClicked(clickHandlerDonate);
    }

    private void setAmount() {
        addButton.setFill(new ImagePattern(new Image(getClass().getResource("/icons/add.jpg").toExternalForm())));
        EventHandler clickHandlerAdd = new EventHandler() {
            @Override
            public void handle(Event event) {
                amount.setText(String.valueOf(Integer.parseInt(amount.getText()) + 1));
            }
        };
        addButton.setOnMouseClicked(clickHandlerAdd);
        reduceButton.setFill(new ImagePattern(new Image(getClass().getResource("/icons/reduce.png").toExternalForm())));
        EventHandler clickHandlerReduce = new EventHandler() {
            @Override
            public void handle(Event event) {
                amount.setText(String.valueOf(Integer.parseInt(amount.getText()) - 1));
            }
        };
        reduceButton.setOnMouseClicked(clickHandlerReduce);
    }

    private void setUsers() {
        users.getItems().addAll(controller.getGame().getCurrentMatch().getPlayers());
        // TODO : check for nonstring type in choicebox
        users.getSelectionModel().selectFirst();
    }

    private void setProperty() {
        property.getItems().addAll(Property.getAllProperties());
        // TODO : check for nonstring type in choicebox
        property.getSelectionModel().selectFirst();
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
        Adjust.adjustPane(sendRequestPane, ratioX, ratioY);
        Adjust.adjustRectangle(back, ratioX, ratioY);
        Adjust.adjustRectangle(sendRequestTitleBackground, ratioX, ratioY);
        Adjust.adjustLabel(sendRequestTitle, ratioX, ratioY);
        Adjust.adjustRectangle(partitionBackground, ratioX, ratioY);
        Adjust.adjustLabel(propertyText, ratioX, ratioY);
        Adjust.adjustLabel(userText, ratioX, ratioY);
        Adjust.adjustLabel(amountText, ratioX, ratioY);
        Adjust.adjustLabel(priceText, ratioX, ratioY);
        Adjust.adjustLabel(messageText, ratioX, ratioY);
        Adjust.adjustChoiceBox(property, ratioX, ratioY);
        Adjust.adjustChoiceBox(users, ratioX, ratioY);
        Adjust.adjustLabel(amount, ratioX, ratioY);
        Adjust.adjustCircle(addButton, ratioX, ratioY);
        Adjust.adjustCircle(reduceButton, ratioX, ratioY);
        Adjust.adjustTextField(price, ratioX, ratioY);
        Adjust.adjustTextArea(message, ratioX, ratioY);
        Adjust.adjustRectangle(requestButton, ratioX, ratioY);
        Adjust.adjustLabel(requestButtonText, ratioX, ratioY);
        Adjust.adjustRectangle(donateButton, ratioX, ratioY);
        Adjust.adjustLabel(donateButtonText, ratioX, ratioY);
    }

    @Override
    public void adjustWithScene() {
        adjust(stage.getScene().getWidth() / sendRequestPane.getPrefWidth(),
                stage.getScene().getHeight() / sendRequestPane.getPrefHeight());
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
