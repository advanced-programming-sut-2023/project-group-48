package model.Buildings;

import model.User;

public class Castle extends Building {
    private int fireRange;
    private int defendRange;

    public Castle(User owner, int column, int row, String type, int fireRange, int defendRange) {
        super(owner, column, row, type);
        this.fireRange = fireRange;
        this.defendRange = defendRange;
    }

    public int getFireRange() {
        return fireRange;
    }

    public int getDefendRange() {
        return defendRange;
    }
}
