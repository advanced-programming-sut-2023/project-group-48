package controller;

import model.Game;
import view.*;

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

    public Controller() {
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

    }

}