package controller;

import model.Match.Property;
import model.Match.Resource;
import model.User;

public class TradeMenuController {
    private final Controller controller;

    public TradeMenuController(Controller controller) {
        this.controller = controller;
    }


    public String addTrade(String receiverUsername, String propertyType, int amount, int price, String message) {
        Property property = Property.getProperty(propertyType);
        User receiver = controller.getGame().getUserByUsername(receiverUsername);
        if (receiver == null) return "User not found";
        if (property == null) return "Invalid property";
        if (amount <= 0) return "Invalid resource amount";
        if (price <= 0) return "Invalid price";

        controller.getGame().getCurrentMatch().sendTradeRequest(controller.getGame().getCurrentMatch().getCurrentPlayer(), receiver, property, amount, price, message);
        return "Trade request sent successfully!";
    }

    public String tradeList() {
        String result = "your trades:\n";
    }

    public String tradeAccept(String id, String message) {

    }

    public String tradeHistory() {

    }
}