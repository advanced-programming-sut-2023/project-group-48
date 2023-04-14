package controller;

public class MapMenuController {
    private final Controller controller;

    enum Direction {
    }

    int currentColumn;
    int currentRow;

    public MapMenuController(Controller controller) {
        this.controller = controller;
    }

    private static boolean isCoordinatesNotValid(int column, int row) {
    }

    public String showMap(int column, int row) {
    }

    public String changeCurrentUnit(String[] directions, int[] counts) {
    }

    public String showDetails(int column, int row) {
    }

    public String exit() {
    }
}