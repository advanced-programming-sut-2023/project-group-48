package view;

import controller.Controller;
import controller.ProfileMenuController;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Popup;
import javafx.stage.Stage;
import model.Game;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class ProfileMenuJFX extends Application implements MenuJFX {
    private Controller controller;
    private ProfileMenuController profileMenuController;
    private Avatar selectedProfilePicture;
    private Avatar currentProfilePicture;
    private AnchorPane profileMenuPane;
    private Popup popup;
    private Pane passwordPane;
    private TextField usernameTextField, oldPasswordTextField, newPasswordTextField, nicknameTextField, emailTextField,
            sloganTextField;
    private PasswordField oldPasswordPasswordField, newPasswordPasswordField;
    private Label titleLabel, infoLabel, usernameLabel, usernameErrorLabel, passwordErrorLabel, nicknameLabel,
            nicknameErrorLabel, emailLabel, emailErrorLabel, sloganLabel, sloganErrorLabel, profilePictureErrorLabel;
    private Separator[] separators;
    private Button changeUsernameButton, changePasswordButton, changeNicknameButton, changeEmailButton,
            changeSloganButton, changeProfilePictureButton, backButton, scoreBoardButton;
    private ScrollPane avatarsPane;
    private ImageView avatarImageView;
    private Pane currentProfilePane;
    private ChoiceBox<String> sloganChoiceBox;
    private CheckBox showPasswordCheckBox, customSloganCheckBox;
    private Circle deleteSloganButton;
    private CaptchaJFX captchaJFX;
    private Button clickedButton;
    private Stage stage;

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        profileMenuPane = FXMLLoader.load(Objects.requireNonNull(Game.class.getResource("/fxml/ProfileMenu.fxml")));
        profileMenuPane.setBackground(Background.fill(new ImagePattern(new Image(getClass().getResource("/backgrounds/8.jpg").toExternalForm()))));

        popup = new Popup();

        titleLabel = (Label) profileMenuPane.getChildren().get(0);

        infoLabel = (Label) profileMenuPane.getChildren().get(1);

        usernameLabel = (Label) profileMenuPane.getChildren().get(3);
        usernameTextField = (TextField) profileMenuPane.getChildren().get(4);
        changeUsernameButton = (Button) profileMenuPane.getChildren().get(5);
        usernameErrorLabel = (Label) profileMenuPane.getChildren().get(6);
        setUsernameProperties();

        sloganLabel = (Label) profileMenuPane.getChildren().get(8);
        sloganTextField = (TextField) profileMenuPane.getChildren().get(9);
        sloganChoiceBox = (ChoiceBox<String>) profileMenuPane.getChildren().get(10);
        changeSloganButton = (Button) profileMenuPane.getChildren().get(11);
        sloganErrorLabel = (Label) profileMenuPane.getChildren().get(12);
        deleteSloganButton = (Circle) profileMenuPane.getChildren().get(13);
        customSloganCheckBox = (CheckBox) profileMenuPane.getChildren().get(14);
        setSloganProperties();

        backButton = (Button) profileMenuPane.getChildren().get(16);
        setBackButtonProperties();

        nicknameLabel = (Label) profileMenuPane.getChildren().get(18);
        nicknameTextField = (TextField) profileMenuPane.getChildren().get(19);
        changeNicknameButton = (Button) profileMenuPane.getChildren().get(20);
        nicknameErrorLabel = (Label) profileMenuPane.getChildren().get(21);
        setNicknameProperties();

        emailLabel = (Label) profileMenuPane.getChildren().get(23);
        emailTextField = (TextField) profileMenuPane.getChildren().get(24);
        changeEmailButton = (Button) profileMenuPane.getChildren().get(25);
        emailErrorLabel = (Label) profileMenuPane.getChildren().get(26);
        setEmailProperties();

        changePasswordButton = (Button) profileMenuPane.getChildren().get(28);
        passwordPane = (Pane) profileMenuPane.getChildren().get(29);
        passwordErrorLabel = (Label) passwordPane.getChildren().get(0);
        oldPasswordTextField = (TextField) passwordPane.getChildren().get(1);
        newPasswordTextField = (TextField) passwordPane.getChildren().get(2);
        oldPasswordPasswordField = (PasswordField) passwordPane.getChildren().get(3);
        newPasswordPasswordField = (PasswordField) passwordPane.getChildren().get(4);
        showPasswordCheckBox = (CheckBox) passwordPane.getChildren().get(5);
        setPasswordProperties();

        avatarImageView = (ImageView) profileMenuPane.getChildren().get(31);
        avatarsPane = (ScrollPane) profileMenuPane.getChildren().get(32);
        currentProfilePane = (Pane) profileMenuPane.getChildren().get(33);
        changeProfilePictureButton = (Button) profileMenuPane.getChildren().get(34);
        profilePictureErrorLabel = (Label) profileMenuPane.getChildren().get(35);
        setAvatarProperties();

        scoreBoardButton = (Button) profileMenuPane.getChildren().get(36);

        separators = new Separator[]{(Separator) profileMenuPane.getChildren().get(2), (Separator) profileMenuPane.getChildren().get(7),
                (Separator) profileMenuPane.getChildren().get(15), (Separator) profileMenuPane.getChildren().get(17),
                (Separator) profileMenuPane.getChildren().get(22), (Separator) profileMenuPane.getChildren().get(27),
                (Separator) profileMenuPane.getChildren().get(30)};

        captchaJFX = new CaptchaJFX(controller, profileMenuPane);
        setCaptchaProperties();

        setInfoProperties();

        System.out.println(1);
        adjustWithScene();
        System.out.println(2);
        Scene scene = new Scene(profileMenuPane);
        stage.setScene(scene);
        scene.heightProperty().addListener((observable, oldValue, newValue) -> {
            adjustWithScene();
        });
        scene.widthProperty().addListener((observable, oldValue, newValue) -> {
            adjustWithScene();
        });
        System.out.println(3);
        stage.show();
        System.out.println(4);
    }

    private void setInfoProperties() {
        infoLabel.setText(profileMenuController.getInfo());
        usernameLabel.setText(profileMenuController.getUsername());
        emailLabel.setText(profileMenuController.getEmail());
        nicknameLabel.setText(profileMenuController.getNickname());
        sloganLabel.setText(profileMenuController.displaySlogan());
    }

    private void setUsernameProperties() {
        usernameTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (usernameTextField.getText().isEmpty()) usernameErrorLabel.setText("");
            else usernameErrorLabel.setText(profileMenuController.checkUsername(usernameTextField.getText()));
        });
        changeUsernameButton.setOnMouseClicked(event -> {
            if (usernameTextField.getText().isEmpty()) usernameErrorLabel.setText("empty field!");
            else {
                clickedButton = changeUsernameButton;
                try {
                    captchaJFX.popOutCaptchaPane();
                } catch (MalformedURLException | URISyntaxException e) {
                    throw new RuntimeException(e);
                }
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
            if (sloganTextField.getText().isEmpty() && customSloganCheckBox.isSelected())
                sloganErrorLabel.setText("empty field!");
            else {
                clickedButton = changeSloganButton;
                try {
                    captchaJFX.popOutCaptchaPane();
                } catch (MalformedURLException | URISyntaxException e) {
                    throw new RuntimeException(e);
                }
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
        nicknameTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                if (!nicknameTextField.getText().isEmpty()) nicknameErrorLabel.setText("");
            }
        });
        changeNicknameButton.setOnMouseClicked(event -> {
            if (nicknameTextField.getText().isEmpty()) nicknameErrorLabel.setText("empty field!");
            else {
                clickedButton = changeNicknameButton;
                try {
                    captchaJFX.popOutCaptchaPane();
                } catch (MalformedURLException | URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    private void setEmailProperties() {
        emailTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                emailErrorLabel.setText(profileMenuController.checkEmail(emailTextField.getText()));
            }
        });
        changeEmailButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (emailTextField.getText().isEmpty()) emailErrorLabel.setText("empty field!");
                else {
                    clickedButton = changeEmailButton;
                    try {
                        captchaJFX.popOutCaptchaPane();
                    } catch (MalformedURLException | URISyntaxException e) {
                        throw new RuntimeException(e);
                    }
                }
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
        changePasswordButton.setOnMouseClicked(event -> {
            clickedButton = changePasswordButton;
            newPasswordTextField.setText("");
            oldPasswordTextField.setText("");
            try {
                captchaJFX.popOutCaptchaPane();
                captchaJFX.getCaptchaPane().setLayoutX(profileMenuPane.getPrefWidth() / 2 - captchaJFX.getCaptchaPane().getPrefWidth() / 2);
                captchaJFX.getCaptchaPane().setLayoutY(passwordPane.getLayoutY() + passwordPane.getPrefHeight());
            } catch (MalformedURLException | URISyntaxException e) {
                throw new RuntimeException(e);
            }
            passwordPane.setVisible(true);
        });
    }

    private void setAvatarProperties() {
        avatarsPane.setBackground(Background.EMPTY);

        addProfilePictures(avatarsPane, 46);

        currentProfilePane.getChildren().add(new Avatar(currentProfilePane.getPrefWidth() / 2,
                currentProfilePane.getPrefHeight() / 2, profileMenuController.getAvatarImagePattern()));
        currentProfilePicture = (Avatar) currentProfilePane.getChildren().get(0);

        avatarImageView.setOnDragOver(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent dragEvent) {
                if (dragEvent.getDragboard().hasFiles()) {
                    dragEvent.acceptTransferModes(TransferMode.ANY);
                }
            }
        });
        avatarImageView.setOnDragDropped(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent dragEvent) {
                ArrayList<File> files = (ArrayList<File>) dragEvent.getDragboard().getFiles();
                try {
                    Image image = new Image(new FileInputStream(files.get(0)));
                    avatarImageView.setImage(image);
                    selectedProfilePicture.setStroke(Color.BLACK);
                    selectedProfilePicture = new Avatar(currentProfilePane.getPrefWidth() / 2,
                            currentProfilePane.getPrefHeight() / 2, new ImagePattern(image));
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        changeProfilePictureButton.setOnMouseClicked(event -> {
            clickedButton = changeProfilePictureButton;
            try {
                captchaJFX.popOutCaptchaPane();
            } catch (MalformedURLException | URISyntaxException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void setCaptchaProperties() {
        captchaJFX.getCaptchaAnswerButton().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (captchaJFX.getCaptchaAnswer().getText().equals(controller.getCaptchaAnswer())) {
                    if (changeUsernameButton.equals(clickedButton)) {
                        changeUsername();
                    } else if (changeSloganButton.equals(clickedButton)) {
                        changeSlogan();
                    } else if (changeNicknameButton.equals(clickedButton)) {
                        changeNickname();
                    } else if (changeEmailButton.equals(clickedButton)) {
                        changeEmail();
                    } else if (changePasswordButton.equals(clickedButton)) {
                        changePassword();
                    } else if (changeProfilePictureButton.equals(clickedButton)) {
                        changeProfilePicture();
                    }
                } else {
                    captchaJFX.getCaptchaError().setText("captcha answer is incorrect!");
                    captchaJFX.getCaptchaError().setTextFill(Color.RED);
                }
            }
        });
    }

    private void changeUsername() {
        String result = profileMenuController.changeUserInfo('u', usernameTextField.getText());
        if (!result.endsWith("changed successfully!")) {
            usernameErrorLabel.setText(result);
        } else {
            setInfoProperties();
            usernameErrorLabel.setText(result);
            usernameErrorLabel.setTextFill(Color.GREEN);
            popup.getContent().removeAll(popup.getContent());
            popup.getContent().add(new Label(result));
            popup.show(stage);
            captchaJFX.popOffCaptchaPane();
        }
    }

    private void changeSlogan() {
        String slogan = sloganChoiceBox.getSelectionModel().getSelectedItem().equals("Slogan : None") ?
                sloganTextField.getText() : sloganChoiceBox.getSelectionModel().getSelectedItem();
        String result = profileMenuController.changeUserInfo('s', slogan);
        if (!result.endsWith("slogan changed successfully!")) {
            emailErrorLabel.setText(result);
            emailErrorLabel.setTextFill(Color.RED);
        } else {
            setInfoProperties();
            emailErrorLabel.setText(result);
            emailErrorLabel.setTextFill(Color.GREEN);
            emailTextField.setText("");
            popup.getContent().removeAll(popup.getContent());
            popup.getContent().add(new Label(result));
            popup.show(stage);
            captchaJFX.popOffCaptchaPane();
        }
    }

    private void changeNickname() {
        String result = profileMenuController.changeUserInfo('n', nicknameTextField.getText());
        if (!result.endsWith("username changed successfully!")) {
            nicknameErrorLabel.setText(result);
            nicknameErrorLabel.setTextFill(Color.RED);
        } else {
            setInfoProperties();
            nicknameLabel.setText(result);
            nicknameErrorLabel.setTextFill(Color.GREEN);
            nicknameTextField.setText("");
            popup.getContent().removeAll(popup.getContent());
            popup.getContent().add(new Label(result));
            popup.show(stage);
            captchaJFX.popOffCaptchaPane();
        }
    }

    private void changeEmail() {
        String result = profileMenuController.changeUserInfo('e', usernameTextField.getText());
        if (!result.endsWith("username changed successfully!")) {
            emailErrorLabel.setText(result);
            emailErrorLabel.setTextFill(Color.RED);
        } else {
            setInfoProperties();
            emailErrorLabel.setText(result);
            emailErrorLabel.setTextFill(Color.GREEN);
            emailTextField.setText("");
            popup.getContent().removeAll(popup.getContent());
            popup.getContent().add(new Label(result));
            popup.show(stage);
            captchaJFX.popOffCaptchaPane();
        }
    }

    private void changePassword() {
        if (newPasswordTextField.getText().isEmpty()) {
            passwordErrorLabel.setText("empty field!");
            passwordErrorLabel.setTextFill(Color.RED);
            return;
        }
        String oldPasswordCheck = profileMenuController.checkOldPassword(oldPasswordTextField.getText());
        if (oldPasswordCheck.contains("incorrect")) {
            passwordErrorLabel.setText(oldPasswordCheck);
            passwordErrorLabel.setTextFill(Color.RED);
            return;
        }
        String result = profileMenuController.changeUserInfo('p', newPasswordTextField.getText());
        if (!result.endsWith("successfully!")) {
            passwordErrorLabel.setText(result);
            passwordErrorLabel.setTextFill(Color.RED);
        } else {
            passwordErrorLabel.setText(result);
            passwordErrorLabel.setTextFill(Color.GREEN);
            popup.getContent().removeAll(popup.getContent());
            popup.getContent().add(new Label(result));
            popup.show(stage);
            captchaJFX.popOffCaptchaPane();
            passwordPane.setVisible(false);
        }
    }

    private void changeProfilePicture() {
        if (selectedProfilePicture == null) {
            profilePictureErrorLabel.setText("Please select a profile picture!");
        } else {
            String result = profileMenuController.changeAvatar(selectedProfilePicture.getImagePattern());
            profilePictureErrorLabel.setText(result);
            profilePictureErrorLabel.setTextFill(Color.GREEN);
            currentProfilePicture.setImagePattern(selectedProfilePicture.getImagePattern());
            captchaJFX.popOffCaptchaPane();
        }
    }

    public void addProfilePictures(ScrollPane scrollPane, int profilesCount) {
        ArrayList<Avatar> profilePictures = new ArrayList<>();
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setBackground(Background.EMPTY);
        anchorPane.setPrefWidth(scrollPane.getPrefWidth());
        anchorPane.setPrefHeight(scrollPane.getPrefHeight());
        ImagePattern imagePattern;
        final int[] profileX = {40};
        final int[] profileY = {40};
        Avatar profile;
        for (int i = 0; i < profilesCount; i++) {
            imagePattern = new ImagePattern(new Image(Objects.requireNonNull(Game.class.getResource("/avatars/" + (i + 1) + ".png")).toExternalForm()));
            System.out.println(imagePattern.getImage() == null);
            profile = createProfilePicture(scrollPane, profileX, profileY, imagePattern);
            makeSelectable(profile);
            profilePictures.add(profile);
            anchorPane.getChildren().add(profile);
        }

        imagePattern = new ImagePattern(new Image(Objects.requireNonNull(Game.class.getResource("/avatars/Random.png")).toExternalForm()));
        profile = createProfilePicture(scrollPane, profileX, profileY, imagePattern);
        profile.setOnMouseClicked(event -> {
            selectProfilePicture(profilePictures.get(new Random().nextInt(profilePictures.size())));
        });
        anchorPane.getChildren().add(profile);

        imagePattern = new ImagePattern(new Image(Objects.requireNonNull(Game.class.getResource("/avatars/Custom.png")).toExternalForm()));
        profile = createProfilePicture(scrollPane, profileX, profileY, imagePattern);
        profile.setOnMouseClicked(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Choose Profile Picture");
            File file = fileChooser.showOpenDialog(stage);
            if (file != null && file.getPath().endsWith(".png")) {
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

    @Override
    public void adjust(double ratioX, double ratioY) {
        Adjust.adjustPane(profileMenuPane, ratioX, ratioY);
        Adjust.adjustLabel(titleLabel, ratioX, ratioY);
        Adjust.adjustLabel(infoLabel, ratioX, ratioY);
        Adjust.adjustLabel(usernameLabel, ratioX, ratioY);
        Adjust.adjustLabel(sloganLabel, ratioX, ratioY);
        Adjust.adjustLabel(nicknameLabel, ratioX, ratioY);
        Adjust.adjustLabel(emailLabel, ratioX, ratioY);
        Adjust.adjustLabel(usernameErrorLabel, ratioX, ratioY);
        Adjust.adjustLabel(sloganErrorLabel, ratioX, ratioY);
        Adjust.adjustLabel(nicknameErrorLabel, ratioX, ratioY);
        Adjust.adjustLabel(emailErrorLabel, ratioX, ratioY);
        Adjust.adjustLabel(profilePictureErrorLabel, ratioX, ratioY);
        Adjust.adjustTextField(usernameTextField, ratioX, ratioY);
        Adjust.adjustTextField(sloganTextField, ratioX, ratioY);
        Adjust.adjustTextField(nicknameTextField, ratioX, ratioY);
        Adjust.adjustTextField(emailTextField, ratioX, ratioY);
        Adjust.adjustButton(changeUsernameButton, ratioX, ratioY);
        Adjust.adjustButton(changeSloganButton, ratioX, ratioY);
        Adjust.adjustButton(changeNicknameButton, ratioX, ratioY);
        Adjust.adjustButton(changeEmailButton, ratioX, ratioY);
        Adjust.adjustButton(changePasswordButton, ratioX, ratioY);
        Adjust.adjustButton(changeProfilePictureButton, ratioX, ratioY);
        Adjust.adjustButton(backButton, ratioX, ratioY);
        Adjust.adjustButton(scoreBoardButton, ratioX, ratioY);
        Adjust.adjustChoiceBox(sloganChoiceBox, ratioX, ratioY);
        Adjust.adjustCircle(deleteSloganButton, ratioX, ratioY);
        Adjust.adjustImageView(avatarImageView, ratioX, ratioY);
        Adjust.adjustScrollPane(avatarsPane, ratioX, ratioY);
        Adjust.adjustPane(currentProfilePane, ratioX, ratioY);
        Adjust.adjustCircle(currentProfilePicture, ratioX, ratioY);
        AnchorPane anchorPane = (AnchorPane) avatarsPane.getContent();
        for (Node node : anchorPane.getChildren()) {
            if (node instanceof Avatar) {
                Adjust.adjustCircle((Avatar) node, ratioX, ratioY);
            }
        }
        for (Separator separator : separators) {
            Adjust.adjustSeparator(separator, ratioX, ratioY);
        }
        Adjust.adjustPane(passwordPane, ratioX, ratioY);
        Adjust.adjustLabel(passwordErrorLabel, ratioX, ratioY);
        Adjust.adjustTextField(oldPasswordTextField, ratioX, ratioY);
        Adjust.adjustTextField(newPasswordTextField, ratioX, ratioY);
        Adjust.adjustPasswordField(oldPasswordPasswordField, ratioX, ratioY);
        Adjust.adjustPasswordField(newPasswordPasswordField, ratioX, ratioY);
        Adjust.adjustCheckBox(showPasswordCheckBox, ratioX, ratioY);
        Adjust.adjustCheckBox(customSloganCheckBox, ratioX, ratioY);
        captchaJFX.adjust(ratioX, ratioY);
    }

    @Override
    public void adjustWithScene() {
        adjust(stage.getScene().getWidth() / profileMenuPane.getPrefWidth(),
                stage.getScene().getHeight() / profileMenuPane.getPrefHeight());
    }

    public ProfileMenuController getProfileMenuController() {
        return profileMenuController;
    }
}