package model.Buildings;

import model.User;

public class Tower extends Building {
    private final int popularityChange;

    public Tower(User owner, int column, int row, String type, int popularityChange) {
        super(owner, column, row, type);
        this.popularityChange = popularityChange;
    }

    public int getPopularityChange() {
        return popularityChange;
    }
}
