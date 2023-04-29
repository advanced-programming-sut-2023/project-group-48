package model.Buildings;

import model.Match.Property;
import model.User;

import java.util.HashMap;

public class Storage extends Building {
    private final HashMap<Property, Integer> properties;
    private final int capacity;

    public Storage(User owner, int column, int row, String type, int capacity) {
        super(owner, column, row, type);
        properties = new HashMap<Property, Integer>();
        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }
}
