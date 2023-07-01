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

    public void saveMap(Cell[][] map){
        Gson gson = new Gson();
        String json = gson.toJson(map);
        try {
            FileReader fileReader = new FileReader("Maps.json");
            ArrayList<Cell[][]> maps = gson.fromJson(fileReader, new TypeToken<ArrayList<Cell[][]>>(){}.getType());
            maps.add(map);
            FileWriter fileWriter = new FileWriter("Maps.json");
            gson.toJson(maps, fileWriter);
            fileWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public ArrayList<Cell[][]> loadMaps(){
        Gson gson = new Gson();
        FileReader fileReader = null;
        try {
            fileReader = new FileReader("Maps.json");
            ArrayList<Cell[][]> maps = gson.fromJson(fileReader, new TypeToken<ArrayList<Cell[][]>>(){}.getType());
            fileReader.close();
            return maps;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}