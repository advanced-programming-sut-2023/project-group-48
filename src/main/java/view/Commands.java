package view;

        import java.util.ArrayList;
        import java.util.regex.Matcher;
        import java.util.regex.Pattern;

public enum Commands {
    //SIGNUP
    CREATEUSER{"" , "" , "" , "" , ""} ,
    PICKSECURITYQUESTION{"" , "" , ""} ,
    ANSWERSECURITYQUESTION{} ,
    REENTERRANDOMPASSWORD{} ,
    //(STATIC COMMAND)
    ENTERCAPTCHA{} ,
    //LOGIN
    USERLOGIN{"" , "" , ""} ,
    FORGOTPASSWORD{""} ,
    //PROFILE
    USERNAMECHANGE{""} ,
    NICKNAMECHANGE{""} ,
    PASSWORDCHANGE{"" , ""} ,
    REENTERNEWPASSWORD{} ,
    EMAILCHANGE{""} ,
    SLOGANCHANGE{""} ,
    SLOGANREMOVE{} ,
    DISPLAYHIGHSCORE{} ,
    DISPLAYRANK{} ,
    DISPLAYSLOGAN{} ,
    DISPLAYPROFILE{} ,
    //MAP
    SHOWMAP{"" , ""} ,
    MOVEMAP{"" , "" , "" , ""} ,
    MAPDETAILS{"" , ""} ,
    EXITMAP{} ,
    //MAIN
    USERLOGOUT{} ,
    STARTMATCH{} ,
    //MATCH
    SHOWPOPULARITYFACTORS{} ,
    SHOWPOPULARITY{} ,
    SHOWFOODLIST{} ,
    FOODRATE{""} ,
    FOODRATESHOW{} ,
    TAXRATE{""} ,
    TAXRATESHOW{} ,
    FEARRATE{""} ,
    DROPBUILDING{"" , "" , ""} ,
    SELECTBUILDING{"" , ""} ,
    CREATEUNIT{"" , ""} ,
    REPAIR{} ,
    SELECTUNIT{"" , ""} ,
    MOVEUNIT{"" , ""} ,
    PATROLUNIT{"" , "" , "" , ""} ,
    SETUNITSTATE{"" , "" , ""} ,
    ATTACK{""} ,
    AIRATTACK{"" , ""} ,
    POUROIL{""} ,
    DIGTUNNEL{"" , ""} ,
    BUILDEQUIPMENT{""} ,
    DISBANDUNIT{} ,
    SETTEXTUREONEBLOCK{"" , "" , ""} ,
    SETTEXTUREBLOCKS{"" , "" , "" , "" , ""} ,
    CLEARBLOCK{"" , ""} ,
    DROPROCK{"" , "" , ""} ,
    DROPTREE{"" , "" , ""} ,
    DROPUNIT{"" , "" , "" , ""} ,
    //TRADE
    TRADE{"" , "" , "" , ""} ,
    TRADELIST{} ,
    TRADEACCEPT{"" , ""} ,
    TRADEHISTORY{} ,
    //SHOP
    SHOWPRICELIST{} ,
    BUY{"" , ""} ,
    SELL{"" , ""}
    ;

    private ArrayList<String> regexs;

    Commands (ArrayList<String> regexs){
        this.regexs = regexs;
    }

    public static Matcher getMatcher(String input, Commands commands){
        String finalregex;
        for() {
            //...
            Matcher matcher = Pattern.compile(finalregex).matcher(input);
            if (matcher.matches()) return matcher;
        }
        return null;
    }
}
