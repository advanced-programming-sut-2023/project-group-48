package model.Buildings;

import model.Match.Direction;
import model.Match.Governance;
import model.Match.Property;
import model.User;

import java.util.HashMap;

public class Storage extends Building {
    private static final HashMap<String, Integer> storagesCapacities = new HashMap<>() {{
        put("Armoury", 1000);
        put("Stockpile", 1000);
        put("Granary", 1000);
    }};
    private final HashMap<Property, Integer> properties;
    private final int capacity;

    public Storage(Governance governance, int row, int column, String type, BuildingType buildingType, Direction direction) {
        super(governance, row, column, type, buildingType, direction);
        this.properties = new HashMap<>();
        this.capacity = storagesCapacities.get(type);
    }

    public void addProperty(Property property, int count) {
        properties.put(property, properties.getOrDefault(property, 0) + count);
    }

    public void removeProperty(Property property, int count) {
        properties.put(property, properties.getOrDefault(property, 0) - count);
    }

    public int getPropertyCount(Property property) {
        return properties.getOrDefault(property, 0);
    }

    public int getCapacity() {
        return capacity;
    }
}
