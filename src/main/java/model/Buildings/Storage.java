package model.Buildings;

import model.Match.Direction;
import model.Match.Governance;
import model.Match.Property;
import model.User;

import java.util.HashMap;

public class Storage extends Building {
    private final HashMap<Property, Integer> properties;
    private final int capacity;

    public Storage(Governance governance, int row, int column, String type, BuildingType buildingType, Direction direction, HashMap<Property, Integer> properties, int capacity) {
        super(governance, row, column, type, buildingType, direction);
        this.properties = new HashMap<Property, Integer>;
        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }
}
