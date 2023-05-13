package model.Buildings;

import model.Match.Direction;
import model.Match.Governance;
import model.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Tower extends Building {
    private static final ArrayList<String> towerRelated = new ArrayList<>(Arrays.asList("Tall Wall", "Short Wall", "Stair", "Lookout Tower", "Perimeter Tower", "Defence Turret", "Square Tower", "Round Tower"));
    private static final HashMap<String, Integer[]> towersStats = new HashMap<>() {{
        // fireRange-defendRange-capacity
        put("Lookout Tower", new Integer[]{1, 1, 1});
        put("Perimeter Tower", new Integer[]{1, 1, 1});
        put("Defence Turret", new Integer[]{1, 1, 1});
        put("Square Tower", new Integer[]{1, 1, 1});
        put("Round Tower", new Integer[]{1, 1, 1});
    }};
    private final int fireRange;
    private final int defendRange;
    private final int capacity;

    public Tower(Governance governance, int row, int column, String type, BuildingType buildingType, Direction direction) {
        super(governance, row, column, type, buildingType, direction);
        this.fireRange = towersStats.get(type)[0];
        this.defendRange = towersStats.get(type)[1];
        this.capacity = towersStats.get(type)[2];
    }

    public static boolean isTowerRelated(String type) {
        return towerRelated.contains(type);
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
