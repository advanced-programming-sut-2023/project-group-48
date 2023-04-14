package model;

import java.util.HashMap;

public class Governance {
    public static enum Factor {}
    public static enum Food {}
    private int popularity;
    private HashMap<Factor, Integer> popularityFactors;
    private int population;
    private int coinCount;
    private HashMap<Food, Integer> foods;
    private int foodRate;
    private int taxRate;

    public Governance() {
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public int getPopularity() {
        return popularity;
    }

    public void changePopularityByFactor(Factor factor) {}

    public int getCoinCount() {
        return coinCount;
    }

    public void setCoinCount(int coinCount) {
        this.coinCount = coinCount;
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
}