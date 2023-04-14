package model;

import model.People.Troop;

import java.util.ArrayList;

public class Game {
    private ArrayList<User> users;
    private User currentUser;
    private Menu currentMenu;
    private Match currentMatch;
    private Troop selectedTroop;
    private Unit selectedUnit;

    public Game() {}

    public static String getRandomCaptcha(){}

    public User getCurrentUser() {}

    public void setCurrentUser(User currentUser) {}

    public Menu getCurrentMenu() {
    }

    public void setCurrentMenu(Menu currentMenu) {
    }

    public Match getCurrentMatch() {
    }

    public void setCurrentMatch(Match currentMatch) {
    }

    public void addUser(User user) {}

    public User getUserByUsername(String username) {}

    public User getUserByEmail(String email) {}

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

    public Unit getUnit(int column, int row) {}
}