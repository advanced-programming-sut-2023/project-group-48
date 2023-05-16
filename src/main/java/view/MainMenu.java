package view;

import controller.Controller;
import controller.MainMenuController;
import view.Commands.MainMenuCommands;
import view.Messages.MainMenuMessages;

import java.io.IOException;
import java.util.regex.Matcher;

public class MainMenu extends Menu {
    private final MainMenuController mainMenuController;

    public MainMenu(Controller controller) {
        super(controller);
        this.mainMenuController = new MainMenuController(controller);
    }

    @Override
    public void run() throws IOException {
        while (true) {
            String command = scanner.nextLine().trim();
            Matcher matcher;
            if (command.matches("^exit$")) {
                controller.exit();
                break;
            } else if (command.matches("^enter\\s+profile\\s+menu$")) {
                System.out.println(controller.enterProfileMenu());
                break;
            } else if (command.matches("^user\\s+logout$")) {
                System.out.println(controller.logout());
                break;
            } else if ((matcher = MainMenuCommands.getMatcher(command, MainMenuCommands.MATCH_START)) != null) {
                String result = mainMenuController.startMatch(Integer.parseInt(matcher.group("rounds")), Integer.parseInt(matcher.group("mapNumber")), matcher.group("userNames"));
                System.out.println(result);
                if (result.startsWith("match started!")) return;
            } else
                System.out.println("Invalid Command!");
        }
    }

}
