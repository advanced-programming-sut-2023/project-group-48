package controller;

import model.Match.Match;
import model.User;

import java.io.IOException;
import java.util.ArrayList;

public class MainMenuController {
    private final Controller controller;

    public MainMenuController(Controller controller) {
        this.controller = controller;
    }

    public String startMatch(int rounds, int mapNumber, String... playersUsernames) {

        ArrayList<User> players = new ArrayList<>();
        for (String username : playersUsernames) {
            User user = controller.getGame().getUserByUsername(username);
            if (user != null) players.add(user);
        }
        if (players.size() <= 1) return "not enough players!";
        controller.getGame().setCurrentMatch(new Match(rounds, players, mapNumber));
        return "match started!";
    }

    public String logout() throws IOException {
        return controller.logout();
    }
}