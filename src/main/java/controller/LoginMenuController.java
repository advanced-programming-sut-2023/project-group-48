package controller;

import model.Game;
import model.User;

// Parsa
public class LoginMenuController {
    private final Controller controller;
    private int wrongPasswordCount = 0;
    private User attendedUser = null;
    private boolean mustStayLoggedIn = false;
    private User failedUser = null;
    private User forgotUser = null;
    private String captchaAnswer = null;
    private int step = 0;

    public LoginMenuController(Controller controller) {
        this.controller = controller;
    }

    public String login(String username, String password, boolean stayLoggedIn) {
        attendedUser = controller.getGame().getUserByUsername(username);
        if (attendedUser == null) return "Username and password didn’t match!";
        if (attendedUser.isPasswordNotCorrect(password)) {
            if (!attendedUser.equals(failedUser)) {
                failedUser = attendedUser;
                wrongPasswordCount = 1;
            } else {
                wrongPasswordCount++;
            }
            if (wrongPasswordCount >= 3) {
                // TODO
            }
            return "Username and password didn’t match!";
        }

        mustStayLoggedIn = stayLoggedIn;
        failedUser = null;
        step = 1;
        return generateCaptcha();
    }

    public String generateCaptcha() {
        String[] captcha = Game.getRandomCaptcha();
        captchaAnswer = captcha[1];
        return captcha[0] + "\nenter the number: ";
    }

    public String finalStep(String userCaptchaAnswer) {
        if (userCaptchaAnswer.equals("refresh captcha")) {return generateCaptcha();}
        if (!userCaptchaAnswer.equals(captchaAnswer)) return "captcha answer is incorrect!";

        // TODO
        controller.getGame().setCurrentUser(attendedUser);
        return "logged in successfully!\n" + controller.enterMainMenu();
    }

    public String forgotPassword(String username) {
        forgotUser = controller.getGame().getUserByUsername(username);
        if (forgotUser == null) return "user doesn't exist!";

        step = 2;
        return "please enter your answer:\n" + forgotUser.getSecurityQuestion();
    }

    public String AnswerToSecurityQuestion(String answer) {
        if (forgotUser.isSecurityAnswerNotCorrect(answer)) return "answer is not correct!";

        step = 0;
        controller.getGame().setCurrentUser(forgotUser);
        return "logged in successfully\n" + controller.enterMainMenu();
    }
}