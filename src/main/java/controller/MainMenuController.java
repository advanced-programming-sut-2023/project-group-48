package controller;

import model.Match.Match;

import java.io.IOException;

public class MainMenuController {
    private final Controller controller;

    public MainMenuController(Controller controller) {
        this.controller = controller;
    }

    public String startMatch(int rounds, String... playersUsernames) {
        if (playersUsernames.length <= 1) return "not enough players!";

        controller.getGame().setCurrentMatch(new Match());
        return "match started!";
    }

    public String logout() throws IOException {
        return controller.logout();
    }
}