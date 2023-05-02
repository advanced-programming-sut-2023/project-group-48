package controller;

import model.Game;
import view.*;

import java.io.IOException;

public class Controller {
    private final SignUpMenu signUpMenu;
    private final LoginMenu loginMenu;
    private final MainMenu mainMenu;
    private final MapMenu mapMenu;
    private final MatchMenu matchMenu;
    private final ProfileMenu profileMenu;
    private final TradeMenu tradeMenu;
    private final ShopMenu shopMenu;
    private final Game game;

    public Controller() throws IOException {
        this.signUpMenu = new SignUpMenu(this);
        this.loginMenu = new LoginMenu(this);
        this.mainMenu = new MainMenu(this);
        this.mapMenu = new MapMenu(this);
        this.profileMenu = new ProfileMenu(this);
        this.matchMenu = new MatchMenu(this);
        this.tradeMenu = new TradeMenu(this);
        this.shopMenu = new ShopMenu(this);
        this.game = new Game();
    }

    public void run() {
        while (game.getCurrentMenu() != null) {
            game.getCurrentMenu().run();
        }
    }

    public Game getGame() {
        return game;
    }

    public String enterLoginMenu() {
        game.setCurrentMenu(loginMenu);
        return "entered login Menu!";
    }
    public String enterMainMenu() {
        game.setCurrentMenu(mainMenu);
        return "entered main Menu!";
    }

    public String logout() throws IOException {
        game.setCurrentMenu(signUpMenu);
        game.setCurrentUser(null);
        return "logged out!";
    }

    public void exit() {
        game.setCurrentMenu(null);
    }
}