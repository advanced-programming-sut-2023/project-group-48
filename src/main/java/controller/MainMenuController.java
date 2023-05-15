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
        ArrayList<String> playersUsernames = new ArrayList<>();
        String usernameRegex = "(?<username>\".+\"|\\S+)";
        Pattern pattern = Pattern.compile(usernameRegex);
        Matcher matcher = pattern.matcher(usernames);
        while (matcher.find()) {
            playersUsernames.add(matcher.group("username"));
        }
        ArrayList<User> players = new ArrayList<>();
        for (String username : playersUsernames) {
            User user = controller.getGame().getUserByUsername(username);
            if (user != null) players.add(user);
        }
        if (players.size() <= 1) return "not enough players!";
        Match match = new Match(rounds, players, mapNumber);
        controller.getGame().setCurrentMatch(match);
        controller.setCurrentMatch(match);
        return "match started!\n" + controller.enterMatchMenu();
    }
}