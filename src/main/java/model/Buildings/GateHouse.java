package model.Buildings;

public class GateHouse {
    private boolean isDoorOpen;
    private boolean isCaptured = false;

    public GateHouse() {
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