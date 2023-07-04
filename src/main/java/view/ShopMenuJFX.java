package view;

import controller.Controller;
import controller.ShopMenuController;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import model.Match.Property;

import java.util.Objects;

public class ShopMenuJFX extends Application implements MenuJFX {

    private AnchorPane shopMenuPane;
    private Controller controller;
    private ShopMenuController shopMenuController;
    private Rectangle back, apply, buyButton, sellButton, reset, background, tradeMenu;
    private Label applyText, resetText, propertyTitle, stockTitle, buyTitle, sellTitle, priceTitle, shopTitle, stock, buyNumber, sellNumber, price, tradeMenuText;
    private ChoiceBox property;
    private Stage stage;

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        shopMenuPane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/ShopMenu.fxml")));
        shopMenuPane.setBackground(new Background(new BackgroundImage(new Image(getClass().getResource("/backgrounds/7.png").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        shopMenuPane.setOnMouseClicked(new EventHandler() {
            @Override
            public void handle(Event event) {
                shopMenuPane.requestFocus();
            }
        });

        shopTitle = (Label) shopMenuPane.getChildren().get(0);
        back = (Rectangle) shopMenuPane.getChildren().get(1);
        setBackButton();

        background = (Rectangle) shopMenuPane.getChildren().get(2);
        background.setFill(Color.WHITE);

        propertyTitle = (Label) shopMenuPane.getChildren().get(3);
        stockTitle = (Label) shopMenuPane.getChildren().get(4);
        buyTitle = (Label) shopMenuPane.getChildren().get(5);
        sellTitle = (Label) shopMenuPane.getChildren().get(6);
        priceTitle = (Label) shopMenuPane.getChildren().get(7);

        apply = (Rectangle) shopMenuPane.getChildren().get(8);
        applyText = (Label) shopMenuPane.getChildren().get(9);
        reset = (Rectangle) shopMenuPane.getChildren().get(10);
        resetText = (Label) shopMenuPane.getChildren().get(11);
        setApplyAndResetButton();

        property = (ChoiceBox) shopMenuPane.getChildren().get(12);
        setProperty();

        stock = (Label) shopMenuPane.getChildren().get(13);
        setStock();

        buyNumber = (Label) shopMenuPane.getChildren().get(14);
        buyButton = (Rectangle) shopMenuPane.getChildren().get(15);
        sellNumber = (Label) shopMenuPane.getChildren().get(16);
        sellButton = (Rectangle) shopMenuPane.getChildren().get(17);
        setBuyAndSellButtons();

        price = (Label) shopMenuPane.getChildren().get(18);
        setPrice();

        tradeMenu = (Rectangle) shopMenuPane.getChildren().get(19);
        tradeMenuText = (Label) shopMenuPane.getChildren().get(20);
        setTradeMenuButton();

        adjustWithScene();

        Scene scene = new Scene(shopMenuPane);
        stage.setScene(scene);
        scene.heightProperty().addListener((observable, oldValue, newValue) -> {
            adjustWithScene();
        });
        scene.widthProperty().addListener((observable, oldValue, newValue) -> {
            adjustWithScene();
        });
        stage.show();

    }

    private void setTradeMenuButton() {
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
        tradeMenu.hoverProperty().addListener(event -> {
            tradeMenu.setOpacity(1.5 - tradeMenu.getOpacity());
        });
        tradeMenuText.hoverProperty().addListener(event -> {
            tradeMenu.setOpacity(1.5 - tradeMenu.getOpacity());
        });
        tradeMenu.setOnMouseClicked(clickHandler);
        tradeMenuText.setOnMouseClicked(clickHandler);
    }

    private void setPrice() {
        property.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observableValue, Object o, Object t1) {
                price.setText(String.valueOf((Integer.parseInt(buyNumber.getText()) - (Integer.parseInt(sellNumber.getText()) / 2)) * controller.getGame().getCurrentMatch().getShop().getPrices().get(Property.getProperty(property.getSelectionModel().getSelectedItem().toString()))));
            }
        });
    }

    private void setBuyAndSellButtons() {
        buyButton.setFill(new ImagePattern(new Image(getClass().getResource("/icons/buy.jpg").toExternalForm())));
        EventHandler clickHandlerBuy = new EventHandler() {
            @Override
            public void handle(Event event) {
                buyNumber.setText(String.valueOf(Integer.parseInt(buyNumber.getText()) + 1));
            }
        };
        buyButton.setOnMouseClicked(clickHandlerBuy);
        sellButton.setFill(new ImagePattern(new Image(getClass().getResource("/icons/sell.png").toExternalForm())));
        EventHandler clickHandlerSell = new EventHandler() {
            @Override
            public void handle(Event event) {
                sellNumber.setText(String.valueOf(Integer.parseInt(sellNumber.getText()) + 1));
            }
        };
        sellButton.setOnMouseClicked(clickHandlerSell);
    }

    private void setStock() {
        property.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observableValue, Object o, Object t1) {
                stock.setText(String.valueOf(controller.getGame().getCurrentMatch().getCurrentPlayer().getGovernance().getPropertyCount(Property.getProperty(property.getSelectionModel().getSelectedItem().toString()))));
            }
        });
    }

    private void setProperty() {
        property.getItems().addAll(controller.getGame().getCurrentMatch().getShop().getProducts());
        property.getSelectionModel().selectFirst();
    }

    private void setApplyAndResetButton() {
        EventHandler clickHandlerApply = new EventHandler() {
            @Override
            public void handle(Event event) {
                if (Integer.parseInt(buyNumber.getText()) > 0 && Integer.parseInt(sellNumber.getText()) > 0){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("the operation is failed!");
                    alert.setContentText("you cannot buy and sell products concurrently!");
                    alert.showAndWait();
                }
                else if (Integer.parseInt(buyNumber.getText()) == 0 && Integer.parseInt(sellNumber.getText()) == 0){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("the operation is failed!");
                    alert.setContentText("you should choose an amount!");
                    alert.showAndWait();
                }
                else if (Integer.parseInt(buyNumber.getText()) > 0){
                    String result = shopMenuController.buy(property.getSelectionModel().getSelectedItem().toString(), Integer.parseInt(buyNumber.getText()));
                    if (!result.startsWith("You bought")){
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setHeaderText("the operation is failed!");
                        alert.setContentText(result);
                        alert.showAndWait();
                    }
                    else {
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setHeaderText("the operation is successful!");
                        alert.setContentText(result);
                        alert.showAndWait();
                    }
                }
                else {
                    String result = shopMenuController.sell(property.getSelectionModel().getSelectedItem().toString(), Integer.parseInt(buyNumber.getText()));
                    if (!result.startsWith("You sold")){
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setHeaderText("the operation is failed!");
                        alert.setContentText(result);
                        alert.showAndWait();
                    }
                    else {
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setHeaderText("the operation is successful!");
                        alert.setContentText(result);
                        alert.showAndWait();
                    }
                }
                buyNumber.setText("0");
                sellNumber.setText("0");
                price.setText("00");
            }
        };
        apply.hoverProperty().addListener(event -> {
            apply.setOpacity(1.5 - apply.getOpacity());
        });
        applyText.hoverProperty().addListener(event -> {
            apply.setOpacity(1.5 - apply.getOpacity());
        });
        apply.setOnMouseClicked(clickHandlerApply);
        applyText.setOnMouseClicked(clickHandlerApply);
        EventHandler clickHandlerReset = new EventHandler() {
            @Override
            public void handle(Event event) {
                buyNumber.setText("0");
                sellNumber.setText("0");
                price.setText("00");
            }
        };
        reset.hoverProperty().addListener(event -> {
            reset.setOpacity(1.5 - reset.getOpacity());
        });
        resetText.hoverProperty().addListener(event -> {
            reset.setOpacity(1.5 - reset.getOpacity());
        });
        reset.setOnMouseClicked(clickHandlerReset);
        resetText.setOnMouseClicked(clickHandlerReset);
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

    @Override
    public void adjust(double ratioX, double ratioY) {
        Adjust.adjustPane(shopMenuPane, ratioX, ratioY);
        Adjust.adjustLabel(shopTitle, ratioX, ratioY);
        Adjust.adjustRectangle(back, ratioX, ratioY);
        Adjust.adjustRectangle(background, ratioX, ratioY);
        Adjust.adjustLabel(propertyTitle, ratioX, ratioY);
        Adjust.adjustLabel(stockTitle, ratioX, ratioY);
        Adjust.adjustLabel(buyTitle, ratioX, ratioY);
        Adjust.adjustLabel(sellTitle, ratioX, ratioY);
        Adjust.adjustLabel(priceTitle, ratioX, ratioY);
        Adjust.adjustRectangle(apply, ratioX, ratioY);
        Adjust.adjustLabel(applyText, ratioX, ratioY);
        Adjust.adjustRectangle(reset, ratioX, ratioY);
        Adjust.adjustLabel(resetText,ratioX, ratioY);
        Adjust.adjustChoiceBox(property, ratioX, ratioY);
        Adjust.adjustLabel(stock, ratioX, ratioY);
        Adjust.adjustRectangle(buyButton, ratioX, ratioY);
        Adjust.adjustLabel(buyNumber, ratioX, ratioY);
        Adjust.adjustRectangle(sellButton, ratioX, ratioY);
        Adjust.adjustLabel(sellNumber, ratioX, ratioY);
        Adjust.adjustLabel(price, ratioX, ratioY);
    }

    @Override
    public void adjustWithScene() {
        adjust(stage.getScene().getWidth() / shopMenuPane.getPrefWidth(),
                stage.getScene().getHeight() / shopMenuPane.getPrefHeight());
    }


    public ShopMenuController getShopMenuController() {
        return shopMenuController;
    }

    public void setShopMenuController(ShopMenuController shopMenuController) {
        this.shopMenuController = shopMenuController;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }
}
