package view.Commands;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum MatchMenuCommands {
    FOODRATE{ "food\\s+rate" , "\\s+-r\\s+(?<ratenumber>\\d+)" } ,
    TAXRATE{ "tax\\s+rate" , "\\s+-r\\s+(?<ratenumber>\\d+)" } ,
    FEARRATE{ "fear\\s+rate" , "\\s+-r\\s+(?<ratenumber>\\d+)" } ,
    DROPBUILDING{ "dropbuilding" , "\\s+-x\\s+(?<x>\\d+)" , "\\s+-y\\s+(?<y>\\d+)" , "\\s+-type\\s+(?<type>\\S+)" } ,
    SELECTBUILDING{ "select\\s+building" , "\\s+-x\\s+(?<x>\\d+)" , "\\s+-y\\s+(?<y>\\d+)" } ,
    CREATEUNIT { "createunit" , "\\s+-type\\s+(?<type>\\S+)" , "\\s+-count\\s+(?<count>\\d+)" } ,
    SELECTUNIT { "select\\s+unit" , "\\s+-x\\s+(?<x>\\d+)" , "\\s+-y\\s+(?<y>\\d+)" } ,
    MOVEUNIT { "move\\s+unit\\s+to" , "\\s+-x\\s+(?<x>\\d+)" , "\\s+-y\\s+(?<y>\\d+)" } ,
    PATROLUNIT { "patrol\\s+unit" , "\\s+-x1\\s+(?<x1>\\d+)" , "\\s+-x2\\s+(?<x2>\\d+)" , "\\s+-y1\\s+(?<y1>\\d+)" , "\\s+-y2\\s+(?<y2>\\d+)" } ,
    SETUNITSTATE { "set" , "\\s+-x\\s+(?<x>\\d+)" , "\\s+-y\\s+(?<y>\\d+)" , "\\s+-s\\s+(?<state>\\S+)" } ,
    ATTACKENEMY { "attack" , "\\s+-e\\s+(?<x>\\d+)\\s+(?<y>\\d+)" } ,
    AIRATTACK { "attack" , "\\s+-x\\s+(?<x>\\d+)" , "\\s+-y\\s+(?<y>\\d+)" } ,
    POUROIL { "pour\\s+oil" , "\\s+-d\\s+(?<direction>\\S+)" } ,
    DIGTUNNEL { "dig\\s+tunnel" , "\\s+-x\\s+(?<x>\\d+)" , "\\s+-y\\s+(?<y>\\d+)" } ,
    BUILDEQUIPMENT { "build" , "\\s+-q\\s+(?<equipmentname>\\S+)" } ,
    SETTEXTUREONEBLOCK { "settexture" , "\\s+-x\\s+(?<x>\\d+)" , "\\s+-y\\s+(?<y>\\d+)" , "\\s+-t\\s+(?<type>\\S+)" } ,
    SETTEXTUREBLOCKS { "settexture" , "\\s+-x1\\s+(?<x1>\\d+)" , "\\s+-y1\\s+(?<y1>\\d+)" , "\\s+-x2\\s+(?<x2>\\d+)" , "\\s+-y2\\s+(?<y2>\\d+)" , "\\s+-t\\s+(?<type>\\S+)" } ,
    CLEAR { "clear" , "\\s+-x\\s+(?<x>\\d+)" , "\\s+-y\\s+(?<y>\\d+)" } ,
    DROPROCK { "droprock" , "\\s+-x\\s+(?<x>\\d+)" , "\\s+-y\\s+(?<y>\\d+)" , "\\s+-d\\s+(?<direction>\\S)" } ,
    DROPTREE { "droptree" , "\\s+-x\\s+(?<x>\\d+)" , "\\s+-y\\s+(?<y>\\d+)" , "\\s+-t\\s+(?<type>\\S+)" } ,
    DROPUNIT { "dropunit" , "\\s+-x\\s+(?<x>\\d+)" , "\\s+-y\\s+(?<y>\\d+)" , "\\s+-t\\s+(?<type>\\S+)" , "\\s+-c\\s+(?<count>\\d+)" } ;

    private ArrayList<String> regexs;

    MatchMenuCommands (ArrayList<String> regexs){
        this.regexs = regexs;
    }

    public static Matcher getMatcher(String input, MatchMenuCommands commands){
        String firstRegex = commands.regexs.get(0);
        ArrayList<String> regexes = new ArrayList<>();
        regexes = commands.regexs;
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
