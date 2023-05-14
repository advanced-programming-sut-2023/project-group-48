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
        while (true){
            String command = scanner.nextLine().trim();
            Matcher matcher;
            if (command.matches("^user\\s+logout$")){
                System.out.println(controller.logout());
                break;
            }
            else if ((matcher = MainMenuCommands.getMatcher(command, MainMenuCommands.MATCHSTART)) != null){

            }
            else if (command.matches("^enter\\s+profile\\s+menu$")){
                System.out.println(controller.enterProfileMenu());
                break;
            }
            else if (command.matches("^enter\\s+match\\s+menu$")){
                System.out.println(controller.enterMatchMenu());
                break;
            }
            else
                System.out.println("Invalid Command!");
        }
    }

}
