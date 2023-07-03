package model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.Match.Cell;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class DataBase {
    private User currentUser;

    public DataBase() {
        currentUser = null;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }


}