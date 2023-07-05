package view.Online;

import client.Client;
import controller.Controller;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.stage.Stage;
import model.Room;
import model.TextMessage;
import view.Adjust;
import view.Avatar;
import view.MenuJFX;

import java.util.ArrayList;
import java.util.Objects;

public class OnlineChatMenuJFX extends Application implements MenuJFX {
    private Controller controller;
    private AnchorPane chatMenuPane, roomsContent, chatContent;
    private ScrollPane roomsPane, chatPane;
    private TextField createRoomTextField, sendMessageTextField, addToRoomTextField;
    private Button createRoomButton, addToRoomButton, sendMessageButton, backButton;
    private Label chatStatus;
    private Room currentRoom;
    private Label selectedChat;
    private boolean isEditing = false;
    private Stage stage;

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        chatMenuPane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/OnlineChat.fxml")));
        setChatMenuProperties();

        roomsPane = (ScrollPane) chatMenuPane.getChildren().get(0);
        roomsContent = (AnchorPane) roomsPane.getContent();
        setRoomsPaneProperties();

        createRoomTextField = (TextField) chatMenuPane.getChildren().get(1);
        createRoomButton = (Button) chatMenuPane.getChildren().get(2);
        setCreateRoomProperties();

        chatPane = (ScrollPane) chatMenuPane.getChildren().get(3);
        chatContent = (AnchorPane) chatPane.getContent();
        setChatRoomProperties();

        sendMessageTextField = (TextField) chatMenuPane.getChildren().get(4);
        sendMessageButton = (Button) chatMenuPane.getChildren().get(5);
        chatStatus = (Label) chatMenuPane.getChildren().get(6);
        setSendMessageProperties();

        addToRoomTextField = (TextField) chatMenuPane.getChildren().get(7);
        addToRoomButton = (Button) chatMenuPane.getChildren().get(8);
        setAddToRoomProperties();

        backButton = (Button) chatMenuPane.getChildren().get(9);
        setBackButton();

        adjustWithScene();
        Scene scene = new Scene(chatMenuPane);
        stage.setScene(scene);
        scene.heightProperty().addListener((observable, oldValue, newValue) -> {
            adjustWithScene();
        });
        scene.widthProperty().addListener((observable, oldValue, newValue) -> {
            adjustWithScene();
        });
        this.stage = stage;
    }

    private void setChatMenuProperties() {
        chatMenuPane.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                switch (keyEvent.getCode()) {
                    case E:
                        isEditing = true;
                        break;
                    case D:
                        Client.deleteMessage(currentRoom.roomID,
                                currentRoom.messages.get((chatContent.getChildren().indexOf(selectedChat) - 1) / 2));
                        setChatRoomProperties();
                        break;
                }
            }
        });
    }

    private Label getLabel(String value) {
        Label label = new Label();
        label.setText(value);
        label.setPrefHeight(40);
        return label;
    }

    private void setRoomsPaneProperties() {
        roomsContent.getChildren().clear();
        int count = Client.rooms.size();
        roomsContent.setPrefHeight(count * 40);
        for (int i = 0; i < count; i++) {
            Label label = getLabel(Client.rooms.get(i).roomID);
            label.setLayoutX(0);
            label.setLayoutY(i * 40);
            int finalI = i;
            label.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    currentRoom = Client.rooms.get(finalI);
                    setChatRoomProperties();
                    chatStatus.setText("Chatting in " + currentRoom.roomID);
                    Client.seenMessage(currentRoom.roomID);
                }
            });
            roomsContent.getChildren().add(label);
        }
    }

    private void setCreateRoomProperties() {
        createRoomButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Client.newRoom(createRoomTextField.getText());
                setRoomsPaneProperties();
            }
        });
    }

    private void setChatRoomProperties() {
        chatContent.getChildren().clear();
        if (currentRoom != null) {
            ArrayList<ImagePattern> imagePatterns = new ArrayList<>();
            for (String user : currentRoom.users) {
                imagePatterns.add(new ImagePattern(new Image(controller.getGame().getUserByUsername(user).getAvatarUrl())));
            }
            int count = currentRoom.messages.size();
            for (int i = 0; i < count; i++) {
                Avatar avatar = new Avatar(Avatar.PROFILE_RADIUS, Avatar.PROFILE_RADIUS + i * (40 + Avatar.PROFILE_RADIUS * 2),
                        imagePatterns.get(currentRoom.users.indexOf(currentRoom.messages.get(i).username)));
                Label label = getLabel(currentRoom.messages.get(i).username + "|" +
                        currentRoom.messages.get(i).message + "|" +
                        currentRoom.messages.get(i).sentTime + "|" +
                        (currentRoom.messages.get(i).seen ? "." : "#"));
                label.setLayoutX(0);
                label.setLayoutY(i * (40 + Avatar.PROFILE_RADIUS * 2) + Avatar.PROFILE_RADIUS * 2);
                label.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        selectedChat = label;
                    }
                });
                chatContent.getChildren().add(avatar);
                chatContent.getChildren().add(label);
            }
        }
    }

    private void setSendMessageProperties() {
        sendMessageButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (!isEditing) Client.sendMessage(currentRoom.roomID, sendMessageTextField.getText());
                else {
                    Client.editMessage(currentRoom.roomID,
                            currentRoom.messages.get((chatContent.getChildren().indexOf(selectedChat) - 1) / 2),
                            selectedChat.getText());
                    isEditing = false;
                }
                setChatRoomProperties();
            }
        });
    }

    private void setAddToRoomProperties() {
        addToRoomButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (currentRoom != null) Client.addToRoom(currentRoom.roomID, addToRoomTextField.getText());
            }
        });
    }

    private void setBackButton() {
        backButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    controller.enterOnlineMenuJFX();
                    stop();
                    controller.getGame().getCurrentMenuJFX().start(stage);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void adjust(double ratioX, double ratioY) {
        Adjust.adjustPane(chatMenuPane, ratioX, ratioY);
        Adjust.adjustButton(createRoomButton, ratioX, ratioY);
        Adjust.adjustButton(sendMessageButton, ratioX, ratioY);
        Adjust.adjustButton(addToRoomButton, ratioX, ratioY);
        Adjust.adjustButton(backButton, ratioX, ratioY);
        Adjust.adjustTextField(createRoomTextField, ratioX, ratioY);
        Adjust.adjustTextField(sendMessageTextField, ratioX, ratioY);
        Adjust.adjustTextField(addToRoomTextField, ratioX, ratioY);
        Adjust.adjustLabel(chatStatus, ratioX, ratioY);
        Adjust.adjustScrollPane(roomsPane, ratioX, ratioY);
        Adjust.adjustScrollPane(chatPane, ratioX, ratioY);
        Adjust.adjustPane(chatContent, ratioX, ratioY);
        Adjust.adjustPane(roomsContent, ratioX, ratioY);
        for (Node child : chatContent.getChildren()) {
            if (child instanceof Avatar)
                Adjust.adjustCircle((Avatar) child, ratioX, ratioY);
            else if (child instanceof Label)
                Adjust.adjustLabel((Label) child, ratioX, ratioY);
        }
        for (Node child : roomsContent.getChildren()) {
            if (child instanceof Label)
                Adjust.adjustLabel((Label) child, ratioX, ratioY);
            else if (child instanceof Avatar)
                Adjust.adjustCircle((Avatar) child, ratioX, ratioY);
        }
    }

    @Override
    public void adjustWithScene() {
        adjust(stage.getScene().getWidth() / chatMenuPane.getPrefWidth(), stage.getScene().getHeight() / chatMenuPane.getPrefHeight());
    }
}
