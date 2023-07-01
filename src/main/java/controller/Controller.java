package controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Game;
import model.Match.Match;
import view.*;

import java.io.IOException;

public class Controller {
    private final SignUpMenuJFX signUpMenuJFX;
    private final LogInMenuJFX logInMenuJFX;
    private final MainMenuJFX mainMenuJFX;
    private final MatchMenuJFX matchMenuJFX;
    private final Game game;
    private String captchaAnswer;
    private Stage stage;
    private double sceneWidth, sceneHeight;

    public Controller(Stage stage) throws IOException {
        this.stage = stage;
        setStage();
        this.signUpMenuJFX = new SignUpMenuJFX();
        signUpMenuJFX.setController(this);
        signUpMenuJFX.setSignUpMenuController(new SignUpMenuController(this));
        this.logInMenuJFX = new LogInMenuJFX();
        logInMenuJFX.setController(this);
        logInMenuJFX.setLoginMenuController(new LoginMenuController(this));
        this.mainMenuJFX = new MainMenuJFX();
        mainMenuJFX.setController(this);
        mainMenuJFX.setMainMenuController(new MainMenuController(this));
        this.matchMenuJFX = new MatchMenuJFX();
        matchMenuJFX.setController(this);
        matchMenuJFX.setMatchMenuController(new MatchMenuController(this));
        matchMenuJFX.setMapMenuController(new MapMenuController(this));
        this.game = new Game(mainMenuJFX, signUpMenuJFX);
    }

    private void setStage() {
        stage.setScene(new Scene(new Pane()));
        stage.getScene().setFill(Color.BLACK);
        stage.setMaximized(true);
        stage.show();
        sceneWidth = stage.getScene().getWidth();
        sceneHeight = stage.getScene().getHeight();
        System.out.println("width: " + sceneWidth + ", height: " + sceneHeight);
        stage.maximizedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
                System.out.println("maximized: " + stage.isMaximized());
            }
        });
    }

    public Stage getStage() {
        return stage;
    }

    public double getSceneWidth() {
        return sceneWidth;
    }

    public void setSceneWidth(double sceneWidth) {
        this.sceneWidth = sceneWidth;
    }

    public double getSceneHeight() {
        return sceneHeight;
    }

    public void setSceneHeight(double sceneHeight) {
        this.sceneHeight = sceneHeight;
    }

    public static String getRemovedQuotationMarks(String input) {
        if (input.startsWith("\"") && input.endsWith("\"")) return input.substring(1, input.length() - 1);
        return input;
    }

    public void run() throws Exception {
//        while (game.getCurrentMenuJFX() != null) {
//            game.getCurrentMenuJFX().start(stage);
//        }
        if (game.getCurrentMenuJFX() != null) {
            game.getCurrentMenuJFX().start(stage);
        }
    }

    public Game getGame() {
        return game;
    }

    public void enterSignUpMenuJFX() {
        game.setCurrentMenuJFX(signUpMenuJFX);
    }

    public void enterLogInMenuJFX() {
        game.setCurrentMenuJFX(logInMenuJFX);
    }

    public void enterMainMenuJFX() {
        game.setCurrentMenuJFX(mainMenuJFX);
    }

    public void enterMatchMenuJFX() {
        game.setCurrentMenuJFX(matchMenuJFX);
    }

    public void logOutJFX() throws IOException {
        game.setCurrentMenuJFX(signUpMenuJFX);
        game.setCurrentUser(null);
    }

    public void exitJFX() throws IOException {
        game.updateJsonFiles();
        game.setCurrentMenuJFX(null);
    }

    public void setHoverProperty(Node node) {
        node.hoverProperty().addListener(event -> {
            node.setOpacity(1.5 - node.getOpacity());
        });
    }

    public String getCaptchaAnswer() {
        return captchaAnswer;
    }

    public void setCaptchaAnswer(String captchaAnswer) {
        this.captchaAnswer = captchaAnswer;
    }

    public void setMatch(Match match) {

    }
}