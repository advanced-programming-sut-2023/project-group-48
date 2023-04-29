package model.Buildings;

import model.Match.Direction;
import model.Match.Governance;
import model.User;

public class Inn extends Building {
    private final int popularityChange;
    private final int wineUsage;
    private final int rate;

    public Inn(Governance governance, int column, int row, String type, BuildingType buildingType, Direction direction, int popularityChange, int wineUsage, int rate) {
        super(governance, column, row, type, buildingType, direction);
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
