package model.Match;

import model.Buildings.Building;
import model.Buildings.BuildingType;
import model.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Properties;

public class Governance {
    private final User owner;
    private int popularity;
    private HashMap<PopularityFactor, Integer> popularityFactors;
    private int population;
    private int maxPopulation;
    private int foodRate;
    private int foodVariety;
    private int taxRate;
    private int fearRate;
    private ArrayList<Building> buildings;
    private HashMap<Property, Integer> properties;
    private ArrayList<Request> sentRequests;
    private ArrayList<Request> receivedRequests;
    private ArrayList<Request> requestsHistory;

    {
        properties = new HashMap<>();
        for (Property allProperty : Property.getAllProperties()) {
            properties.put(allProperty, 0);
        }
        popularityFactors = new HashMap<>();
        for (PopularityFactor allPopularityFactor : PopularityFactor.getPopularityFactors()) {
            popularityFactors.put(allPopularityFactor, 0);
        }
    }

    public Governance(User owner) {
        this.owner = owner;
    }

    public User getOwner() {
        return owner;
    }

    public int getPopularity() {
        return popularity;
    }

    public void updatePopularity() {
        for (PopularityFactor allPopularityFactor : PopularityFactor.getPopularityFactors()) {
            this.popularity += popularityFactors.get(allPopularityFactor);
        }
    }
    public void changePopularityByFactor() {
        int finalFoodFactor=0;

        foodVariety=0;
        for (Property allProperty : Property.getAllFoods()) {
            if(properties.get(allProperty) > 0){
                foodVariety+=1;
            }
        }

        switch (foodRate){
            case -2:
                finalFoodFactor+=-8;
                break;
            case -1:
                finalFoodFactor+=-4;
                break;
            case 0:
                finalFoodFactor+=0;
                break;
            case 1:
                finalFoodFactor+=+4;
                break;
            case 2:
                finalFoodFactor+=+8;
                break;
            default:
                break;
        }
        switch (foodVariety){
            case 1:
                finalFoodFactor+=0;
                break;
            case 2:
                finalFoodFactor+=1;
                break;
            case 3:
                finalFoodFactor+=2;
                break;
            case 4:
                finalFoodFactor+=3;
                break;
            default:
                break;
        }
        if(foodVariety==0) {
            finalFoodFactor = 0;
        }
        popularityFactors.put(PopularityFactor.FOOD,finalFoodFactor);
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public int getMaxPopulation() {
        return maxPopulation;
    }

    public void setMaxPopulation(int maxPopulation) {
        this.maxPopulation = maxPopulation;
    }

    public void addFood(Property food, int count) {
        properties.put(food, properties.get(food) + count);
    }

    public void reduceFood(Property food, int count) {
        properties.put(food, properties.get(food) - count);
    }

    public HashMap<Property, Integer> getFoods() {
        HashMap<Property, Integer> foods = new HashMap<>();
        for (Property allFood : Property.getAllFoods()) {
            foods.put(allFood, properties.get(allFood));
        }
        return foods;
    }

    public int getFoodRate() {
        return foodRate;
    }

    public void setFoodRate(int foodRate) {
        this.foodRate = foodRate;
    }

    public int getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(int taxRate) {
        this.taxRate = taxRate;
    }

    public int getFearRate() {
        return fearRate;
    }

    public void setFearRate(int fearRate) {
        this.fearRate = fearRate;
    }

    public void addBuilding(Building building) {
    }

    public void removeBuilding(Building building) {
    }

    public void addProperty(Property property, int count) {
    }

    public void reduceProperty(Property property, int count) {
    }

    public boolean areResourcesEnoughToBuild(String type) {
    }

    public boolean areResourcesEnoughToRepair(Building building) {
    }

    public boolean areResourcesEnoughToCreateUnit(String type, int count) {
    }

    public boolean isPopularityEnoughToCreateUnit(String type) {
    }

    public void addRequest(Request request) {
        if (request.getReceiver().equals(owner.getGovernance())) receivedRequests.add(request);
        else sentRequests.add(request);
    }

    public ArrayList<Request> getSentRequests() {
        return sentRequests;
    }

    public ArrayList<Request> getReceivedRequests() {
        return receivedRequests;
    }

    public void answerRequest(Request request, boolean accepted) {
        request.setAccepted(accepted);
        requestsHistory.add(request);
    }

    public ArrayList<Request> getRequestsHistory() {
        return requestsHistory;
    }

    public Request getRequestById(ArrayList<Request> requests, int id) {
        for (Request request : requests) {
            if (request.getId() == id) return request;
        }
        return null;
    }

    public void trade(Governance receiver, Resource resource, int count) {

    }

    public void payForBuilding(String type) {
    }

    public void payForCreatingUnit(String type, int count) {
    }

    public int getPropertyCount(Property property) {
        return properties.get(property);
    }
}