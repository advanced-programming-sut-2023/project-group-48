package view;

import controller.Controller;
import controller.MapMenuController;

public class MapMenu extends Menu {
    private final MapMenuController mapMenuController;
    public MapMenu(Controller controller) {
        super(controller);
        this.mapMenuController = new MapMenuController(controller);
    }

    private enum Commands {}

    @Override
    public void run() {
    }
}
