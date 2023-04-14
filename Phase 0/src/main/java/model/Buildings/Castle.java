package model.Buildings;

import model.User;

public class Castle extends Building {
    private final int fireRange;
    private final int defendRange;
    private final int capacity;

    public Castle(User owner, int column, int row, String type, int fireRange, int defendRange, int capacity) {
        super(owner, column, row, type);
        this.fireRange = fireRange;
        this.defendRange = defendRange;
        this.capacity = capacity;
    }

    public int getFireRange() {
        return fireRange;
    }

    public int getDefendRange() {
        return defendRange;
    }

    public int getCapacity() {
        return capacity;
    }
}
