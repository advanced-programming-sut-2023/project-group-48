package view.Commands;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum ShopMenuCommands {
    BUY (new ArrayList<>(List.of("buy" , "\\s+-i\\s+(?<itemname>\".+\"|\\S+)" , "\\s+-a\\s+(?<itemamount>\\d+)"))) ,
    SELL (new ArrayList<>(List.of("sell" , "\\s+-i\\s+(?<itemname>\".+\"|\\S+)" , "\\s+-a\\s+(?<itemamount>\\d+)"))) ;

    private ArrayList<String> regexs;

    ShopMenuCommands (ArrayList<String> regexs){
        this.regexs = regexs;
    }

    public static Matcher getMatcher(String input, ShopMenuCommands commands){
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
        ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
        result.add(new ArrayList<String>());
        for (int i = 0; i < list.size(); i++) {
            ArrayList<ArrayList<String>> currentArr = new ArrayList<ArrayList<String>>();
            for (ArrayList<String> l : result) {
                for (int j = 0; j < l.size()+1; j++) {
                    l.add(j, list.get(i));
                    ArrayList<String> temp = new ArrayList<String>(l);
                    currentArr.add(temp);
                    l.remove(j);
                }
            }
            result = new ArrayList<ArrayList<String>>(currentArr);
        }
        return result;
    }

}
