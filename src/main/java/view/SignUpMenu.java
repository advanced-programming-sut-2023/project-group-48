package view;

import controller.Controller;
import controller.SignUpMenuController;
import view.Commands.SignUpMenuCommands;
import view.Messages.SignUpMenuMessages;

import java.util.regex.Matcher;

public class SignUpMenu extends Menu {
    private final SignUpMenuController signUpMenuController;
    public SignUpMenu(Controller controller) {
        super(controller);
        this.signUpMenuController = new SignUpMenuController(controller);
    }

    //private enum Commands {}

    @Override
    public void run() {
        while (true) {
            String command = scanner.nextLine().trim();
            Matcher matcher;
            if((matcher = SignUpMenuCommands.getMatcher(command, SignUpMenuCommands.CREATEUSER)) != null){

            }
            /*else if ((matcher = SignUpMenuCommands.getMatcher(command, SignUpMenuCommands.ANSWERSECURITYWQUESTION)) != null){

            }*/
            else
                System.out.println("Invalid Command!");
        }
    }

}
