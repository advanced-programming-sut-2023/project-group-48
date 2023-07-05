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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import model.Game;
import model.User;
import view.Adjust;
import view.Avatar;
import view.MenuJFX;

import java.io.IOException;
import java.util.Objects;

public class OnlineFriendMenuJFX extends Application implements MenuJFX {
    private static final ImagePattern acceptImagePattern = new ImagePattern(new Image(Objects.requireNonNull(Game.class.getResource("/icons/accept.png")).toExternalForm()));
    private static final ImagePattern rejectImagePattern = new ImagePattern(new Image(Objects.requireNonNull(Game.class.getResource("/icons/reject.png")).toExternalForm()));
    private Controller controller;
    private AnchorPane friendMenuPane, friendsContent, searchedUserPane, requestsContent;
    private ScrollPane friendsScrollPane, requestScrollPane;
    private TextField userSearchTextField;
    private Button userSearchButton, backButton, sendRequestButton;
    private Label friendsTitleLabel, searchedUserInfoLabel, requestTitleLabel;
    private Stage stage;

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        friendMenuPane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/OnlineFriend.fxml")));
        friendMenuPane.setBackground(Background.fill(new ImagePattern(new Image(getClass().getResource("/backgrounds/6.jpg").toExternalForm()))));

        userSearchTextField = (TextField) friendMenuPane.getChildren().get(0);
        userSearchButton = (Button) friendMenuPane.getChildren().get(1);
        searchedUserPane = (AnchorPane) friendMenuPane.getChildren().get(2);
        searchedUserInfoLabel = (Label) searchedUserPane.getChildren().get(0);
        setSearchProperties();

        friendsTitleLabel = (Label) friendMenuPane.getChildren().get(3);

        friendsScrollPane = (ScrollPane) friendMenuPane.getChildren().get(4);
        friendsContent = (AnchorPane) friendsScrollPane.getContent();
        setFriendsProperties();

        requestTitleLabel = (Label) friendMenuPane.getChildren().get(5);
        requestScrollPane = (ScrollPane) friendMenuPane.getChildren().get(6);
        requestsContent = (AnchorPane) requestScrollPane.getContent();
        setRequestsProperties();

        backButton = (Button) friendMenuPane.getChildren().get(7);
        setBackButton();

        sendRequestButton = (Button) friendMenuPane.getChildren().get(8);
        setSendRequestButton();

        adjustWithScene();
        Scene scene = new Scene(friendMenuPane);
        stage.setScene(scene);
        scene.heightProperty().addListener((observable, oldValue, newValue) -> {
            adjustWithScene();
        });
        scene.widthProperty().addListener((observable, oldValue, newValue) -> {
            adjustWithScene();
        });
        stage.show();
    }

    private void setSendRequestButton() {
        sendRequestButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Client.setFriendRequest(userSearchTextField.getText());
            }
        });
    }

    private void setSearchProperties() {
        Avatar searchedUserAvatar = new Avatar(searchedUserPane.getPrefWidth() / 2, searchedUserPane.getPrefHeight() * 3 / 4, null);
        searchedUserPane.getChildren().add(searchedUserAvatar);
        userSearchButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                User searchedUser = controller.getGame().getUserByUsername(userSearchTextField.getText());
                if (searchedUser == null) {
                    searchedUserInfoLabel.setText("User not found!");
                    searchedUserAvatar.setImagePattern(null);
                } else {
                    searchedUserInfoLabel.setText(searchedUser.getUsername() + " " + searchedUser.lastSeen);
                    searchedUserAvatar.setImagePattern(new ImagePattern(new Image(searchedUser.getAvatarUrl())));
                }
            }
        });
    }

    private void setRequestsProperties() {
        requestsContent.getChildren().clear();
        requestsContent.setPrefHeight(Client.friendRequests.size() * 40);
        for (int i = 0; i < Client.friendRequests.size(); i++) {
            User friendRequest = Client.friendRequests.get(i);
            Label friendRequestLabel = new Label(friendRequest.getUsername());
            friendRequestLabel.setTextAlignment(TextAlignment.CENTER);
            friendRequestLabel.setPrefWidth(400);
            friendRequestLabel.setPrefHeight(40);
            friendRequestLabel.setLayoutY(Client.friendRequests.indexOf(friendRequest) * 40);
            friendRequestLabel.setLayoutX(0);
            requestsContent.getChildren().add(friendRequestLabel);
            Circle rejectCircle = new Circle(friendRequestLabel.getPrefWidth() + friendRequestLabel.getLayoutX() -25,
                    Client.friendRequests.indexOf(friendRequest) * 40 + 20, 20, rejectImagePattern);
            int finalI = i;
            rejectCircle.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    System.out.println("reject");
                    Client.answerFriendRequest(Client.friendRequests.get(finalI).getUsername(), false);
                    setRequestsProperties();
                    setFriendsProperties();
                }
            });
            Circle acceptCircle = new Circle(friendRequestLabel.getPrefWidth() + friendRequestLabel.getLayoutX() + 25,
                    Client.friendRequests.indexOf(friendRequest) * 40 + 20, 20, acceptImagePattern);
            acceptCircle.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    System.out.println("accept");
                    Client.answerFriendRequest(Client.friendRequests.get(finalI).getUsername(), true);
                    setRequestsProperties();
                    setFriendsProperties();
                }
            });
            requestsContent.getChildren().addAll(rejectCircle, acceptCircle);
        }
    }

    private void setFriendsProperties() {
        friendsContent.getChildren().clear();
        friendsContent.setPrefHeight(Client.friends.size() * 40);
        for (User friend : Client.friends) {
            Label friendLabel = new Label(friend.getUsername());
            friendLabel.setTextAlignment(TextAlignment.CENTER);
            friendLabel.setPrefWidth(500);
            friendLabel.setPrefHeight(40);
            friendLabel.setLayoutY(Client.friends.indexOf(friend) * 40);
            friendLabel.setLayoutX(0);
            friendsContent.getChildren().add(friendLabel);
        }
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
        Adjust.adjustPane(friendMenuPane, ratioX, ratioY);
        Adjust.adjustPane(friendsContent, ratioX, ratioY);
        Adjust.adjustPane(requestsContent, ratioX, ratioY);
        Adjust.adjustPane(searchedUserPane, ratioX, ratioY);
        Adjust.adjustButton(userSearchButton, ratioX, ratioY);
        Adjust.adjustButton(backButton, ratioX, ratioY);
        Adjust.adjustLabel(friendsTitleLabel, ratioX, ratioY);
        Adjust.adjustLabel(requestTitleLabel, ratioX, ratioY);
        Adjust.adjustLabel(searchedUserInfoLabel, ratioX, ratioY);
        Adjust.adjustTextField(userSearchTextField, ratioX, ratioY);
        Adjust.adjustScrollPane(friendsScrollPane, ratioX, ratioY);
        Adjust.adjustScrollPane(requestScrollPane, ratioX, ratioY);
        for (Node child : friendsContent.getChildren()) {
            if (child instanceof Label)
                Adjust.adjustLabel((Label) child, ratioX, ratioY);
            else if (child instanceof Avatar)
                Adjust.adjustCircle((Avatar) child, ratioX, ratioY);
        }
        for (Node child : requestsContent.getChildren()) {
            if (child instanceof Label)
                Adjust.adjustLabel((Label) child, ratioX, ratioY);
            else if (child instanceof Avatar)
                Adjust.adjustCircle((Avatar) child, ratioX, ratioY);
        }
    }

    @Override
    public void adjustWithScene() {
        adjust(stage.getScene().getWidth() / friendMenuPane.getPrefWidth(),
                stage.getScene().getHeight() / friendMenuPane.getPrefHeight());
    }
}
