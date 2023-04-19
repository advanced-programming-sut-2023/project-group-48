package model.Buildings;

import model.User;

public class Storage extends Building {
    private final int capacity;

    public Storage(User owner, int column, int row, String type, int capacity) {
        super(owner, column, row, type);
        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }
}
