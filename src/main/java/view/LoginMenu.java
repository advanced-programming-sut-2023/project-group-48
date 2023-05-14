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
                userLogin(matcher);
            }
            else if ((matcher = LoginMenuCommands.getMatcher(command, LoginMenuCommands.FORGOTPASSWORD)) != null){

            }
            else if (command.matches("^enter\\s+signup\\s+menu$")){
                System.out.println(controller.enterSignupMenu());
                break;
            }
            else
                System.out.println("Invalid Command!");
        }
    }

    private void userLogin(Matcher matcher){
        String username = matcher.group("username");
        String password = matcher.group("password");
        boolean stayLoggedIn = false;
        if (matcher.groupCount() == 3) {
            stayLoggedIn = true;
        }
        String result = loginMenuController.login(username, password, stayLoggedIn);
        System.out.println(result);

    }

}