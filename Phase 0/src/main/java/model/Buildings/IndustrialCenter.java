package model.Buildings;

import model.User;

public class IndustrialCenter extends Building {
    private final int rate;

    public IndustrialCenter(User owner, int column, int row, String type, int rate) {
        super(owner, column, row, type);
        this.rate = rate;
    }

    public int getRate() {
        return rate;
    }
}
