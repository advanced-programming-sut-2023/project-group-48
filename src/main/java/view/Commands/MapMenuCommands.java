package view.Commands;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum MapMenuCommands {
    SHOW_MAP (new ArrayList<>(List.of("show\\s+map" , "\\s+-x\\s+(?<x>\\d+)" , "\\s+-y\\s+(?<y>\\d+)"))) ,
    MOVE_MAP (new ArrayList<>(List.of("map" , "(\\s+(?<firstDirection>\\S+)(\\s+(?<firstNumber>\\d+))?)?" , "(\\s+(?<secondDirection>\\S+)(\\s+(?<secondNumber>\\d+))?)?"))) ,
    MAP_DETAILS (new ArrayList<>(List.of("show\\s+details" , "\\s+-x\\s+(?<x>\\d+)" , "\\s+-y\\s+(?<y>\\d+)"))) ;



    private final ArrayList<String> regexs;

    MapMenuCommands (ArrayList<String> regexs){
        this.regexs = regexs;
    }

    public static Matcher getMatcher(String input, MapMenuCommands commands){
        String firstRegex = commands.regexs.get(0);
        ArrayList<String> regexes = new ArrayList<>(commands.regexs);
        regexes.remove(0);
        ArrayList<ArrayList<String>> permutations = permutate(regexes);
        for (ArrayList<String> arrayList : permutations){
            String finalRegex = firstRegex;
            for (int i=0 ; i<arrayList.size() ; i++){
                finalRegex += arrayList.get(i);
            }
            Matcher matcher = Pattern.compile(finalRegex).matcher(input);
            if(matcher.matches()){
                return matcher;
            }
        }
        return null;
    }

    private static ArrayList<ArrayList<String>> permutate(ArrayList<String> list) {
        ArrayList<ArrayList<String>> result = new ArrayList<>();
        result.add(new ArrayList<>());
        for (int i = 0; i < list.size(); i++) {
            ArrayList<ArrayList<String>> currentArr = new ArrayList<>();
            for (ArrayList<String> l : result) {
                for (int j = 0; j < l.size()+1; j++) {
                    l.add(j, list.get(i));
                    ArrayList<String> temp = new ArrayList<>(l);
                    currentArr.add(temp);
                    l.remove(j);
                }
            }
            result = new ArrayList<>(currentArr);
        }
        return result;
    }

}
