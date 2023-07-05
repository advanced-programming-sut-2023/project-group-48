package model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.Match.Cell;
import model.Match.LandType;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class MapMethods {
    public static boolean saveMap(SavableMap map){
        //TODO: cant save a map again!
        Gson gson = new Gson();
        String json = gson.toJson(map);
        try {
            FileReader fileReader = new FileReader("Maps.json");
            ArrayList<SavableMap> maps = gson.fromJson(fileReader, new TypeToken<ArrayList<SavableMap>>(){}.getType());
            if(checkForClonedMaps(map)){
                return false;
            }
            maps.add(map);
            FileWriter fileWriter = new FileWriter("Maps.json");
            gson.toJson(maps, fileWriter);
            fileWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
    public static ArrayList<SavableMap> loadMaps(){
        Gson gson = new Gson();
        FileReader fileReader = null;
        try {
            fileReader = new FileReader("Maps.json");
            ArrayList<SavableMap> maps = gson.fromJson(fileReader, new TypeToken<ArrayList<SavableMap>>(){}.getType());
            fileReader.close();
            return maps;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static SavableMap convertMapToSavableMap(Cell[][] map){
        SavableMap savableMap = new SavableMap();
        savableMap.map = new String[map.length][map[0].length];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < savableMap.map[0].length; j++) {
                savableMap.map[i][j] = map[i][j].getLandType().toString();
            }
        }
        return savableMap;
    }

    public static Cell[][] convertSavableMapToMap(SavableMap savableMap){
        Cell[][] map = new Cell[savableMap.map.length][savableMap.map[0].length];
        for (int i = 0; i < savableMap.map.length; i++) {
            for (int j = 0; j < savableMap.map[0].length; j++) {
                map[i][j] = new Cell(i, j, LandType.getLandType(savableMap.map[i][j]));
            }
        }
        return map;
    }

    public static boolean checkForClonedMaps(SavableMap map){
        ArrayList<SavableMap> maps = loadMaps();
        for (SavableMap savableMap : maps) {
            if(savableMap.equals(map)){
                return false;
            }
        }
        return true;
    }
}