package model.Match;

import model.Buildings.*;
import model.People.People;
import model.People.PeopleType;
import model.People.Troop;
import model.User;

import java.util.*;

public class Governance {
    private final User owner;
    private int popularity;
    private final HashMap<PopularityFactor, Integer> popularityFactors;
    private int population;
    private int unemployedPopulation;
    private int maxPopulation;
    private int foodRate;
    private int taxRate;
    private int fearRate;
    private final ArrayList<Building> buildings;
    private final HashMap<Property, Integer> properties;
    private ArrayList<Request> sentRequests;
    private ArrayList<Request> receivedRequests;
    private ArrayList<Request> requestsHistory;
    public boolean isSultanAlive = true;

    {
        properties = new HashMap<>();
        for (Property allProperty : Property.getAllProperties()) {
            properties.put(allProperty, 0);
        }
        properties.put(Property.WOOD, 1000);
        properties.put(Property.COIN, 1000);
        properties.put(Property.STONE, 1000);
        popularityFactors = new HashMap<>();
        for (PopularityFactor allPopularityFactor : PopularityFactor.getPopularityFactors()) {
            popularityFactors.put(allPopularityFactor, 0);
        }
    }

    public Governance(User owner) {
        this.owner = owner;
        this.buildings = new ArrayList<>();
        this.population = 20;
        this.unemployedPopulation = 20;
        this.maxPopulation = 40;
        this.sentRequests = new ArrayList<>();
        this.receivedRequests = new ArrayList<>();
        this.requestsHistory = new ArrayList<>();
    }

    public User getOwner() {
        return owner;
    }

    public int getPopularity() {
        updatePopularity();
        return popularity;
    }
    public int getPopularityFactor(PopularityFactor popularityFactor) {
        updatePopularity();
        return popularityFactors.get(popularityFactor);
    }
    public void updatePopulation() {
        population += getPopularity() * 5;
        if(population < 0) {
            population = 0;
        }
        if(population > maxPopulation) {
            population = maxPopulation;
        }
    }
    public void updatePopularity() {
        this.popularity = 0;
        changeFoodPopularity();
        changeTaxPopularity();
        changeReligionPopularity();
        changeFearPopularity();
        for (PopularityFactor factor : PopularityFactor.getPopularityFactors()) {
            this.popularity += popularityFactors.get(factor);
        }
//        System.out.println("Popularity: " + population);
    }

    public void reducePopularityBySickness() {
        popularity--;
    }

    public void changeFoodPopularity() {
        int finalFoodFactor = 0;

        int foodVariety = 0;
        for (Property allProperty : Property.getAllFoods()) {
            if (properties.get(allProperty) > 0) {
                foodVariety += 1;
            }
        }

        switch (foodRate) {
            case -2:
                finalFoodFactor += -8;
                break;
            case -1:
                finalFoodFactor += -4;
                break;
            case 0:
                finalFoodFactor += 0;
                break;
            case 1:
                finalFoodFactor += +4;
                break;
            case 2:
                finalFoodFactor += +8;
                break;
            default:
                break;
        }
        switch (foodVariety) {
            case 1:
                finalFoodFactor += 0;
                break;
            case 2:
                finalFoodFactor += 1;
                break;
            case 3:
                finalFoodFactor += 2;
                break;
            case 4:
                finalFoodFactor += 3;
                break;
            default:
                break;
        }
        if (foodVariety == 0) {
            finalFoodFactor = 0;
        }
        popularityFactors.put(PopularityFactor.FOOD, finalFoodFactor);
    }

    public void changeTaxPopularity() {
        int finalTaxFactor = 0;
        switch (taxRate) {
            case -3:
                finalTaxFactor += 7;
                break;
            case -2:
                finalTaxFactor += 5;
                break;
            case -1:
                finalTaxFactor += 3;
                break;
            case 0:
                finalTaxFactor += 0;
                break;
            case 1:
                finalTaxFactor += -2;
                break;
            case 2:
                finalTaxFactor += -4;
                break;
            case 3:
                finalTaxFactor += -6;
                break;
            case 4:
                finalTaxFactor += -8;
                break;
            case 5:
                finalTaxFactor += -12;
                break;
            case 6:
                finalTaxFactor += -16;
                break;
            case 7:
                finalTaxFactor += -20;
                break;
            case 8:
                finalTaxFactor += -24;
                break;
        }
        popularityFactors.put(PopularityFactor.TAX, finalTaxFactor);
    }

    public void changeReligionPopularity() {
        int finalReligionFactor = 0;
        double doubleReligionFactor = 0;
        int percentReligionFactor = 0;
        for (Building building : buildings) {
            if (building.getType().equals("Church")) {
                doubleReligionFactor += 1;
            } else if (building.getType().equals("Cathedral")) {
                doubleReligionFactor += 2;
            }
        }
        percentReligionFactor = (int) Math.floor((doubleReligionFactor / ((double) population)) * 2500);
        if (0 <= percentReligionFactor && percentReligionFactor < 25) {
            finalReligionFactor += 2;
        }
        if (25 <= percentReligionFactor && percentReligionFactor < 50) {
            finalReligionFactor += 4;
        }
        if (50 <= percentReligionFactor && percentReligionFactor < 75) {
            finalReligionFactor += 6;
        }
        if (75 <= percentReligionFactor) {
            finalReligionFactor += 8;
        }
        popularityFactors.put(PopularityFactor.RELIGION, finalReligionFactor);
    }

    public void changeFearPopularity() {
        int finalFearFactor = 0;
        double doubleFearFactor = 0;
        int percentFearFactor = 0;

        for (Building building : buildings) {
            if (building.getType().equals("Good Things")) {
                doubleFearFactor += 1;
            } else if (building.getType().equals("Bad Things")) {
                doubleFearFactor -= 1;
            } else if (building.getType().equals("Inn")) {
                doubleFearFactor += 2;
            }
        }
        percentFearFactor = (int) Math.floor((doubleFearFactor / ((double) population)) * 1250);

        if (-75 >= percentFearFactor) {
            finalFearFactor += -4;
        }
        if (-50 >= percentFearFactor && percentFearFactor > -75) {
            finalFearFactor += -3;
        }
        if (-25 >= percentFearFactor && percentFearFactor > -50) {
            finalFearFactor += -2;
        }
        if (-0 >= percentFearFactor && percentFearFactor > -25) {
            finalFearFactor += -1;
        }
        if (0 <= percentFearFactor && percentFearFactor < 25) {
            finalFearFactor += 1;
        }
        if (25 <= percentFearFactor && percentFearFactor < 50) {
            finalFearFactor += 2;
        }
        if (50 <= percentFearFactor && percentFearFactor < 75) {
            finalFearFactor += 3;
        }
        if (75 <= percentFearFactor) {
            finalFearFactor += 4;
        }
        popularityFactors.put(PopularityFactor.FEAR, finalFearFactor);
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
        buildings.add(building);
        if (building instanceof IndustrialCenter) {
            People.createPeopleByType(this, building.getRow(), building.getColumn(),IndustrialCenter.getWorkerType(building.getType()), PeopleType.WORKER);
            unemployedPopulation--;
        } else if (building.getType().equals("Hovel")) {
            maxPopulation += 8;
        }
    }

    public void removeBuilding(Building building) {
        buildings.remove(building);
    }


    public ArrayList<Building> getBuildings() {
        return buildings;
    }

    public void addProperty(Property property, int count) {
        properties.put(property, properties.get(property) + count);
        if (!property.equals(Property.COIN)) loadStorages(property, count);
    }

    public void reduceProperty(Property property, int count) {
        properties.put(property, properties.get(property) - count);
        if (!property.equals(Property.COIN)) unloadStorages(property, count);
    }

    public void addRequest(Request request) {
        if (request.getReceiver().equals(this)) receivedRequests.add(request);
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

    public boolean areResourcesEnoughToBuild(String type) {
        for (Map.Entry<Property, Integer> entry : BuildingType.getMaterialsNeedToBuild(type).entrySet()) {
            if (properties.get(entry.getKey()) < entry.getValue()) return false;
        }
        return true;
    }

    public boolean areResourcesEnoughToRepair(String type) {
        for (Map.Entry<Property, Integer> entry : BuildingType.getMaterialsNeedToBuild(type).entrySet()) {
            if (properties.get(entry.getKey()) < entry.getValue() / 2) return false;
        }
        return true;
    }

    public void payForBuilding(String type) {
        for (Map.Entry<Property, Integer> entry : BuildingType.getMaterialsNeedToBuild(type).entrySet()) {
            properties.put(entry.getKey(), properties.get(entry.getKey()) - entry.getValue());
        }
    }

    public void payForRepair(String type) {
        for (Map.Entry<Property, Integer> entry : BuildingType.getMaterialsNeedToBuild(type).entrySet()) {
            properties.put(entry.getKey(), properties.get(entry.getKey()) - entry.getValue() / 2);
        }
    }

    public boolean areResourcesEnoughToCreateUnit(String type, int count) {
        for (Property property : Troop.getRequiredResource(type)) {
            if (properties.get(property) == 0) return false;
        }
        if(Troop.getTroopCost(type)>properties.get(Property.COIN)) return false;
        return true;
    }

    public boolean isPopularityEnoughToCreateUnit(String type) {
        return unemployedPopulation > 0;
    }

    public void payForCreatingUnit(String type, int count) {
        for (Property property : Troop.getRequiredResource(type)) {
            properties.put(property, properties.get(property) - 1);
        }
        properties.put(Property.COIN, properties.get(Property.COIN)- Troop.getTroopCost(type));
    }

    public int getPropertyCount(Property property) {
        return properties.get(property);
    }

    public boolean canStore(Property property) {
        Storage storage = getFreeStorageToStore(property);
        return storage != null;
    }

    public Storage getFreeStorageToStore(Property property) {
        for (Building building : buildings) {
            if (building instanceof Storage) {
                Storage storage = (Storage) building;
                if (storage.canStoreProperty(property) && (storage.getTotalAmount() < storage.getCapacity()))
                    return storage;
            }
        }
        return null;
    }

    private Storage getStorageWithProperty(Property property) {
        for (Building building : buildings) {
            if (building instanceof Storage) {
                Storage storage = (Storage) building;
                if (storage.canStoreProperty(property) && storage.getPropertyCount(property) > 0) return storage;
            }
        }
        return null;
    }

    public void loadStorages(Property property, int amount) {
        while (amount >= 0) {
            Storage storage = getFreeStorageToStore(property);
            if (storage == null) break;
            int storageCapability = storage.getCapacity() - storage.getTotalAmount();
            storage.addProperty(property, Math.min(amount, storageCapability));
            amount -= storageCapability;
        }
    }

    public void unloadStorages(Property property, int amount) {
        while (amount >= 0) {
            Storage storage = getStorageWithProperty(property);
            if (storage == null) break;
            int storageAmount = storage.getPropertyCount(property);
            storage.removeProperty(property, Math.min(amount, storageAmount));
            amount -= storageAmount;
        }
    }
}