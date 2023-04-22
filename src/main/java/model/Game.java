package model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.Match.Match;
import model.Match.Unit;
import model.People.Troop;
import view.Menu;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

// Parsa
public class Game {
    private final ArrayList<User> users;
    private DataBase db;
    private User currentUser;
    private Menu currentMenu;
    private Match currentMatch;
    private Troop selectedTroop;
    private Unit selectedUnit;

    public Game() throws IOException {
        File usersFile = new File("Users.jason");
        if (usersFile.exists()) {
            BufferedReader fileReader = new BufferedReader(new FileReader(usersFile));
            users = new Gson().fromJson(fileReader, new TypeToken<ArrayList<User>>(){}.getType());
            fileReader.close();
        } else {
            users = new ArrayList<User>();
            FileWriter fileWriter = new FileWriter(usersFile);
            fileWriter.close();
        }

        File dataBaseFile = new File("DataBase.jason");
        if (dataBaseFile.exists()) {
            BufferedReader fileReader = new BufferedReader(new FileReader(dataBaseFile));
            db = new Gson().fromJson(fileReader, DataBase.class);
            fileReader.close();
        } else {
            db = new DataBase();
            FileWriter fileWriter = new FileWriter(dataBaseFile);
            fileWriter.close();
        }
    }

    public static String[] getRandomCaptcha() {
        Random random = new Random();
        int digitCount = random.nextInt(5) + 4;
        ArrayList<Integer> numbersToUserInCaptcha = new ArrayList<>();
        StringBuilder captchaAnswer = new StringBuilder();
        for (int i = 0; i < digitCount; i++) {
            int number = random.nextInt(10);
            numbersToUserInCaptcha.add(number);
            captchaAnswer.append(i == 0 ? "" : " ").append(Integer.toString(number));
        }
        return new String[]{Captcha.getFullArt(numbersToUserInCaptcha), captchaAnswer.toString()};
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) throws IOException {
        this.currentUser = currentUser;
        if (currentUser == null) {
            db.setCurrentUser(null);
            FileWriter fileWriter = new FileWriter("DataBase.jason");
            fileWriter.write(new Gson().toJson(db));
            fileWriter.close();
        }
    }

    public void setDataBaseCurrentUser(User user) throws IOException {
        db.setCurrentUser(currentUser);
        FileWriter fileWriter = new FileWriter("DataBase.jason");
        fileWriter.write(new Gson().toJson(db));
        fileWriter.close();
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

    public void addUser(User user) throws IOException {
        users.add(user);
        Gson gson = new Gson();
        FileWriter fileWriter = new FileWriter("Users.jason");
        fileWriter.write(gson.toJson(users));
        fileWriter.close();
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