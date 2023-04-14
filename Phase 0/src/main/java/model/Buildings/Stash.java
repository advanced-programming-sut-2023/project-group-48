package model.Buildings;

import model.User;

public class Stash extends Building {
    private final int capacity;

    public Stash(User owner, int column, int row, String type, int capacity) {
        super(owner, column, row, type);
        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }
}
