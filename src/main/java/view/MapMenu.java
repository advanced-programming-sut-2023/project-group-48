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

    @Override
    public void run() {
        while (true){
            String command = scanner.nextLine().trim();
            Matcher matcher;
            if (command.matches("back")) {
                System.out.println(controller.enterMatchMenu());
            }
            else if ((matcher = MapMenuCommands.getMatcher(command , MapMenuCommands.SHOWMAP)) != null ){
                System.out.println(mapMenuController.showMap(Integer.parseInt(matcher.group("y")), Integer.parseInt(matcher.group("x"))));
            }
            else if ((matcher = MapMenuCommands.getMatcher(command , MapMenuCommands.MOVEMAP)) != null ){
                System.out.println(mapMenuController.changeCurrentCell(new String[]{matcher.group("firstdirection"), matcher.group("firstnumber")},
                                                                       new String[]{matcher.group("seconddirection"), matcher.group("secondnumber")}));
            }
            else if ((matcher = MapMenuCommands.getMatcher(command , MapMenuCommands.MAPDETAILS)) != null ){
                System.out.println(mapMenuController.showCellDetails(Integer.parseInt(matcher.group("y")), Integer.parseInt(matcher.group("x"))));
            }
            else
                System.out.println("Invalid Command!");
        }
    }
}

