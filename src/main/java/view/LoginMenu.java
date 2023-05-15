package view;

import controller.Controller;
import controller.LoginMenuController;
import view.Commands.LoginMenuCommands;
import view.Messages.LoginMenuMessages;

import java.io.IOException;
import java.util.regex.Matcher;

public class LoginMenu extends Menu {
    private final LoginMenuController loginMenuController;

    public LoginMenu(Controller controller) {
        super(controller);
        this.loginMenuController = new LoginMenuController(controller);
    }

    @Override
    public void run() throws IOException {
        while (true) {
            String command = scanner.nextLine().trim();
            Matcher matcher;
            if (command.matches("^enter\\s+sign\\s+up\\s+menu$")) {
                System.out.println(controller.enterSignUpMenu());
            } else if ((matcher = LoginMenuCommands.getMatcher(command, LoginMenuCommands.USER_LOGIN)) != null) {
                if (userLogin(matcher)) return;
            } else if ((matcher = LoginMenuCommands.getMatcher(command, LoginMenuCommands.FORGOT_PASSWORD)) != null) {
                if (forgotPassword(matcher)) return;
            } else if (command.matches("^enter\\s+signup\\s+menu$")) {
                System.out.println(controller.enterSignUpMenu());
                break;
            } else
                System.out.println("Invalid Command!");
        }
    }

    private boolean userLogin(Matcher matcher) throws IOException {
        String username = matcher.group("username");
        String password = matcher.group("password");
        boolean stayLoggedIn = false;
        if (matcher.groupCount() == 3) {
            stayLoggedIn = true;
        }
        String result = loginMenuController.login(Controller.getRemovedQuotationMarks(username), password, stayLoggedIn);
        System.out.println(result);
        if (loginMenuController.getStep() == 0) return false;
        while (true) {
            String answer = loginMenuController.finalStep(scanner.nextLine().trim());
            System.out.println(answer);
            if (answer.startsWith("logged in successfully!")) return true;
        }
    }

    private boolean forgotPassword(Matcher matcher) throws IOException {
        String username = matcher.group("username");
        String result = loginMenuController.forgotPassword(username);
        System.out.println(result);
        if (loginMenuController.getStep() != 2) return false;
        while (true) {
            String answer = loginMenuController.AnswerToSecurityQuestion(scanner.nextLine().trim());
            System.out.println(answer);
            if (answer.startsWith("logged in successfully!")) return true;
        }
    }

}