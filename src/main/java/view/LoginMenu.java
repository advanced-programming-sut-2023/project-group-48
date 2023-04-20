package view;

import controller.Controller;
import controller.LoginMenuController;

public class LoginMenu extends Menu {
    private final LoginMenuController loginMenuController;
    public LoginMenu(Controller controller) {
        super(controller);
        this.loginMenuController = new LoginMenuController(controller);
    }

    private enum Commands {}

    @Override
    public void run() {
    }
}