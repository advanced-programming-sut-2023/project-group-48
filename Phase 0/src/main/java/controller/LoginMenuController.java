package controller;

public class LoginMenuController {
    private final Controller controller;
    private int wrongPasswordCount;

    public LoginMenuController(Controller controller) {
        this.controller = controller;
        wrongPasswordCount = 0;
    }

    public String login(String username, String password, boolean stayLoggedIn) {
    }

    public String forgotPassword(String username) {
    }

    public String AnswerToSecurityQuestion(String answer) {
    }
}