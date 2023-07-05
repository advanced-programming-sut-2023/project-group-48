package view;

import controller.Controller;
import controller.ProfileMenuController;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.paint.ImagePattern;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import model.Game;
import model.User;

import java.util.ArrayList;
import java.util.Objects;

public class ScoreBoardJFX extends Application implements MenuJFX {
    private Controller controller;
    private ProfileMenuController profileMenuController;
    private AnchorPane scoreBoardPane;
    private ScrollPane scoreBoardScrollPane;
    private AnchorPane anchorPane;
    private Button backButton;
    private ArrayList<Avatar> avatars;
    private ArrayList<User> scoreBoard;
    private boolean isOffline;
    private Stage stage;

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        scoreBoardPane = FXMLLoader.load(Objects.requireNonNull(Game.class.getResource("/fxml/ScoreBoard.fxml")));
        scoreBoardPane.setBackground(Background.fill(new ImagePattern(new Image(getClass().getResource("/backgrounds/7.png").toExternalForm()))));

        scoreBoardScrollPane = (ScrollPane) scoreBoardPane.getChildren().get(0);
        setScoreBoard();

        backButton = (Button) scoreBoardPane.getChildren().get(1);
        setBackButton();

        adjustWithScene();
        Scene scene = new Scene(scoreBoardPane);
        stage.setScene(scene);
        scene.heightProperty().addListener((observable, oldValue, newValue) -> {
            adjustWithScene();
        });
        scene.widthProperty().addListener((observable, oldValue, newValue) -> {
            adjustWithScene();
        });
        stage.show();
    }

    private void setScoreBoard() {
        scoreBoard = profileMenuController.getScoreBoard();
        avatars = new ArrayList<>();
        anchorPane = (AnchorPane) scoreBoardScrollPane.getContent();
        anchorPane.setPrefHeight(Avatar.PROFILE_RADIUS * (1.5 * scoreBoard.size() + 1));
        for (int i = 0; i < scoreBoard.size(); i++) {
            User user = scoreBoard.get(i);
            Avatar avatar = new Avatar(Avatar.PROFILE_RADIUS * 2, Avatar.PROFILE_RADIUS * (i * 3 + 2),
                    new ImagePattern(new Image(user.getAvatarUrl())));
            avatars.add(avatar);
            Label usernameLabel = getLabel(Avatar.PROFILE_RADIUS * 4, Avatar.PROFILE_RADIUS * (i * 3 + 2),
                    user.getUsername());
            Label highScoreLabel = getLabel(usernameLabel.getLayoutX() + usernameLabel.getPrefWidth() + Avatar.PROFILE_RADIUS,
                    Avatar.PROFILE_RADIUS * (i * 3 + 2), String.valueOf(user.getHighScore()));
            Label lastSeenLabel = null;
            if (!isOffline) {
                System.out.println("last seen: " + user.lastSeen);
                lastSeenLabel = getLabel(highScoreLabel.getLayoutX() + highScoreLabel.getPrefWidth() + Avatar.PROFILE_RADIUS,
                        Avatar.PROFILE_RADIUS * (i * 3 + 2), String.valueOf(user.lastSeen));
            }
            anchorPane.getChildren().add(avatar);
            anchorPane.getChildren().add(usernameLabel);
            anchorPane.getChildren().add(highScoreLabel);
            if (!isOffline)
                anchorPane.getChildren().add(lastSeenLabel);
        }
        for (Avatar avatar : avatars) {
            makeAvatarClickable(avatar);
        }
    }

    private void makeAvatarClickable(Avatar avatar) {
        avatar.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                profileMenuController.changeAvatar(avatar.getImagePattern());
                avatars.get(scoreBoard.indexOf(profileMenuController.getCurrentUser())).setImagePattern(avatar.getImagePattern());
            }
        });
    }

    private Label getLabel(double x, int y, String value) {
        Label label = new Label();
        label.setText(value);
        double width = (scoreBoardScrollPane.getPrefWidth() - Avatar.PROFILE_RADIUS * (isOffline ? 6 : 7)) / (isOffline ? 2 : 3);
        label.setPrefWidth(width);
        label.setPrefHeight(Avatar.PROFILE_RADIUS * 2);
        label.setLayoutX(x);
        label.setLayoutY(y - label.getPrefHeight() / 2);
        label.setTextAlignment(TextAlignment.CENTER);
        label.setFont(Font.font(18));
        return label;
    }

    private void setBackButton() {
        backButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    if (isOffline)controller.enterProfileMenuJFX();
                    else controller.enterOnlineMenuJFX();
                    stop();
                    controller.getGame().getCurrentMenuJFX().start(stage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void setProfileMenuController(ProfileMenuController profileMenuController) {
        this.profileMenuController = profileMenuController;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void adjust(double ratioX, double ratioY) {
        Adjust.adjustPane(scoreBoardPane, ratioX, ratioY);
        Adjust.adjustScrollPane(scoreBoardScrollPane, ratioX, ratioY);
        Adjust.adjustButton(backButton, ratioX, ratioY);
        for (Node child : anchorPane.getChildren()) {
            if (child instanceof Avatar)
                Adjust.adjustCircle((Avatar) child, ratioX, ratioY);
            else if (child instanceof Label)
                Adjust.adjustLabel((Label) child, ratioX, ratioY);
        }
    }

    @Override
    public void adjustWithScene() {
        adjust(stage.getScene().getWidth() / scoreBoardPane.getPrefWidth(),
                stage.getScene().getHeight() / scoreBoardPane.getPrefHeight());
    }

    public boolean isOffline() {
        return isOffline;
    }

    public void setOffline(boolean offline) {
        isOffline = offline;
    }
}