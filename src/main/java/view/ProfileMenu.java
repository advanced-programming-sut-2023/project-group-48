package view;

import controller.Controller;
import controller.ProfileMenuController;

public class ProfileMenu extends Menu {
    private final ProfileMenuController profileMenuController;
    public ProfileMenu(Controller controller) {
        super(controller);
        this.profileMenuController = new ProfileMenuController(controller);
    }

    private enum Commands {}

    @Override
    public void run() {
    }
}