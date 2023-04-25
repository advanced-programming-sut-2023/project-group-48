package view;

import controller.Controller;
import controller.ShopMenuController;

public class ShopMenu extends Menu {
    private final ShopMenuController shopMenuController;
    public ShopMenu(Controller controller) {
        super(controller);
        this.shopMenuController = new ShopMenuController(controller);
    }

    //private enum Commands {}

    @Override
    public void run() {

    }
}
