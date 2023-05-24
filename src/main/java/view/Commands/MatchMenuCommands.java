package view.Commands;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum MatchMenuCommands {
    FOOD_RATE(new ArrayList<>(List.of("food\\s+rate", "\\s+-r\\s+(?<rateNumber>-?\\d+)"))),
    TAX_RATE(new ArrayList<>(List.of("tax\\s+rate", "\\s+-r\\s+(?<rateNumber>-?\\d+)"))),
    FEAR_RATE(new ArrayList<>(List.of("fear\\s+rate", "\\s+-r\\s+(?<rateNumber>-?\\d+)"))),
    DROP_BUILDING(new ArrayList<>(List.of("dropbuilding", "\\s+-x\\s+(?<x>\\d+)", "\\s+-y\\s+(?<y>\\d+)", "\\s+-type\\s+(?<type>\".+\"|\\S+)"))),
    SELECT_BUILDING(new ArrayList<>(List.of("select\\s+building", "\\s+-x\\s+(?<x>\\d+)", "\\s+-y\\s+(?<y>\\d+)"))),
    CREATE_UNIT(new ArrayList<>(List.of("createunit", "\\s+-type\\s+(?<type>\".+\"|\\S+)", "\\s+-count\\s+(?<count>\\d+)"))),
    SELECT_UNIT(new ArrayList<>(List.of("select\\s+unit", "\\s+-x\\s+(?<x>\\d+)", "\\s+-y\\s+(?<y>\\d+)"))),
    MOVE_UNIT(new ArrayList<>(List.of("move\\s+unit\\s+to", "\\s+-x\\s+(?<x>\\d+)", "\\s+-y\\s+(?<y>\\d+)"))),
    PATROL_UNIT(new ArrayList<>(List.of("patrol\\s+unit", "\\s+-x1\\s+(?<x1>\\d+)", "\\s+-x2\\s+(?<x2>\\d+)", "\\s+-y1\\s+(?<y1>\\d+)", "\\s+-y2\\s+(?<y2>\\d+)"))),
    SET_UNIT_STATE(new ArrayList<>(List.of("set", "\\s+-x\\s+(?<x>\\d+)", "\\s+-y\\s+(?<y>\\d+)", "\\s+-s\\s+(?<state>\\S+)"))),
    ATTACK_ENEMY(new ArrayList<>(List.of("attack", "\\s+-e\\s+(?<x>\\d+)\\s+(?<y>\\d+)"))),
    AIR_ATTACK(new ArrayList<>(List.of("attack", "\\s+-x\\s+(?<x>\\d+)", "\\s+-y\\s+(?<y>\\d+)"))),
    POUR_OIL(new ArrayList<>(List.of("pour\\s+oil", "\\s+-d\\s+(?<direction>\\S+)"))),
    DIG_TUNNEL(new ArrayList<>(List.of("dig\\s+tunnel", "\\s+-x\\s+(?<x>\\d+)", "\\s+-y\\s+(?<y>\\d+)"))),
    COLLAPSE_TUNNEL(new ArrayList<>(List.of("collapse\\s+tunnel", "\\s+-x\\s+(?<x>\\d+)", "\\s+-y\\s+(?<y>\\d+)"))),
    DIG_DITCH(new ArrayList<>(List.of("dig\\s+Pitch Ditch", "\\s+-x\\s+(?<x>\\d+)", "\\s+-y\\s+(?<y>\\d+)"))),
    BUILD_EQUIPMENT(new ArrayList<>(List.of("build", "\\s+-q\\s+(?<equipmentName>\".+\"|\\S+)"))),
    SET_TEXTURE_ONE_BLOCK(new ArrayList<>(List.of("settexture", "\\s+-x\\s+(?<x>\\d+)", "\\s+-y\\s+(?<y>\\d+)", "\\s+-t\\s+(?<type>\".+\"|\\S+)"))),
    SET_TEXTURE_BLOCKS(new ArrayList<>(List.of("settexture", "\\s+-x1\\s+(?<x1>\\d+)", "\\s+-y1\\s+(?<y1>\\d+)", "\\s+-x2\\s+(?<x2>\\d+)", "\\s+-y2\\s+(?<y2>\\d+)", "\\s+-t\\s+(?<type>\".+\"|\\S+)"))),
    CLEAR(new ArrayList<>(List.of("clear", "\\s+-x\\s+(?<x>\\d+)", "\\s+-y\\s+(?<y>\\d+)"))),
    DROP_ROCK(new ArrayList<>(List.of("droprock", "\\s+-x\\s+(?<x>\\d+)", "\\s+-y\\s+(?<y>\\d+)", "\\s+-d\\s+(?<direction>\\S)"))),
    DROP_TREE(new ArrayList<>(List.of("droptree", "\\s+-x\\s+(?<x>\\d+)", "\\s+-y\\s+(?<y>\\d+)", "\\s+-t\\s+(?<type>\".+\"|\\S+)"))),
    DROP_UNIT(new ArrayList<>(List.of("dropunit", "\\s+-x\\s+(?<x>\\d+)", "\\s+-y\\s+(?<y>\\d+)", "\\s+-t\\s+(?<type>\\S+)", "\\s+-c\\s+(?<count>\\d+)")));

    private final ArrayList<String> regexs;

    MatchMenuCommands(ArrayList<String> regexs) {
        this.regexs = regexs;
    }

    public static Matcher getMatcher(String input, MatchMenuCommands commands) {
        String firstRegex = commands.regexs.get(0);
        ArrayList<String> regexes = new ArrayList<>(commands.regexs);
        regexes.remove(0);
        ArrayList<ArrayList<String>> permutations = permutate(regexes);
        for (ArrayList<String> arrayList : permutations) {
            String finalRegex = firstRegex;
            for (int i = 0; i < arrayList.size(); i++) {
                finalRegex += arrayList.get(i);
            }
            Matcher matcher = Pattern.compile(finalRegex).matcher(input);
            if (matcher.matches()) {
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
                for (int j = 0; j < l.size() + 1; j++) {
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
