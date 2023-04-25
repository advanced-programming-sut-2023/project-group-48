package view;

import controller.Controller;
import controller.TradeMenuController;

public class TradeMenu extends Menu {
    private final TradeMenuController tradeMenuController;
    public TradeMenu(Controller controller) {
        super(controller);
        this.tradeMenuController = new TradeMenuController(controller);
    }

    //private enum Commands {}

    @Override
    public void run() {
    }
}
