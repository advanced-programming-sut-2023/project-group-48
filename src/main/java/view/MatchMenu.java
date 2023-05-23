package view;

import controller.Controller;
import controller.MatchMenuController;
import model.Match.Direction;
import model.Match.Match;
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
            if (command.matches("next turn")) {
                String result = matchMenuController.nextTurn();
                System.out.println(result);
                if (result.endsWith("entered main Menu!")) break;
            } else if (command.matches("^enter\\s+shop\\s+menu$")) {
                System.out.println(controller.enterShopMenu());
                break;
            } else if (command.matches("^enter\\s+trade\\s+menu$")) {
                System.out.println(controller.enterTradeMenu());
                break;
            } else if (command.matches("^enter\\s+map\\s+menu$")) {
                System.out.println(controller.enterMapMenu());
                break;
            } else if (command.matches("^show\\s+popularity\\s+factors$")) {
                System.out.println(matchMenuController.showPopularityFactors());
            } else if (command.matches("^show\\s+popularity$")) {
                System.out.println(matchMenuController.showPopularity());
            } else if (command.matches("^show\\s+food\\s+list$")) {
                System.out.println(matchMenuController.showFoodList());
            } else if ((matcher = MatchMenuCommands.getMatcher(command, MatchMenuCommands.FOOD_RATE)) != null) {
                System.out.println(matchMenuController.setFoodRate(Integer.parseInt(matcher.group("rateNumber"))));
            } else if (command.matches("^food\\s+rate\\s+show$")) {
                System.out.println(matchMenuController.showFoodRate());
            } else if ((matcher = MatchMenuCommands.getMatcher(command, MatchMenuCommands.TAX_RATE)) != null) {
                System.out.println(matchMenuController.setTaxRate(Integer.parseInt(matcher.group("rateNumber"))));
            } else if (command.matches("^tax\\s+rate\\s+show$")) {
                System.out.println(matchMenuController.showTaxRate());
            } else if ((matcher = MatchMenuCommands.getMatcher(command, MatchMenuCommands.FEAR_RATE)) != null) {
                System.out.println(matchMenuController.setFearRate(Integer.parseInt(matcher.group("rateNumber"))));
            } else if ((matcher = MatchMenuCommands.getMatcher(command, MatchMenuCommands.DROP_BUILDING)) != null) {
                System.out.println(matchMenuController.dropBuilding(Integer.parseInt(matcher.group("y")), Integer.parseInt(matcher.group("y")), Controller.getRemovedQuotationMarks(matcher.group("type")))); // TODO direction
            } else if ((matcher = MatchMenuCommands.getMatcher(command, MatchMenuCommands.SELECT_BUILDING)) != null) {
                System.out.println(matchMenuController.selectBuilding(Integer.parseInt(matcher.group("y")), Integer.parseInt(matcher.group("x"))));
            } else if ((matcher = MatchMenuCommands.getMatcher(command, MatchMenuCommands.CREATE_UNIT)) != null) {
                System.out.println(matchMenuController.createUnit(Controller.getRemovedQuotationMarks(matcher.group("type")), Integer.parseInt(matcher.group("count"))));
            } else if (command.matches("^repair$")) {
                System.out.println(matchMenuController.repair());
            } else if ((matcher = MatchMenuCommands.getMatcher(command, MatchMenuCommands.SELECT_UNIT)) != null) {
                System.out.println(matchMenuController.selectUnit(Integer.parseInt(matcher.group("y")), Integer.parseInt(matcher.group("x"))));
            } else if ((matcher = MatchMenuCommands.getMatcher(command, MatchMenuCommands.MOVE_UNIT)) != null) {
                System.out.println(matchMenuController.moveUnit(Integer.parseInt(matcher.group("y")), Integer.parseInt(matcher.group("x"))));
            } else if ((matcher = MatchMenuCommands.getMatcher(command, MatchMenuCommands.PATROL_UNIT)) != null) {
                System.out.println(matchMenuController.patrolUnit(Integer.parseInt(matcher.group("y1")), Integer.parseInt(matcher.group("x1")),
                        Integer.parseInt(matcher.group("y2")), Integer.parseInt(matcher.group("x2"))));
            } else if ((matcher = MatchMenuCommands.getMatcher(command, MatchMenuCommands.SET_UNIT_STATE)) != null) {
                System.out.println(matchMenuController.setTroopsState(Integer.parseInt(matcher.group("y")), Integer.parseInt(matcher.group("x")), matcher.group("state")));
            } else if ((matcher = MatchMenuCommands.getMatcher(command, MatchMenuCommands.ATTACK_ENEMY)) != null) {
                // TODO
            } else if ((matcher = MatchMenuCommands.getMatcher(command, MatchMenuCommands.AIR_ATTACK)) != null) {
                // TODO
            } else if ((matcher = MatchMenuCommands.getMatcher(command, MatchMenuCommands.POUR_OIL)) != null) {
                // TODO
            } else if ((matcher = MatchMenuCommands.getMatcher(command, MatchMenuCommands.DIG_TUNNEL)) != null) {
                // TODO
            } else if ((matcher = MatchMenuCommands.getMatcher(command, MatchMenuCommands.BUILD_EQUIPMENT)) != null) {
                // TODO
            } else if (command.matches("^disband\\s+unit$")) {
                System.out.println(matchMenuController.disbandUnit());
            } else if ((matcher = MatchMenuCommands.getMatcher(command, MatchMenuCommands.SET_TEXTURE_ONE_BLOCK)) != null) {
                System.out.println(matchMenuController.setTexture(Integer.parseInt(matcher.group("y")),
                        Integer.parseInt(matcher.group("x")), Controller.getRemovedQuotationMarks(matcher.group("type"))));
            } else if ((matcher = MatchMenuCommands.getMatcher(command, MatchMenuCommands.SET_TEXTURE_BLOCKS)) != null) {
                System.out.println(matchMenuController.setTexture(Integer.parseInt(matcher.group("y1")), Integer.parseInt(matcher.group("x1")),
                        Integer.parseInt(matcher.group("y2")), Integer.parseInt(matcher.group("x2")),
                        Controller.getRemovedQuotationMarks(matcher.group("type"))));
            } else if ((matcher = MatchMenuCommands.getMatcher(command, MatchMenuCommands.CLEAR)) != null) {
                System.out.println(matchMenuController.clear(Integer.parseInt(matcher.group("y")), Integer.parseInt(matcher.group("x"))));
            } else if ((matcher = MatchMenuCommands.getMatcher(command, MatchMenuCommands.DROP_ROCK)) != null) {
                System.out.println(matchMenuController.dropRockOrTree(Integer.parseInt(matcher.group("y")),
                        Integer.parseInt(matcher.group("x")), "rock", Direction.getDirection(matcher.group("direction"))));
            } else if ((matcher = MatchMenuCommands.getMatcher(command, MatchMenuCommands.DROP_TREE)) != null) { // TODO Controller.getRemovedQuotationMarks(matcher.group("treetype"))
                System.out.println(matchMenuController.dropRockOrTree(Integer.parseInt(matcher.group("y")),
                        Integer.parseInt(matcher.group("x")), "tree", Direction.getDirection(matcher.group("direction"))));
            } else if ((matcher = MatchMenuCommands.getMatcher(command, MatchMenuCommands.DROP_UNIT)) != null) {
                // TODO
            } else
                System.out.println("Invalid Command!");
        }
    }

    public void setCurrentMatch(Match match) {
        matchMenuController.setMatch(match);
    }
}
