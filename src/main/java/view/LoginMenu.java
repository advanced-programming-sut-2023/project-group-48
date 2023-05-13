package view;

import controller.Controller;
import controller.LoginMenuController;
import view.Commands.LoginMenuCommands;
import view.Messages.LoginMenuMessages;

import java.util.regex.Matcher;

public class LoginMenu extends Menu {
    private final LoginMenuController loginMenuController;
    public LoginMenu(Controller controller) {
        super(controller);
        this.loginMenuController = new LoginMenuController(controller);
    }

    @Override
    public void run() {
        while (true) {
            String command = scanner.nextLine().trim();
            Matcher matcher;
            if ((matcher = LoginMenuCommands.getMatcher(command, LoginMenuCommands.USERLOGIN)) != null){

            }
            else if ((matcher = LoginMenuCommands.getMatcher(command, LoginMenuCommands.FORGOTPASSWORD)) != null){

            }
            else if (command.matches("^enter\\s+signup\\s+menu$")){

            }
            else
                System.out.println("Invalid Command!");
        }
    }

}