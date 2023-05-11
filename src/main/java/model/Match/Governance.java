package model.Match;

import model.Buildings.Building;
import model.User;

import java.util.ArrayList;
import java.util.HashMap;

public class Governance {
    private final User owner;
    private int popularity;
    private HashMap<PopularityFactor, Integer> popularityFactors;
    private int population;
    private int maxPopulation;
    private int coinCount;
    private int woodCount;
    private int goldCount;
    private int ironCount;
    private int stoneCount;
    private HashMap<Food, Integer> foods;
    private int foodRate;
    private int taxRate;

    private ArrayList<Building> buildings;

    private HashMap<Property, Integer> properties;
    private ArrayList<Request> sentRequests;
    private ArrayList<Request> receivedRequests;
    private ArrayList<Request> requestsHistory;

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

    public int getCoinCount() {
        return coinCount;
    }

    public void setCoinCount(int coinCount) {
        this.coinCount = coinCount;
    }

    public int getWoodCount() {
        return woodCount;
    }

    public void setWoodCount(int woodCount) {
        this.woodCount = woodCount;
    }

    public int getGoldCount() {
        return goldCount;
    }

    public void setGoldCount(int goldCount) {
        this.goldCount = goldCount;
    }

    public int getIronCount() {
        return ironCount;
    }

    public void setIronCount(int ironCount) {
        this.ironCount = ironCount;
    }

    public int getStoneCount() {
        return stoneCount;
    }

    public void setStoneCount(int stoneCount) {
        this.stoneCount = stoneCount;
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

    public void addBuilding(Building building) {
    }

    public void removeBuilding(Building building) {
    }

    public void addProperty(Property property, int count) {
    }

    public void reduceProperty(Property property, int count) {
    }

    public boolean areResourcesEnoughToRepair(Building building) {
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

    public void trade(Governance receiver, Resource resource, int count) {

    }

}