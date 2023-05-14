package model.Match;

import java.util.HashMap;

public class Shop {
    private HashMap<Property, Integer> price;
    {
        price = new HashMap<>();
        price.put(Property.MEAT, 10);
        price.put(Property.CHEESE, 10);
        price.put(Property.APPLE, 10);
        price.put(Property.HOPS, 10);
        price.put(Property.WOOD, 10);
        price.put(Property.STONE, 10);
        price.put(Property.SPEAR, 10);
        price.put(Property.BOW, 10);
        price.put(Property.MACE, 10);
        price.put(Property.LEATHERARMOUR, 10);
        price.put(Property.BEER, 10);
        price.put(Property.BARLEY, 10);
        price.put(Property.BREAD, 10);
        price.put(Property.IRON, 10);
        price.put(Property.PITCH, 10);
        price.put(Property.CROSSBOW, 10);
        price.put(Property.PIKE, 10);
        price.put(Property.SWORD, 10);
        price.put(Property.ARMOUR, 10);
    }




    public int getPropertyPrice(Property property) {
        return price.get(property);
    }
    public int getPropertyCost(Property property) {
        return price.get(property)/2;
    }
    public void sellToGovernance(Governance governance, Property property, int amount){
        governance.addProperty(property, amount);
        governance.addProperty(Property.COIN,-getPropertyPrice(property)*amount);

    }
    public void buyFromGovernance(Governance governance, Property property, int amount){
        governance.addProperty(property, -amount);
        governance.addProperty(Property.COIN,getPropertyCost(property)*amount);

    }
}
