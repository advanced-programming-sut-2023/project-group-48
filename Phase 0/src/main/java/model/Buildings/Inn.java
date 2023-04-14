package model.Buildings;

import model.User;

public class Inn extends Building {
    private final int popularityChange;
    private final int wineUsage;
    private final int rate;

    public Inn(User owner, int column, int row, String type, int popularityChange, int wineUsage, int rate) {
        super(owner, column, row, type);
        this.popularityChange = popularityChange;
        this.wineUsage = wineUsage;
        this.rate = rate;
    }

    public int getPopularityChange() {
        return popularityChange;
    }

    public int getWineUsage() {
        return wineUsage;
    }

    public int getRate() {
        return rate;
    }
}
