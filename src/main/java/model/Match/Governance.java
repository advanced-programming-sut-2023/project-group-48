package model.Match;

import model.Buildings.Building;
import model.Buildings.BuildingType;
import model.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Governance {
    private final User owner;
    private int popularity;
    private HashMap<PopularityFactor, Integer> popularityFactors;
    private int population;
    private int maxPopulation;
    private HashMap<Food, Integer> foods; // TODO: set all foods 0
    private int foodRate;
    private int taxRate;
    private int fearRate;
    private ArrayList<Building> buildings;
    private HashMap<Property, Integer> properties;
    private ArrayList<Request> sentRequests;
    private ArrayList<Request> receivedRequests;
    private ArrayList<Request> requestsHistory;

    {

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

    public void changePopularityByFactor(PopularityFactor factor) {
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

    public void addFood(Food food, int count) {
        foods.put(food, foods.get(food) + count);
    }

    public void reduceFood(Food food, int count) {
        foods.put(food, foods.get(food) - count);
    }

    public HashMap<Food, Integer> getFoods() {
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