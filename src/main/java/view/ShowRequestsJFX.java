package view;

import controller.Controller;
import controller.TradeMenuController;
import javafx.application.Application;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ShowRequestsJFX extends Application implements MenuJFX{

    private AnchorPane showRequestsPane;
    private Controller controller;
    private TradeMenuController tradeMenuController;
    private Stage stage;

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
    }

    @Override
    public void adjust(double ratioX, double ratioY) {

    }

    @Override
    public void adjustWithScene() {

    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public TradeMenuController getTradeMenuController() {
        return tradeMenuController;
    }

    public void setTradeMenuController(TradeMenuController tradeMenuController) {
        this.tradeMenuController = tradeMenuController;
    }
}
