package controller;

public class MainMenuController {
    private final Controller controller;

    public MainMenuController(Controller controller) {
        this.controller = controller;
    }

    public String startMatch(String... playersUsernames) {
    }

    public String logout() {
        return controller.logout();
    }
}
