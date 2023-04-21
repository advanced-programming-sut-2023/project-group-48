package model;

import model.Match.Match;
import model.Match.Unit;
import model.People.Troop;
import view.Menu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

// Parsa
public class Game {
    private final ArrayList<User> users;
    private User currentUser;
    private Menu currentMenu;
    private Match currentMatch;
    private Troop selectedTroop;
    private Unit selectedUnit;

    public Game() {
        this.users = new ArrayList<>();
    }

    public static String[] getRandomCaptcha() {
        Random random = new Random();
        int digitCount = random.nextInt(5) + 4;
        ArrayList<Integer> numbersToUserInCaptcha = new ArrayList<>();
        String captchaAnswer = "";
        for (int i = 0; i < digitCount; i++) {
            int number = random.nextInt(10);
            numbersToUserInCaptcha.add(number);
            captchaAnswer += (i == 0 ? "" : " ") + Integer.toString(number);
        }
        String[] result = {Captcha.getFullArt(numbersToUserInCaptcha), captchaAnswer};
        return result;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public Menu getCurrentMenu() {
        return currentMenu;
    }

    public void setCurrentMenu(Menu currentMenu) {
        this.currentMenu = currentMenu;
    }

    public Match getCurrentMatch() {
        return currentMatch;
    }

    public void setCurrentMatch(Match currentMatch) {
        this.currentMatch = currentMatch;
    }

    public void addUser(User user) {
        users.add(user);
    }

    public User getUserByUsername(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) return user;
        }
        return null;
    }

    public User getUserByEmail(String email) {
        for (User user : users) {
            if (user.getEmail().toLowerCase().equals(email.toLowerCase())) return user;
        }
        return null;
    }

    public Troop getSelectedTroop() {
        return selectedTroop;
    }

    public void setSelectedTroop(Troop selectedTroop) {
        this.selectedTroop = selectedTroop;
    }

    public Unit getSelectedUnit() {
        return selectedUnit;
    }

    public void setSelectedUnit(Unit selectedUnit) {
        this.selectedUnit = selectedUnit;
    }
}