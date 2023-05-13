package model.Buildings;

import model.Match.Direction;
import model.Match.Governance;
import model.User;

public class Inn extends Building {
    private final int popularityChange;
    private final int wineUsage;
    private final int rate;

    public Inn(Governance governance, int row, int column, String type, BuildingType buildingType, Direction direction) {
        super(governance, row, column, type, buildingType, direction);
        this.popularityChange = 0; // TODO
        this.wineUsage = 0; // TODO
        this.rate = 0; // TODO
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
