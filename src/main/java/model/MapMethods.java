package model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.Match.Cell;
import model.Match.LandType;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class MapMethods {
//    static {
//            SavableMap mapp = new SavableMap();
//            mapp.name = "map2";
//            mapp.map = new String[5][5];
//            for(int i = 0; i < 5; i++){
//                for(int j = 0; j < 5; j++){
//                    mapp.map[i][j] = "rock";
//                }
//            }
//            saveMap(mapp);
//    }
    public static boolean saveMap(SavableMap map){

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

        ArrayList<SavableMap> maps = new ArrayList<>();
        Gson gson = new Gson();
        FileReader fileReader = null;
        try {
            fileReader = new FileReader("Maps.json");
            maps = gson.fromJson(fileReader, new TypeToken<ArrayList<SavableMap>>(){}.getType());
            if(maps == null){
                maps = new ArrayList<>();
            }
            fileReader.close();
            return maps;
        } catch (Exception e) {
            e.printStackTrace();
            return maps;
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