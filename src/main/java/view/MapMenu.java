package view;

import controller.Controller;
import controller.MapMenuController;
import view.Commands.MapMenuCommands;
import view.Messages.MapMenuMessages;

import java.util.regex.Matcher;

public class MapMenu extends Menu {
    private final MapMenuController mapMenuController;
    public MapMenu(Controller controller) {
        super(controller);
        this.mapMenuController = new MapMenuController(controller);
    }

    /*private enum Commands {
        LOGIN(new ArrayList<>("command", "-i asd", "-jasd"));

        private final ArrayList<String> regex;

        private Commands(ArrayList<String> regex) {
            this.regex = regex;
        }

        public static getMatcher(String input, Command command) {

        }
    }*/

    @Override
    public void run() {
        while (true){
            String command = scanner.nextLine().trim();
            Matcher matcher;
            if ((matcher = MapMenuCommands.getMatcher(command , MapMenuCommands.SHOWMAP)) != null ){

            }
            else if ((matcher = MapMenuCommands.getMatcher(command , MapMenuCommands.MOVEMAP)) != null ){

            }
            else if ((matcher = MapMenuCommands.getMatcher(command , MapMenuCommands.MAPDETAILS)) != null ){

            }
            else if (command.matches("^exit$")){

            }
            else
                System.out.println("Invalid Command!");
        }
    }
}

