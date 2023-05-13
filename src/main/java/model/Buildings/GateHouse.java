package model.Buildings;

import model.Match.Direction;
import model.Match.Governance;

public class GateHouse extends Building{
    private boolean isDoorOpen;
    private boolean isCaptured = false;

    public GateHouse(Governance governance, int row, int column, String type, BuildingType buildingType, Direction direction) {
        super(governance, row, column, type, buildingType, direction);
        this.isDoorOpen = true;
        this.isCaptured = false;
    }

    public boolean isDoorOpen() {
        return isDoorOpen;
    }

    public boolean isCaptured() {
        return isCaptured;
    }

    public void switchDoorSituation() {
        isDoorOpen = !isDoorOpen;
    }

    public void captureGateHouse() {
        isCaptured = true;
    }
}