package model.Match;

import java.util.ArrayList;
import java.util.HashMap;

public class Shop {
    private final HashMap<Property, Integer> prices;
    private final ArrayList<String> products;
    {
        products = new ArrayList<>();
        products.add("meat");
        products.add("cheese");
        products.add("apple");
        products.add("hops");
        products.add("wood");
        products.add("stone");
        products.add("spear");
        products.add("bow");
        products.add("mace");
        products.add("leatherarmour");
        products.add("beer");
        products.add("barley");
        products.add("bread");
        products.add("iron");
        products.add("pitch");
        products.add("crossbow");
        products.add("pike");
        products.add("sword");
        products.add("armour");
        prices = new HashMap<>();
        prices.put(Property.MEAT, 10);
        prices.put(Property.CHEESE, 10);
        prices.put(Property.APPLE, 10);
        prices.put(Property.HOPS, 10);
        prices.put(Property.WOOD, 10);
        prices.put(Property.STONE, 10);
        prices.put(Property.SPEAR, 10);
        prices.put(Property.BOW, 10);
        prices.put(Property.MACE, 10);
        prices.put(Property.LEATHERARMOUR, 10);
        prices.put(Property.BEER, 10);
        prices.put(Property.BARLEY, 10);
        prices.put(Property.BREAD, 10);
        prices.put(Property.IRON, 10);
        prices.put(Property.PITCH, 10);
        prices.put(Property.CROSSBOW, 10);
        prices.put(Property.PIKE, 10);
        prices.put(Property.SWORD, 10);
        prices.put(Property.ARMOUR, 10);
    }

    public HashMap<Property, Integer> getPrices() {
        return prices;
    }

    public ArrayList<String> getProducts() {
        return products;
    }

    public void sellToGovernance(Governance governance, Property property, int amount) {
        governance.addProperty(property, amount);
        governance.reduceProperty(Property.COIN, prices.get(property) * amount);
    }

    public void buyFromGovernance(Governance governance, Property property, int amount) {
        governance.reduceProperty(property, amount);
        governance.addProperty(Property.COIN, prices.get(property) / 2 * amount);
    }
}
