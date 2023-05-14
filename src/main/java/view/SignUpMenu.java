package view;

import controller.Controller;
import controller.SignUpMenuController;
import view.Commands.SignUpMenuCommands;
import view.Messages.SignUpMenuMessages;

import java.io.IOException;
import java.util.regex.Matcher;

public class SignUpMenu extends Menu {

    private final SignUpMenuController signUpMenuController;

    public SignUpMenu(Controller controller) {
        super(controller);
        this.signUpMenuController = new SignUpMenuController(controller);
    }

    @Override
    public void run() throws IOException {
        while (true) {
            String command = scanner.nextLine().trim();
            Matcher matcher;
            if((matcher = SignUpMenuCommands.getMatcher(command, SignUpMenuCommands.CREATE_USER)) != null){
                createUser(matcher);
            }
            else if (command.matches("^enter\\s+login\\s+menu$")){
                System.out.println(controller.enterLoginMenu());
                break;
            }
            else if (command.matches("^exit$")){
                controller.exit();
                break;
            }
            else
                System.out.println("Invalid Command!");
        }
    }

    private void createUser(Matcher matcher) throws IOException {
        String username = matcher.group("username");
        String password = matcher.group("password");
        String passConfirmation = matcher.group("passConfirm");
        String email = matcher.group("email");
        String nickname = matcher.group("nickname");
        String slogan;
        String result;
        if (matcher.groupCount() == 6) {
            slogan = matcher.group("slogan");
            result = signUpMenuController.createUserStep(Controller.getRemovedQuotationMarks(username), password,
                    passConfirmation, email, Controller.getRemovedQuotationMarks(nickname), Controller.getRemovedQuotationMarks(slogan));
        }
        else
            result = signUpMenuController.createUserStep(Controller.getRemovedQuotationMarks(username), password,
                    passConfirmation, email, Controller.getRemovedQuotationMarks(nickname));
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

    private void chooseSecurityQuestion() throws IOException {
        while(true){
            Matcher matcher = SignUpMenuCommands.getMatcher(scanner.nextLine().trim(), SignUpMenuCommands.ANSWER_SECURITY_QUESTION);
            if (!matcher.matches()) {
                System.out.println("Invalid Command!\nPlease pick your security question again:");
            }
            else if (!(matcher.group("answer")).equals(matcher.group("answerConfirm"))){
                System.out.println("answer confirmation is not correct!");
            }
            else {
                System.out.println(signUpMenuController.captchaStep(Controller.getRemovedQuotationMarks(matcher.group("questionNumber")),
                        Controller.getRemovedQuotationMarks(matcher.group("answer"))));
                answerCaptcha();
                break;
            }
        }
    }

    private void answerCaptcha() throws IOException {
        while(true){
            String answer = signUpMenuController.finalStep(scanner.nextLine().trim());
            System.out.println(answer);
            if (answer.equals("user created successfully!")) break;
        }
    }
}
