package view;

import controller.Controller;
import controller.MatchMenuController;

public class MatchMenu extends Menu {
    private final MatchMenuController matchMenuController;
    public MatchMenu(Controller controller) {
        super(controller);
        this.matchMenuController = new MatchMenuController(controller);
    }

    //private enum Commands {}

    @Override
    public void run() {
    }
}
