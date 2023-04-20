package view;

import controller.Controller;
import controller.MainMenuController;

public class MainMenu extends Menu {
    private final MainMenuController mainMenuController;
    public MainMenu(Controller controller) {
        super(controller);
        this.mainMenuController = new MainMenuController(controller);
    }

    private enum Commands {}

    @Override
    public void run() {
    }
}
