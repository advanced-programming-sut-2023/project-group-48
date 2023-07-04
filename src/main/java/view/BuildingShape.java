package view;

import javafx.scene.shape.Rectangle;
import model.Buildings.Building;

public class BuildingShape extends Rectangle {
    private final Building building;
    public BuildingShape(double width, double height, Building building) {
        super(width, height);
        this.building = building;
    }

    public Building getBuilding() {
        return building;
    }
}
