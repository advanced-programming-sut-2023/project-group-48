package controller;

import model.Match.Match;
import model.User;


import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainMenuController {
    private final Controller controller;

    public MainMenuController(Controller controller) {
        this.controller = controller;
    }

    public String startMatch(int rounds, int mapNumber, String usernames) {
        ArrayList<User> players = new ArrayList<>();
        Matcher matcher = Pattern.compile("(?<username>\".+\"|\\S+)").matcher(usernames);
        while (matcher.find()) {
            User user = controller.getGame().getUserByUsername(matcher.group("username"));
            if (user != null) players.add(user);
        }
        if (players.size() <= 1) return "not enough players!";
        Match match = new Match(rounds, players, mapNumber);
        controller.getGame().setCurrentMatch(match);
//        controller.setCurrentMatch(match);
//        controller.enterMatchMenuJFX();
        return "\nmatch started!\n" + match.getCurrentPlayer().getUsername() + " is now playing!";
    }
}