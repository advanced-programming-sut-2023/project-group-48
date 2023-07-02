package view;

import controller.Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Game;
import model.ProfilePicture;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class ProfileMenu extends Application {
    private Controller controller;
    private Avatar selectedProfilePicture;
    private Avatar currentProfilePicture;
    private TextField usernameTextField, oldPasswordTextField, newPasswordTextField, nicknameTextField, emailTextField, sloganTextField;
    private PasswordField oldPasswordPasswordField, newPasswordPasswordField;
    private Label titleLabel, infoLabel, usernameLabel, usernameErrorLabel, passwordErrorLabel, nicknameLabel, nicknameErrorLabel, emailLabel, emailErrorLabel, sloganLabel, sloganErrorLabel, profilePictureErrorLabel;
    private Separator[] separators;
    private Button changeUsernameButton, changePasswordButton, changeNicknameButton, changeEmailButton, changeSloganButton, changeProfilePictureButton, backButton;
    private ScrollPane avatarsPane;
    private ImageView avatarImageView;
    private Pane currentProfilePane;
    private ChoiceBox<String> sloganChoiceBox;
    private CheckBox customSloganCheckBox;
    private Circle deleteSloganButton;
    private Stage stage;

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        AnchorPane profileMenuPane = FXMLLoader.load(new URL(Objects.requireNonNull(Game.class.getResource("/fxml/ProfileMenu.fxml")).toExternalForm()));
        profileMenuPane.setBackground(Background.fill(new ImagePattern(new Image(getClass().getResource("/backgrounds/5.png").toExternalForm()))));

        infoLabel = (Label) profileMenuPane.getChildren().get(1);
//        setInfoProperties();

        usernameLabel = (Label) profileMenuPane.getChildren().get(3);
        usernameTextField = (TextField) profileMenuPane.getChildren().get(4);
        changeUsernameButton = (Button) profileMenuPane.getChildren().get(5);
        usernameErrorLabel = (Label) profileMenuPane.getChildren().get(6);
//        setUsernameProperties();

        passwordErrorLabel = (Label) profileMenuPane.getChildren().get(8);
        oldPasswordTextField = (TextField) profileMenuPane.getChildren().get(9);
        newPasswordTextField = (TextField) profileMenuPane.getChildren().get(10);
        oldPasswordPasswordField = (PasswordField) profileMenuPane.getChildren().get(1);
        newPasswordPasswordField = (PasswordField) profileMenuPane.getChildren().get(12);
        changePasswordButton = (Button) profileMenuPane.getChildren().get(13);
//        setPasswordProperties();

        backButton = (Button) profileMenuPane.getChildren().get(15);
//        setBackButtonProperties();

        nicknameLabel = (Label) profileMenuPane.getChildren().get(17);
        nicknameTextField = (TextField) profileMenuPane.getChildren().get(18);
        changeNicknameButton = (Button) profileMenuPane.getChildren().get(19);
        nicknameErrorLabel = (Label) profileMenuPane.getChildren().get(20);
//        setNicknameProperties();


        emailLabel = (Label) profileMenuPane.getChildren().get(22);
        emailTextField = (TextField) profileMenuPane.getChildren().get(23);
        changeEmailButton = (Button) profileMenuPane.getChildren().get(24);
        emailErrorLabel = (Label) profileMenuPane.getChildren().get(25);
//        setEmailProperties();

        nicknameLabel = (Label) profileMenuPane.getChildren().get(27);
        nicknameTextField = (TextField) profileMenuPane.getChildren().get(28);
        sloganChoiceBox = (ChoiceBox<String>) profileMenuPane.getChildren().get(29);
        changeNicknameButton = (Button) profileMenuPane.getChildren().get(30);
        nicknameErrorLabel = (Label) profileMenuPane.getChildren().get(31);
        deleteSloganButton = (Circle) profileMenuPane.getChildren().get(32);
        customSloganCheckBox = (CheckBox) profileMenuPane.getChildren().get(33);
//        setSloganProperties();


        avatarImageView = (ImageView) profileMenuPane.getChildren().get(35);
        avatarsPane = (ScrollPane) profileMenuPane.getChildren().get(36);
        currentProfilePane = (Pane) profileMenuPane.getChildren().get(37);
        changeProfilePictureButton = (Button) profileMenuPane.getChildren().get(38);
        profilePictureErrorLabel = (Label) profileMenuPane.getChildren().get(39);
//        setAvatarProperties();
        File profileDirectory = new File(new URL(Objects.requireNonNull(Game.class.getResource("/profile pictures")).toExternalForm()).toURI());
        int defaultProfilesCount = Objects.requireNonNull(profileDirectory.listFiles()).length - 3;
        addProfilePictures(avatarsPane, defaultProfilesCount);

        currentProfilePane = (Pane) profileMenuPane.getChildren().get(1);
        currentProfilePane.getChildren().add(new Avatar(currentProfilePane.getPrefWidth() / 2, currentProfilePane.getPrefHeight() / 2, controller.getGame().getCurrentUser().getAvatar()));

        profilePictureErrorLabel = (Label) rightVBox.getChildren().get(3);

        Button changeProfileButton = (Button) rightVBox.getChildren().get(2);
        changeProfileButton.setOnMouseClicked(event -> {
            if (selectedProfilePicture == null) {
                profilePictureErrorLabel.setText("Please select a profile picture!");
            } else {
                String result = controller.getProfileMenuController().changeAvatar(selectedProfilePicture.getImagePattern());
                profilePictureErrorLabel.setText(result);
                profilePictureErrorLabel.setTextFill(Color.GREEN);
                currentProfilePane.getChildren().clear();
                currentProfilePicture = new Avatar(currentProfilePane.getPrefWidth() / 2, currentProfilePane.getPrefHeight() / 2, controller.getGame().getCurrentUser().getAvatar());
                currentProfilePane.getChildren().add(currentProfilePicture);
            }
        });

        Label scoreLabel = (Label) leftVBox.getChildren().get(0);
        scoreLabel.setText("Time: " + controller.getGame().getCurrentUser().getSec1() + " sec | " +
                controller.getGame().getCurrentUser().getSec2() + " sec | " + controller.getGame().getCurrentUser().getSec3() + " sec");

        usernameLabel = (Label) leftVBox.getChildren().get(2);
        usernameLabel.setText("Username: " + controller.getGame().getCurrentUser().getUsername());

        TextField newUsername = (TextField) leftVBox.getChildren().get(3);

        usernameErrorLabel = (Label) leftVBox.getChildren().get(5);
        Button changeUsernameButton = (Button) leftVBox.getChildren().get(4);
        changeUsernameButton.setOnMouseClicked(event -> {
            String result = controller.getProfileMenuController().changeUsername(newUsername.getText());
            if (!result.endsWith("changed successfully!")) {
                usernameErrorLabel.setText(result);
            } else {
                usernameLabel.setText("Username: " + controller.getGame().getCurrentUser().getUsername());
                usernameErrorLabel.setText(result);
                usernameErrorLabel.setTextFill(Color.GREEN);
            }
        });

        passwordErrorLabel = (Label) leftVBox.getChildren().get(7);

        TextField oldPassword = (TextField) leftVBox.getChildren().get(8);
        TextField newPassword = (TextField) leftVBox.getChildren().get(9);

        Button changePasswordButton = (Button) leftVBox.getChildren().get(10);
        changePasswordButton.setOnMouseClicked(event -> {
            String result = controller.getProfileMenuController().changePassword(oldPassword.getText(), newPassword.getText());
            if (!result.endsWith("successfully!")) {
                passwordErrorLabel.setText(result);
                passwordErrorLabel.setTextFill(Color.RED);
            } else {
                passwordErrorLabel.setText(result);
                passwordErrorLabel.setTextFill(Color.GREEN);
            }
        });

        Button backButton = (Button) leftVBox.getChildren().get(12);
        backButton.setOnMouseClicked(event -> {
            try {
                controller.getMainMenuController().getMainMenu().start(stage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        Scene scene = new Scene(profileMenuPane);
        stage.setScene(scene);
        stage.show();
    }

    public void addProfilePictures(ScrollPane scrollPane, int profilesCount) {
        ArrayList<Avatar> profilePictures = new ArrayList<>();
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setPrefWidth(scrollPane.getPrefWidth());
        anchorPane.setPrefHeight(scrollPane.getPrefHeight());
        ImagePattern imagePattern;
        final int[] profileX = {40};
        final int[] profileY = {40};
        Avatar profile;
        for (int i = 0; i < profilesCount; i++) {
            imagePattern = new ImagePattern(new Image(Objects.requireNonNull(Game.class.getResource("/profile pictures/" + (i + 1) + ".jpg").toExternalForm())));
            profile = createProfilePicture(scrollPane, profileX, profileY, imagePattern);
            makeSelectable(profile);
            profilePictures.add(profile);
            anchorPane.getChildren().add(profile);
        }

        imagePattern = new ImagePattern(new Image(Objects.requireNonNull(Game.class.getResource("/profile pictures/Random.jpg").toExternalForm())));
        profile = createProfilePicture(scrollPane, profileX, profileY, imagePattern);
        profile.setOnMouseClicked(event -> {
            selectProfilePicture(profilePictures.get(new Random().nextInt(profilePictures.size())));
        });
        anchorPane.getChildren().add(profile);

        imagePattern = new ImagePattern(new Image(Objects.requireNonNull(Game.class.getResource("/profile pictures/Custom.jpg").toExternalForm())));
        profile = createProfilePicture(scrollPane, profileX, profileY, imagePattern);
        profile.setOnMouseClicked(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Choose Profile Picture");
            File file = fileChooser.showOpenDialog(stage);
            if (file != null && file.getPath().endsWith(".jpg")) {
                Avatar newProfilePicture = createProfilePicture(scrollPane, profileX, profileY, new ImagePattern(new Image(file.toURI().toString())));
                makeSelectable(newProfilePicture);
                selectProfilePicture(newProfilePicture);
                anchorPane.getChildren().add(newProfilePicture);
            } else {
                profilePictureErrorLabel.setText("Invalid file format!");
                profilePictureErrorLabel.setVisible(true);
            }
        });
        anchorPane.getChildren().add(profile);

        scrollPane.setContent(anchorPane);
    }

    private void selectProfilePicture(Avatar profilePicture) {
        if (selectedProfilePicture != null) selectedProfilePicture.setStroke(Color.BLACK);
        selectedProfilePicture = profilePicture;
        selectedProfilePicture.setStroke(Color.GREEN);
    }

    private Avatar createProfilePicture(ScrollPane scrollPane, int[] profileX, int[] profileY, ImagePattern imagePattern) {
        Avatar profilePicture = new Avatar(profileX[0], profileY[0], imagePattern);
        profileX[0] = (scrollPane.getPrefWidth() - profileX[0]) < 110 ? 40 : profileX[0] + 70;
        profileY[0] = profileX[0] == 40 ? profileY[0] + 70 : profileY[0];
        return profilePicture;
    }

    private void makeSelectable(Avatar profilePicture) {
        profilePicture.setOnMouseClicked(event -> {
            selectProfilePicture(profilePicture);
        });
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }
}
