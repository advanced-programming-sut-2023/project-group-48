package view;

import controller.Controller;
import controller.SignUpMenuController;

public class SignUpMenu extends Menu {
    private final SignUpMenuController signUpMenuController;
    public SignUpMenu(Controller controller) {
        super(controller);
        this.signUpMenuController = new SignUpMenuController(controller);
    }

    //private enum Commands {}

    @Override
    public void run() {
    }
}
