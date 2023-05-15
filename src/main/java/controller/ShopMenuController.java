package controller;

import model.Match.Property;

import java.util.Map;

public class ShopMenuController {
    private final Controller controller;

    public ShopMenuController(Controller controller) {
        this.controller = controller;
    }

    public String showPriceList() {
        StringBuilder result = new StringBuilder("Properties list:\n");
        for (Map.Entry<Property, Integer> entry : controller.getGame().getCurrentMatch().getShop().getPrices().entrySet()) {
            result.append(entry.getKey().getPropertyInString()).append(": ").append(entry.getValue()).append("\n");
        }
        return result.toString();
    }

    public String buy(String name, int amount) {
        if (amount <= 0) return "Invalid amount!";
        Property property = Property.getProperty(name);
        if (!controller.getGame().getCurrentMatch().getShop().getPrices().containsKey(property))
            return "Invalid property!";
        if (controller.getGame().getCurrentMatch().getCurrentPlayer().getGovernance().getPropertyCount(Property.COIN) <
                controller.getGame().getCurrentMatch().getShop().getPrices().get(property) * amount)
            return "You don't have enough coin!";
        if (!controller.getGame().getCurrentMatch().getCurrentPlayer().getGovernance().canStore(property))
            return "You don't have enough space in your storages!";

        controller.getGame().getCurrentMatch().getShop().sellToGovernance(controller.getGame().getCurrentMatch().getCurrentPlayer().getGovernance(), property, amount);
        controller.getGame().getCurrentMatch().getCurrentPlayer().getGovernance().loadStorages(property, amount);
        return "You bought " + amount + " " + name + "!";
    }

    public String sell(String name, int amount) {
        if (amount <= 0) return "Invalid amount!";
        Property property = Property.getProperty(name);
        if (!controller.getGame().getCurrentMatch().getShop().getPrices().containsKey(property))
            return "Invalid property!";
        if (controller.getGame().getCurrentMatch().getCurrentPlayer().getGovernance().getPropertyCount(property) < amount)
            return "You don't have enough " + name + "!";

        controller.getGame().getCurrentMatch().getShop().buyFromGovernance(controller.getGame().getCurrentMatch().getCurrentPlayer().getGovernance(), property, amount);
        controller.getGame().getCurrentMatch().getCurrentPlayer().getGovernance().unloadStorages(property, amount);
        return "You sold " + amount + " " + name + "!";
    }
}