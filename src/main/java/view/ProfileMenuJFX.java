package view;

import controller.Controller;
import controller.ProfileMenuController;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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


import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
import java.util.regex.Pattern;

public class ProfileMenuJFX extends Application {
    private Controller controller;
    private ProfileMenuController profileMenuController;
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
    private CheckBox showPasswordCheckBox, customSloganCheckBox;
    private Circle deleteSloganButton;
    private Stage stage;

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        AnchorPane profileMenuPane = FXMLLoader.load(Objects.requireNonNull(Game.class.getResource("/fxml/ProfileMenu.fxml")));
        profileMenuPane.setBackground(Background.fill(new ImagePattern(new Image(getClass().getResource("/backgrounds/5.png").toExternalForm()))));

        infoLabel = (Label) profileMenuPane.getChildren().get(1);
//        setInfoProperties();

        usernameLabel = (Label) profileMenuPane.getChildren().get(3);
        usernameTextField = (TextField) profileMenuPane.getChildren().get(4);
        changeUsernameButton = (Button) profileMenuPane.getChildren().get(5);
        usernameErrorLabel = (Label) profileMenuPane.getChildren().get(6);
        setUsernameProperties();

        passwordErrorLabel = (Label) profileMenuPane.getChildren().get(8);
        oldPasswordTextField = (TextField) profileMenuPane.getChildren().get(9);
        newPasswordTextField = (TextField) profileMenuPane.getChildren().get(10);
        oldPasswordPasswordField = (PasswordField) profileMenuPane.getChildren().get(1);
        newPasswordPasswordField = (PasswordField) profileMenuPane.getChildren().get(12);
        changePasswordButton = (Button) profileMenuPane.getChildren().get(13);
        showPasswordCheckBox = (CheckBox) profileMenuPane.getChildren().get(14);
        setPasswordProperties();

        backButton = (Button) profileMenuPane.getChildren().get(16);
        setBackButtonProperties();

        nicknameLabel = (Label) profileMenuPane.getChildren().get(18);
        nicknameTextField = (TextField) profileMenuPane.getChildren().get(18);
        changeNicknameButton = (Button) profileMenuPane.getChildren().get(20);
        nicknameErrorLabel = (Label) profileMenuPane.getChildren().get(21);
        setNicknameProperties();


        emailLabel = (Label) profileMenuPane.getChildren().get(23);
        emailTextField = (TextField) profileMenuPane.getChildren().get(24);
        changeEmailButton = (Button) profileMenuPane.getChildren().get(25);
        emailErrorLabel = (Label) profileMenuPane.getChildren().get(26);
        setEmailProperties();

        nicknameLabel = (Label) profileMenuPane.getChildren().get(28);
        nicknameTextField = (TextField) profileMenuPane.getChildren().get(29);
        sloganChoiceBox = (ChoiceBox<String>) profileMenuPane.getChildren().get(30);
        changeNicknameButton = (Button) profileMenuPane.getChildren().get(31);
        nicknameErrorLabel = (Label) profileMenuPane.getChildren().get(32);
        deleteSloganButton = (Circle) profileMenuPane.getChildren().get(33);
        customSloganCheckBox = (CheckBox) profileMenuPane.getChildren().get(34);
        setSloganProperties();


        avatarImageView = (ImageView) profileMenuPane.getChildren().get(36);
        avatarsPane = (ScrollPane) profileMenuPane.getChildren().get(37);
        currentProfilePane = (Pane) profileMenuPane.getChildren().get(38);
        changeProfilePictureButton = (Button) profileMenuPane.getChildren().get(39);
        profilePictureErrorLabel = (Label) profileMenuPane.getChildren().get(40);
        setAvatarProperties();

        Scene scene = new Scene(profileMenuPane);
        stage.setScene(scene);
        stage.show();
    }

    private void setUsernameProperties() {
        changeUsernameButton.setOnMouseClicked(event -> {
            String result = profileMenuController.changeUserInfo('u', usernameTextField.getText());
            if (!result.endsWith("changed successfully!")) {
                usernameErrorLabel.setText(result);
            } else {
                usernameLabel.setText("Username: " + controller.getGame().getCurrentUser().getUsername());
                usernameErrorLabel.setText(result);
                usernameErrorLabel.setTextFill(Color.GREEN);
            }
        });
    }

    private void setPasswordProperties() {
        newPasswordTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            newPasswordPasswordField.setText(newPasswordTextField.getText());
        });
        newPasswordPasswordField.textProperty().addListener((observable, oldValue, newValue) -> {
            newPasswordTextField.setText(newPasswordPasswordField.getText());
        });
        oldPasswordTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            oldPasswordPasswordField.setText(oldPasswordTextField.getText());
        });
        oldPasswordPasswordField.textProperty().addListener((observable, oldValue, newValue) -> {
            oldPasswordTextField.setText(oldPasswordPasswordField.getText());
        });
        showPasswordCheckBox.setOnMouseClicked(event -> {
            if (showPasswordCheckBox.isSelected()) {
                newPasswordTextField.setVisible(false);
                oldPasswordTextField.setVisible(false);
                newPasswordPasswordField.setVisible(true);
                oldPasswordPasswordField.setVisible(true);
            } else {
                newPasswordTextField.setVisible(true);
                oldPasswordTextField.setVisible(true);
                newPasswordPasswordField.setVisible(false);
                oldPasswordPasswordField.setVisible(false);
            }
        });
        changePasswordButton.setOnMouseClicked(event -> { // TODO
            String result = profileMenuController.changeUserInfo('p', newPasswordTextField.getText());
            if (!result.endsWith("successfully!")) {
                passwordErrorLabel.setText(result);
                passwordErrorLabel.setTextFill(Color.RED);
            } else {
                passwordErrorLabel.setText(result);
                passwordErrorLabel.setTextFill(Color.GREEN);
            }
        });
    }

    private void setBackButtonProperties() {

        backButton.setOnMouseClicked(event -> {
            try {
                controller.enterMainMenuJFX();
                stop();
                controller.getGame().getCurrentMenuJFX().start(stage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private void setNicknameProperties() {
        changeNicknameButton.setOnMouseClicked(event -> {
            String result = profileMenuController.changeUserInfo('n', nicknameTextField.getText());
            if (!result.endsWith("username changed successfully!")) {
                nicknameErrorLabel.setText(result);
                nicknameErrorLabel.setTextFill(Color.RED);
            } else {
//                setInfoProperties();
                nicknameLabel.setText(Pattern.compile("\\s(?<nickname>nickname : .+)\\s").matcher(infoLabel.getText()).group("nickname"));
                nicknameLabel.setText(result);
                nicknameErrorLabel.setTextFill(Color.GREEN);
                nicknameTextField.setText("");
            }
        });
        nicknameTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                nicknameErrorLabel.setText("");
            }
        });

    }

    private void setEmailProperties() {
        changeEmailButton.setOnMouseClicked(event -> {
            String result = profileMenuController.changeUserInfo('e', usernameTextField.getText());
            if (!result.endsWith("username changed successfully!")) {
                emailErrorLabel.setText(result);
                emailErrorLabel.setTextFill(Color.RED);
            } else {
//                setInfoProperties();
                emailLabel.setText(Pattern.compile("\\s(?<email>email : .+)\\s").matcher(infoLabel.getText()).group("email"));
                emailErrorLabel.setText(result);
                emailErrorLabel.setTextFill(Color.GREEN);
                emailTextField.setText("");
            }
        });
        emailTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                emailErrorLabel.setText("");
            }
        });
    }

    private void setSloganProperties() {
        sloganChoiceBox.getItems().add("Slogan : None");
        sloganChoiceBox.getItems().addAll(profileMenuController.getDefaultSlogans());
        sloganChoiceBox.getSelectionModel().selectFirst();
        customSloganCheckBox.setOnMouseClicked(event -> {
            if (customSloganCheckBox.isSelected()) {
                sloganChoiceBox.getSelectionModel().selectFirst();
                sloganChoiceBox.setVisible(false);
                sloganTextField.setVisible(true);
            } else {
                sloganChoiceBox.setVisible(true);
                sloganTextField.setVisible(false);
                sloganTextField.setText("");
            }
        });
        deleteSloganButton.setFill(new ImagePattern(new Image(Objects.requireNonNull(getClass().getResource("/icons/delete.png")).toExternalForm())));
        deleteSloganButton.setOnMouseClicked(event -> {
            sloganTextField.setText("");
            sloganChoiceBox.getSelectionModel().selectFirst();
            profileMenuController.removeSlogan();
        });
        changeSloganButton.setOnMouseClicked(event -> {
            String slogan = sloganChoiceBox.getSelectionModel().getSelectedItem().equals("Slogan : None") ?
                    sloganTextField.getText() : sloganChoiceBox.getSelectionModel().getSelectedItem();
            String result = profileMenuController.changeUserInfo('s', slogan);
            if (!result.endsWith("slogan changed successfully!")) {
                emailErrorLabel.setText(result);
                emailErrorLabel.setTextFill(Color.RED);
            } else {
//                setInfoProperties();
                emailLabel.setText(Pattern.compile("\\s(?<slogan>slogan : .+)\\s").matcher(infoLabel.getText()).group("slogan"));
                emailErrorLabel.setText(result);
                emailErrorLabel.setTextFill(Color.GREEN);
                emailTextField.setText("");
            }
        });
    }

    private void setAvatarProperties() {
        File profileDirectory = new File(Objects.requireNonNull(Game.class.getResource("/avatars")).toExternalForm());
        int defaultProfilesCount = Objects.requireNonNull(profileDirectory.listFiles()).length - 3;
        addProfilePictures(avatarsPane, defaultProfilesCount);

        currentProfilePane.getChildren().add(new Avatar(currentProfilePane.getPrefWidth() / 2,
                currentProfilePane.getPrefHeight() / 2, profileMenuController.getAvatarImagePattern()));

        changeProfilePictureButton.setOnMouseClicked(event -> {
            if (selectedProfilePicture == null) {
                profilePictureErrorLabel.setText("Please select a profile picture!");
            } else {
                String result = profileMenuController.changeAvatar(selectedProfilePicture.getImagePattern());
                profilePictureErrorLabel.setText(result);
                profilePictureErrorLabel.setTextFill(Color.GREEN);
                currentProfilePane.getChildren().clear();
                currentProfilePicture = new Avatar(currentProfilePane.getPrefWidth() / 2,
                        currentProfilePane.getPrefHeight() / 2, profileMenuController.getAvatarImagePattern());
                currentProfilePane.getChildren().add(currentProfilePicture);
            }
        });
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
            imagePattern = new ImagePattern(new Image(Objects.requireNonNull(Game.class.getResource("/avatars/" + (i + 1) + ".jpg").toExternalForm())));
            profile = createProfilePicture(scrollPane, profileX, profileY, imagePattern);
            makeSelectable(profile);
            profilePictures.add(profile);
            anchorPane.getChildren().add(profile);
        }

        imagePattern = new ImagePattern(new Image(Objects.requireNonNull(Game.class.getResource("/avatars/Random.jpg").toExternalForm())));
        profile = createProfilePicture(scrollPane, profileX, profileY, imagePattern);
        profile.setOnMouseClicked(event -> {
            selectProfilePicture(profilePictures.get(new Random().nextInt(profilePictures.size())));
        });
        anchorPane.getChildren().add(profile);

        imagePattern = new ImagePattern(new Image(Objects.requireNonNull(Game.class.getResource("/avatars/Custom.jpg").toExternalForm())));
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

    public void setProfileMenuController(ProfileMenuController profileMenuController) {
        this.profileMenuController = profileMenuController;
    }
}
