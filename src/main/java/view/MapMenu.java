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
            if (command.matches("^back$")) {
                System.out.println(controller.enterMatchMenu());
                break;
            }
            else if ((matcher = MapMenuCommands.getMatcher(command , MapMenuCommands.SHOW_MAP)) != null ){
                System.out.println(mapMenuController.showMap(Integer.parseInt(matcher.group("y")), Integer.parseInt(matcher.group("x"))));
            }
            else if ((matcher = MapMenuCommands.getMatcher(command , MapMenuCommands.MOVE_MAP)) != null ){
                System.out.println(mapMenuController.changeCurrentCell(new String[]{matcher.group("firstDirection"), matcher.group("firstNumber")},
                                                                       new String[]{matcher.group("secondDirection"), matcher.group("secondNumber")}));
            }
            else if ((matcher = MapMenuCommands.getMatcher(command , MapMenuCommands.MAP_DETAILS)) != null ){
                System.out.println(mapMenuController.showCellDetails(Integer.parseInt(matcher.group("y")), Integer.parseInt(matcher.group("x"))));
            }
            else
                System.out.println("Invalid Command!");
        }
    }
}

