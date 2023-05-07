package view;

import controller.Controller;
import controller.MatchMenuController;
import view.Commands.MatchMenuCommands;
import view.Messages.MatchMenuMessages;

import java.util.regex.Matcher;

public class MatchMenu extends Menu {
    private final MatchMenuController matchMenuController;
    public MatchMenu(Controller controller) {
        super(controller);
        this.matchMenuController = new MatchMenuController(controller);
    }

    //private enum Commands {}

    @Override
    public void run() {
        while (true) {
            String command = scanner.nextLine().trim();
            Matcher matcher;
            if (command.matches("^show\\s+popularity\\s+factors$")){

            }
            else if (command.matches("^show\\s+popularity$")){

            }
            else if (command.matches("^show\\s+food\\s+list$")){

            }
            else if((matcher = MatchMenuCommands.getMatcher(command, MatchMenuCommands.FOODRATE)) != null) {

            }
            else if (command.matches("^food\\s+rate\\s+show$")){

            }
            else if ((matcher = MatchMenuCommands.getMatcher(command, MatchMenuCommands.TAXRATE)) != null){

            }
            else if (command.matches("^tax\\s+rate\\s+show$")){

            }
            else if ((matcher = MatchMenuCommands.getMatcher(command, MatchMenuCommands.FEARRATE)) != null){

            }
            else if ((matcher = MatchMenuCommands.getMatcher(command, MatchMenuCommands.DROPBUILDING)) != null){

            }
            else if ((matcher = MatchMenuCommands.getMatcher(command, MatchMenuCommands.SELECTBUILDING)) != null){

            }
            else if ((matcher = MatchMenuCommands.getMatcher(command, MatchMenuCommands.CREATEUNIT)) != null){

            }
            else if (command.matches("^repair$")){

            }
            else if ((matcher = MatchMenuCommands.getMatcher(command, MatchMenuCommands.SELECTUNIT)) != null){

            }
            else if ((matcher = MatchMenuCommands.getMatcher(command, MatchMenuCommands.MOVEUNIT)) != null){

            }
            else if ((matcher = MatchMenuCommands.getMatcher(command, MatchMenuCommands.PATROLUNIT)) != null){

            }
            else if ((matcher = MatchMenuCommands.getMatcher(command, MatchMenuCommands.SETUNITSTATE)) != null){

            }
            else if ((matcher = MatchMenuCommands.getMatcher(command, MatchMenuCommands.ATTACKENEMY)) != null){

            }
            else if ((matcher = MatchMenuCommands.getMatcher(command, MatchMenuCommands.AIRATTACK)) != null){

            }
            else if ((matcher = MatchMenuCommands.getMatcher(command, MatchMenuCommands.POUROIL)) != null){

            }
            else if ((matcher = MatchMenuCommands.getMatcher(command, MatchMenuCommands.DIGTUNNEL)) != null){

            }
            else if ((matcher = MatchMenuCommands.getMatcher(command, MatchMenuCommands.BUILDEQUIPMENT)) != null){

            }
            else if (command.matches("^disband\\s+unit$")){

            }
            else if((matcher = MatchMenuCommands.getMatcher(command, MatchMenuCommands.SETTEXTUREONEBLOCK)) != null){

            }
            else if ((matcher = MatchMenuCommands.getMatcher(command, MatchMenuCommands.SETTEXTUREBLOCKS)) != null){

            }
            else if ((matcher = MatchMenuCommands.getMatcher(command, MatchMenuCommands.CLEAR)) != null){

            }
            else if ((matcher = MatchMenuCommands.getMatcher(command, MatchMenuCommands.DROPROCK)) != null){

            }
            else if ((matcher = MatchMenuCommands.getMatcher(command, MatchMenuCommands.DROPTREE)) != null) {

            }
            else if((matcher = MatchMenuCommands.getMatcher(command, MatchMenuCommands.DROPUNIT)) != null){

            }
            else
                System.out.println("Invalid Command!");
        }
    }
}
