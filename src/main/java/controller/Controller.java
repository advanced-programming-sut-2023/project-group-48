package controller;

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
    private final SignUpMenu signUpMenu;
    private final LoginMenu loginMenu;
    private final MainMenu mainMenu;
    private final MapMenu mapMenu;
    private final MatchMenu matchMenu;
    private final ProfileMenu profileMenu;
    private final TradeMenu tradeMenu;
    private final ShopMenu shopMenu;
    private final Game game;
    private String captchaAnswer;
    private Stage stage;
    private double maxSceneWidth, maxSceneHeight;

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
        this.game = new Game(mainMenuJFX, signUpMenuJFX);
        this.signUpMenu = new SignUpMenu(this);
        this.loginMenu = new LoginMenu(this);
        this.mainMenu = new MainMenu(this);
        this.mapMenu = new MapMenu(this);
        this.profileMenu = new ProfileMenu(this);
        this.matchMenu = new MatchMenu(this);
        this.tradeMenu = new TradeMenu(this);
        this.shopMenu = new ShopMenu(this);
//        this.game = new Game(mainMenu, signUpMenu);
    }

    private void setStage() {
        stage.setScene(new Scene(new Pane()));
        stage.getScene().setFill(Color.BLACK);
        stage.setMaximized(true);
        stage.show();
        maxSceneWidth = stage.getScene().getWidth();
        maxSceneHeight = stage.getScene().getHeight();
        stage.setMaximized(false);
    }

    public Stage getStage() {
        return stage;
    }

    public double getMaxSceneWidth() {
        return maxSceneWidth;
    }

    public double getMaxSceneHeight() {
        return maxSceneHeight;
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

    public void logOutJFX() throws IOException {
        game.setCurrentMenuJFX(signUpMenuJFX);
        game.setCurrentUser(null);
    }

    public void exitJFX() throws IOException {
        game.updateJsonFiles();
        game.setCurrentMenuJFX(null);
    }

    public String enterLoginMenu() {
        game.setCurrentMenu(loginMenu);
        return "entered login Menu!";
    }

    public String enterSignUpMenu() {
        game.setCurrentMenu(signUpMenu);
        return "entered signup Menu!";
    }

    public String enterMainMenu() {
        game.setCurrentMenu(mainMenu);
        return "entered main Menu!";
    }

    public String enterProfileMenu() {
        game.setCurrentMenu(profileMenu);
        return "entered profile Menu!";
    }

    public String enterMatchMenu() {
        game.setCurrentMenu(matchMenu);
        return "entered match Menu!";
    }

    public String enterTradeMenu() {
        game.setCurrentMenu(tradeMenu);
        return "entered trade Menu!";
    }

    public String enterShopMenu() {
        game.setCurrentMenu(shopMenu);
        return "entered shop Menu!";
    }

    public String enterMapMenu() {
        game.setCurrentMenu(mapMenu);
        return "entered map Menu!";
    }

    public String logout() throws IOException {
        game.setCurrentMenu(signUpMenu);
        game.setCurrentUser(null);
        return "logged out!";
    }

    public void exit() throws IOException {
        game.updateJsonFiles();
        game.setCurrentMenu(null);
        game.setCurrentMenuJFX(null);
    }

    public void setCurrentMatch(Match match) {
        matchMenu.setCurrentMatch(match);
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
}