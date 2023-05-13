package controller;

import model.BackGroundColor;

import java.util.HashMap;
import java.util.Map;

public class MapMenuController {
    private final Controller controller;
    private int currentColumn;
    private int currentRow;

    public MapMenuController(Controller controller) {
        this.controller = controller;
    }

    public String showMap(int row, int column) {
        StringBuilder map = new StringBuilder();
        int startingColumn = Math.min(column - 10, 0);
        int startingRow = Math.min(row - 10, 0);
        int endingColumn = Math.min(column + 10, controller.getGame().getCurrentMatch().getMAX_COLUMN());
        int endingRow = Math.min(row + 10, controller.getGame().getCurrentMatch().getMAX_ROW());
        for (int i = startingRow; i <= endingRow; i++) {
            for (int j = startingColumn; j <= endingColumn; j++) {
                map.append(controller.getGame().getCurrentMatch().getCell(i, j).getLandType().getBackGroundColor()
                   .getANSICode()).append(controller.getGame().getCurrentMatch().getCell(i, j).getSymbol()).append(BackGroundColor.RESET.getANSICode());
            }
            map.append("\n");
        }
        return map.toString();
    }

    public String changeCurrentCell(String[] directions, int[] counts) {
        for (int i = 0; i < directions.length; i++) {
            switch (directions[i]) {
                case "up":
                    currentRow -= counts[i];
                    break;
                case "down":
                    currentRow += counts[i];
                    break;
                case "left":
                    currentColumn -= counts[i];
                    break;
                case "right":
                    currentColumn += counts[i];
                    break;
            }
        }
        if (controller.getGame().getCurrentMatch().areCoordinatesNotValid(currentRow, currentColumn)) {
            return "coordinates are not valid!";
        }
        return "current cell changed!";
    }

    public String showCellDetails(int row, int column) {
        StringBuilder details = new StringBuilder();
        details.append("Land Type: ").append(controller.getGame().getCurrentMatch().getCell(column, row).getLandType().toString()).append("\n");
        details.append("Building: ").append(controller.getGame().getCurrentMatch().getCell(column, row).getBuilding().getType()).append("\n");
        HashMap<String, Integer> troopsPassing = controller.getGame().getCurrentMatch().getCell(column, row).getTroopsPassing();
        for (Map.Entry<String, Integer> entry : troopsPassing.entrySet()) {
            details.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
        }
        return details.toString();
    }

    public String exitMapMenu() {
        return controller.enterMatchMenu();
    }
}