package view;

import controller.Controller;
import controller.ProfileMenuController;
import view.Commands.ProfileMenuCommands;
import view.Messages.ProfileMenuMessages;

import java.util.regex.Matcher;

public class ProfileMenu extends Menu {
    private final ProfileMenuController profileMenuController;

    public ProfileMenu(Controller controller) {
        super(controller);
        this.profileMenuController = new ProfileMenuController(controller);
    }

    @Override
    public void run() {
        while (true) {
            String command = scanner.nextLine().trim();
            Matcher matcher;
            if (command.matches("^back$")) {
                System.out.println(controller.enterMainMenu());
                break;
            } else if (command.matches("^enter\\s+main\\s+menu$")) {
                System.out.println(controller.enterMainMenu());
                break;
            } else if ((matcher = ProfileMenuCommands.getMatcher(command, ProfileMenuCommands.USERNAME_CHANGE)) != null) {
                System.out.println(profileMenuController.changeUserInfo('u', Controller.getRemovedQuotationMarks(matcher.group("username"))));
            } else if ((matcher = ProfileMenuCommands.getMatcher(command, ProfileMenuCommands.NICKNAME_CHANGE)) != null) {
                System.out.println(profileMenuController.changeUserInfo('n', Controller.getRemovedQuotationMarks(matcher.group("nickname"))));
            } else if ((matcher = ProfileMenuCommands.getMatcher(command, ProfileMenuCommands.PASSWORD_CHANGE)) != null) {
                changePassword(matcher);
            } else if ((matcher = ProfileMenuCommands.getMatcher(command, ProfileMenuCommands.EMAIL_CHANGE)) != null) {
                System.out.println(profileMenuController.changeUserInfo('e', matcher.group("email")));
            } else if ((matcher = ProfileMenuCommands.getMatcher(command, ProfileMenuCommands.SLOGAN_CHANGE)) != null) {
                System.out.println(profileMenuController.changeUserInfo('s', Controller.getRemovedQuotationMarks(matcher.group("slogan"))));
            } else if (command.matches("^Profile\\s+remove\\s+slogan$")) {
                System.out.println(profileMenuController.removeSlogan());
            } else if (command.matches("^profile\\s+display\\s+highscore$")) {
                System.out.println(profileMenuController.displayHighScore());
            } else if (command.matches("^profile\\s+display\\s+rank$")) {
                System.out.println(profileMenuController.displayRank());
            } else if (command.matches("^profile\\s+display\\s+slogan$")) {
                System.out.println(profileMenuController.displaySlogan());
            } else if (command.matches("^profile\\s+display$")) {
                System.out.println(profileMenuController.profileDisplay());
            } else
                System.out.println("Invalid Command!");
        }
    }

    private void changePassword(Matcher matcher) {
        String oldPassword = matcher.group("oldPassword");
        String newPassword = matcher.group("newPassword");
        System.out.println(profileMenuController.changeUserInfo('p', oldPassword));
        while (true) {
            String answer = profileMenuController.captchaStep(scanner.nextLine().trim());
            System.out.println(answer);
            if (answer.equals("Please enter your new password again")) break;
        }
        while (true) {
            String result = (profileMenuController.finalStep(newPassword, scanner.nextLine().trim()));
            System.out.println(result);
            if (!result.equals("Password confirmation is not correct!")) return;
        }
    }

}