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

    @Override
    public void run() {
        while (true) {
            String command = scanner.nextLine().trim();
            Matcher matcher;
            if((matcher = SignUpMenuCommands.getMatcher(command, SignUpMenuCommands.CREATEUSER)) != null){
                createUser(matcher);
            }
            else if (command.matches("^enter\\s+login\\s+menu$")){

            }
            else if (command.matches("^exit$")){

            }
            /*else if ((matcher = SignUpMenuCommands.getMatcher(command, SignUpMenuCommands.ANSWERSECURITYWQUESTION)) != null){

            }*/
            else
                System.out.println("Invalid Command!");
        }
    }

    private void createUser(Matcher matcher){
        String username = matcher.group("username");
        String password = matcher.group("password");
        String passConfirmation = matcher.group("passconfirm");
        String email = matcher.group("email");
        String nickname = matcher.group("nickname");
        String slogan = matcher.group("slogan");
        String result = signUpMenuController.createUserStep(username, password, passConfirmation, email, nickname, slogan);
        System.out.println(result);
        if (signUpMenuController.getStep() == 0) return;
        if (result.endsWith("Please re-enter your password here:\\n")){
            String string;
            do {
                string = signUpMenuController.chooseQuestionStep(scanner.nextLine().trim());
                System.out.println(string);

            }while (string.startsWith("password"));
        }
        if (result.startsWith("Your slogan") && !result.endsWith("Please re-enter your password here:\\n")){
            System.out.println(signUpMenuController.chooseQuestionStep());
        }
        chooseSecurityQuestion();
    }

    private void chooseSecurityQuestion(){
        while(true){
            Matcher matcher = SignUpMenuCommands.getMatcher(scanner.nextLine().trim(), SignUpMenuCommands.ANSWERSECURITYQUESTION);
            if (!matcher.matches()) {
                System.out.println("Invalid Command!\nPlease pick your security question again:");
            }
            else if (!(matcher.group("answer")).equals(matcher.group("answerconfirm"))){
                System.out.println("answer confirmation is not correct!");
            }
            else {
                System.out.println(signUpMenuController.captchaStep(matcher.group("questionnumber"), matcher.group("answer")));
                answerCaptcha();
            }
        }
    }

    private void answerCaptcha(){
        //TODO
    }

}
