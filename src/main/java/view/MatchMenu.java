package view;

import controller.Controller;
import controller.MatchMenuController;
import model.Match.Direction;
import view.Commands.MatchMenuCommands;
import view.Messages.MatchMenuMessages;

import java.util.regex.Matcher;

public class MatchMenu extends Menu {
    private final MatchMenuController matchMenuController;
    public MatchMenu(Controller controller) {
        super(controller);
        this.matchMenuController = new MatchMenuController(controller);
    }

    @Override
    public void run() {
        while (true) {
            String command = scanner.nextLine().trim();
            Matcher matcher;
            if (command.matches("^enter\\s+shop\\s+menu$")) {
                System.out.println(controller.enterShopMenu());
            }
            else if (command.matches("^enter\\s+trade\\s+menu$")) {
                System.out.println(controller.enterTradeMenu());
            }
            else if (command.matches("^enter\\s+map\\s+menu$")) {
                System.out.println(controller.enterShopMenu());
            }
            else if (command.matches("^show\\s+popularity\\s+factors$")){
                System.out.println(matchMenuController.showPopularityFactors());
            }
            else if (command.matches("^show\\s+popularity$")){
                System.out.println(matchMenuController.showPopularity());
            }
            else if (command.matches("^show\\s+food\\s+list$")){
                System.out.println(matchMenuController.showFoodList());
            }
            else if((matcher = MatchMenuCommands.getMatcher(command, MatchMenuCommands.FOODRATE)) != null) {
                System.out.println(matchMenuController.setFoodRate(Integer.parseInt(matcher.group("ratenumber"))));
            }
            else if (command.matches("^food\\s+rate\\s+show$")){
                System.out.println(matchMenuController.showFoodRate());
            }
            else if ((matcher = MatchMenuCommands.getMatcher(command, MatchMenuCommands.TAXRATE)) != null){
                System.out.println(matchMenuController.setTaxRate(Integer.parseInt(matcher.group("ratenumber"))));
            }
            else if (command.matches("^tax\\s+rate\\s+show$")){
                System.out.println(matchMenuController.showTaxRate());
            }
            else if ((matcher = MatchMenuCommands.getMatcher(command, MatchMenuCommands.FEARRATE)) != null){
                System.out.println(matchMenuController.setFearRate(Integer.parseInt(matcher.group("ratenumber"))));
            }
            else if ((matcher = MatchMenuCommands.getMatcher(command, MatchMenuCommands.DROPBUILDING)) != null){
                System.out.println(matchMenuController.dropBuilding(Integer.parseInt(matcher.group("y")), Integer.parseInt(matcher.group("y")), matcher.group("type"))); // TODO direction
            }
            else if ((matcher = MatchMenuCommands.getMatcher(command, MatchMenuCommands.SELECTBUILDING)) != null){
                System.out.println(matchMenuController.selectBuilding(Integer.parseInt(matcher.group("y")), Integer.parseInt(matcher.group("x"))));
            }
            else if ((matcher = MatchMenuCommands.getMatcher(command, MatchMenuCommands.CREATEUNIT)) != null){
                System.out.println(matchMenuController.createUnit(matcher.group("type"), Integer.parseInt(matcher.group("count"))));
            }
            else if (command.matches("^repair$")){
                System.out.println(matchMenuController.repair());
            }
            else if ((matcher = MatchMenuCommands.getMatcher(command, MatchMenuCommands.SELECTUNIT)) != null){
                System.out.println(matchMenuController.selectUnit(Integer.parseInt(matcher.group("y")), Integer.parseInt(matcher.group("x"))));
            }
            else if ((matcher = MatchMenuCommands.getMatcher(command, MatchMenuCommands.MOVEUNIT)) != null){
                System.out.println(matchMenuController.moveUnit(Integer.parseInt(matcher.group("y")), Integer.parseInt(matcher.group("x"))));
            }
            else if ((matcher = MatchMenuCommands.getMatcher(command, MatchMenuCommands.PATROLUNIT)) != null){
                System.out.println(matchMenuController.patrolUnit(Integer.parseInt(matcher.group("y1")), Integer.parseInt(matcher.group("x1")),
                        Integer.parseInt(matcher.group("y2")), Integer.parseInt(matcher.group("x2"))));
            }
            else if ((matcher = MatchMenuCommands.getMatcher(command, MatchMenuCommands.SETUNITSTATE)) != null){
                System.out.println(matchMenuController.setTroopsState(Integer.parseInt(matcher.group("y")), Integer.parseInt(matcher.group("x")), matcher.group("state")));
            }
            else if ((matcher = MatchMenuCommands.getMatcher(command, MatchMenuCommands.ATTACKENEMY)) != null){
                // TODO
            }
            else if ((matcher = MatchMenuCommands.getMatcher(command, MatchMenuCommands.AIRATTACK)) != null){
                // TODO
            }
            else if ((matcher = MatchMenuCommands.getMatcher(command, MatchMenuCommands.POUROIL)) != null){
                // TODO
            }
            else if ((matcher = MatchMenuCommands.getMatcher(command, MatchMenuCommands.DIGTUNNEL)) != null){
                // TODO
            }
            else if ((matcher = MatchMenuCommands.getMatcher(command, MatchMenuCommands.BUILDEQUIPMENT)) != null){
                // TODO
            }
            else if (command.matches("^disband\\s+unit$")){
                System.out.println(matchMenuController.disbandUnit());
            }
            else if((matcher = MatchMenuCommands.getMatcher(command, MatchMenuCommands.SETTEXTUREONEBLOCK)) != null){
                System.out.println(matchMenuController.setTexture(Integer.parseInt(matcher.group("y")), Integer.parseInt(matcher.group("x")), matcher.group("type")));
            }
            else if ((matcher = MatchMenuCommands.getMatcher(command, MatchMenuCommands.SETTEXTUREBLOCKS)) != null){
                System.out.println(matchMenuController.setTexture(Integer.parseInt(matcher.group("y1")), Integer.parseInt(matcher.group("x1")),
                        Integer.parseInt(matcher.group("y2")), Integer.parseInt(matcher.group("x2")), matcher.group("type")));
            }
            else if ((matcher = MatchMenuCommands.getMatcher(command, MatchMenuCommands.CLEAR)) != null){
                System.out.println(matchMenuController.clear(Integer.parseInt(matcher.group("y")), Integer.parseInt(matcher.group("x"))));
            }
            else if ((matcher = MatchMenuCommands.getMatcher(command, MatchMenuCommands.DROPROCK)) != null){
                System.out.println(matchMenuController.dropRockOrTree(Integer.parseInt(matcher.group("y")), Integer.parseInt(matcher.group("x")), "rock", Direction.getDirection(matcher.group("direction"))));
            }
            else if ((matcher = MatchMenuCommands.getMatcher(command, MatchMenuCommands.DROPTREE)) != null) {
                System.out.println(matchMenuController.dropRockOrTree(Integer.parseInt(matcher.group("y")), Integer.parseInt(matcher.group("x")), "tree", Direction.getDirection(matcher.group("direction"))));
            }
            else if((matcher = MatchMenuCommands.getMatcher(command, MatchMenuCommands.DROPUNIT)) != null){
                // TODO
            }
            else
                System.out.println("Invalid Command!");
        }
    }
}
