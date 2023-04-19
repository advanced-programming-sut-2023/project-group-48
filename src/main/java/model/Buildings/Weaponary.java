package model.Buildings;

import model.User;

import java.util.ArrayList;

public class Weaponary extends Building {
    private ArrayList<Element> elements;
    private final int rate;

    public Weaponary(User owner, int column, int row, String type, int rate) {
        super(owner, column, row, type);
        this.rate = rate;
    }

    public int getRate() {
        return rate;
    }
}
