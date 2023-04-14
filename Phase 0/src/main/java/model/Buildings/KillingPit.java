package model.Buildings;

import model.User;

public class KillingPit extends Building {
    private final int damage;

    public KillingPit(User owner, int column, int row, String type, int damage) {
        super(owner, column, row, type);
        this.damage = damage;
    }

    public int getDamage() {
        return damage;
    }
}
