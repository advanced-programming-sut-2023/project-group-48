package view;

import controller.Controller;
import controller.MainMenuController;
import view.Commands.MainMenuCommands;
import view.Messages.MainMenuMessages;

import java.util.regex.Matcher;

public class MainMenu extends Menu {
    private final MainMenuController mainMenuController;
    public MainMenu(Controller controller) {
        super(controller);
        this.mainMenuController = new MainMenuController(controller);
    }

    //private enum Commands {}

    @Override
    public void run() {
        while (true){
            String command = scanner.nextLine().trim();
            Matcher matcher;
            if (command.matches("^user\\s+logout$")){

            }
            else if (//TODO){ // match start command

            }
            else
                System.out.println("Invalid Command!");
        }
    }

}
