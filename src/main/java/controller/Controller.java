package controller;

import client.Client;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Game;
import model.Match.Match;
import model.SavableMap;
import view.*;
import view.Online.OnlineChatMenuJFX;
import view.Online.OnlineFriendMenuJFX;
import view.Online.OnlineMapMenuJFX;
import view.Online.OnlineMenuJFX;

import java.io.IOException;

public class Controller {
    public static final int Port = 8000;
    private final SignUpMenuJFX signUpMenuJFX;
    private final LogInMenuJFX logInMenuJFX;
    private final MainMenuJFX mainMenuJFX;
    private final MatchMenuJFX matchMenuJFX;
    private final ProfileMenuJFX profileMenuJFX;
    private final ScoreBoardJFX scoreBoardJFX;
    private final ShopMenuJFX shopMenuJFX;
    private final TradeMenuJFX tradeMenuJFX;
    private final SendRequestJFX sendRequestJFX;
    private final ShowRequestsJFX showRequestsJFX;
    private final OnlineMenuJFX onlineMenuJFX;
    private final OnlineChatMenuJFX onlineChatMenuJFX;
    private final OnlineFriendMenuJFX onlineFriendMenuJFX;
    private final OnlineMapMenuJFX onlineMapMenuJFX;
    private final Game game;
    private String captchaAnswer;
    private boolean firstOnline = true;
    private Stage stage;
    private double sceneWidth, sceneHeight;
    private SavableMap usingMap;

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
        matchMenuJFX.setMatchMenuController(new MatchMenuController(this, matchMenuJFX));
        matchMenuJFX.setMapMenuController(new MapMenuController(this));
        this.profileMenuJFX = new ProfileMenuJFX();
        profileMenuJFX.setController(this);
        profileMenuJFX.setProfileMenuController(new ProfileMenuController(this));
        this.scoreBoardJFX = new ScoreBoardJFX();
        scoreBoardJFX.setController(this);
        scoreBoardJFX.setProfileMenuController(profileMenuJFX.getProfileMenuController());
        this.shopMenuJFX = new ShopMenuJFX();
        shopMenuJFX.setController(this);
        shopMenuJFX.setShopMenuController(new ShopMenuController(this));
        this.tradeMenuJFX = new TradeMenuJFX();
        tradeMenuJFX.setController(this);
        tradeMenuJFX.setTradeMenuController(new TradeMenuController(this));
        this.sendRequestJFX = new SendRequestJFX();
        sendRequestJFX.setController(this);
        sendRequestJFX.setTradeMenuController(tradeMenuJFX.getTradeMenuController());
        this.showRequestsJFX = new ShowRequestsJFX();
        showRequestsJFX.setController(this);
        showRequestsJFX.setTradeMenuController(tradeMenuJFX.getTradeMenuController());
        this.onlineMenuJFX = new OnlineMenuJFX();
        onlineMenuJFX.setController(this);
        this.onlineChatMenuJFX = new OnlineChatMenuJFX();
        onlineChatMenuJFX.setController(this);
        this.onlineFriendMenuJFX = new OnlineFriendMenuJFX();
        onlineFriendMenuJFX.setController(this);
        this.onlineMapMenuJFX = new OnlineMapMenuJFX();
        onlineMapMenuJFX.setController(this);
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

    public void enterProfileMenuJFX() {
        game.setCurrentMenuJFX(profileMenuJFX);
    }

    public void enterScoreBoardJFX() {
        game.setCurrentMenuJFX(scoreBoardJFX);
        scoreBoardJFX.setOffline(true);
    }

    public void enterShopMenuJFX() {
        game.setCurrentMenuJFX(shopMenuJFX);
    }

    public void enterTradeMenuJFX() {
        game.setCurrentMenuJFX(tradeMenuJFX);
    }

    public void enterSendRequestJFX() {
        game.setCurrentMenuJFX(sendRequestJFX);
    }

    public void enterShowRequestsJFX() {
        game.setCurrentMenuJFX(showRequestsJFX);
    }

    public void enterOnlineMenuJFX() throws IOException {
        if (firstOnline) {
            onlineMenuJFX.setOnlineUpdateAnimation(new OnlineUpdateAnimation(this));
            onlineMenuJFX.getOnlineUpdateAnimation().play();
            Client.startClient("localhost", Port);
            Client.startConnection();
            firstOnline = false;
        }
        game.setCurrentMenuJFX(onlineMenuJFX);
    }

    public void enterOnlineChatMenuJFX() throws IOException {
        game.setCurrentMenuJFX(onlineChatMenuJFX);
    }

    public void enterOnlineScoreBoardJFX() throws IOException {
        game.setCurrentMenuJFX(scoreBoardJFX);
        scoreBoardJFX.setOffline(false);
    }

    public void enterOnlineFriendMenuJFX() throws IOException {
        game.setCurrentMenuJFX(onlineFriendMenuJFX);
    }

    public void enterOnlineMapMenuJFX() {
        game.setCurrentMenuJFX(onlineMapMenuJFX);
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

    public MatchMenuController getMatchMenuController() {
        return matchMenuJFX.getMatchMenuController();
    }

    public void setUsingMap(SavableMap selectedMap) {
        this.usingMap = selectedMap;
    }

    public SavableMap getUsingMap() {
        return usingMap;
    }
}