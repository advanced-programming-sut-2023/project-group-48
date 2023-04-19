package model.Match;

import java.util.HashMap;

public class Shop {
    private HashMap<Resource, Integer> resourceAmount;
    private HashMap<Resource, Integer> price;
    private HashMap<Resource, Integer> cost;

    public Shop() {
    }

    public void addResource(Resource resource, int amount, int cost, int price) {}

    public void removeResource(Resource resource, int amount) {}

    public HashMap<Resource, Integer> getResourceAmount() {
        return resourceAmount;
    }

    public HashMap<Resource, Integer> getPrice() {
        return price;
    }

    public HashMap<Resource, Integer> getCost() {
        return cost;
    }
}
