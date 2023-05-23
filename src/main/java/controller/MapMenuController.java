package controller;

import model.BackGroundColor;
import model.Buildings.Building;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MapMenuController {
    private final Controller controller;
    private int currentColumn;
    private int currentRow;

    public MapMenuController(Controller controller) {
        this.controller = controller;
        this.currentRow = 0;
        this.currentColumn = 0;
    }

    public String showMap(int row, int column) {
        currentRow = row;
        currentColumn = column;
        StringBuilder map = new StringBuilder();
        int startingColumn = Math.max(column - 10, 1);
        int startingRow = Math.max(row - 10, 1);
        int endingColumn = Math.min(column + 10, controller.getGame().getCurrentMatch().getMAX_COLUMN());
        int endingRow = Math.min(row + 10, controller.getGame().getCurrentMatch().getMAX_ROW());
        for (int k = 0; k < endingColumn - startingColumn; k++) map.append(" ");
        map.append("(").append(endingColumn).append(", ").append(endingRow).append(")\n");
        for (int i = endingRow; i >= startingRow; i--) {
            for (int j = startingColumn; j <= endingColumn; j++) {
                map.append(controller.getGame().getCurrentMatch().getCell(i, j).getLandType().getBackGroundColor()
                        .getANSICode()).append(controller.getGame().getCurrentMatch().getCell(i, j).getSymbol()).append(BackGroundColor.RESET.getANSICode());
            }
            map.append("\n");
        }
        map.append("(").append(startingColumn).append(", ").append(startingRow).append(")");
        return map.toString();
    }

    public String changeCurrentCell(String[] vDirection, String[] hDirection) {
        int hCount, vCount;
        hCount = (hDirection[1] != null) ? Integer.parseInt(hDirection[1]) : 1;
        vCount = (vDirection[1] != null) ? Integer.parseInt(vDirection[1]) : 1;
        ArrayList<String> horizontalMoves = new ArrayList<>(Arrays.asList("right", "left"));
        ArrayList<String> verticalMoves = new ArrayList<>(Arrays.asList("up", "down"));
        if (!horizontalMoves.contains(hDirection[0]) && hDirection[0] != null) return "invalid direction";
        if (!verticalMoves.contains(vDirection[0]) && vDirection[0] != null) return "invalid direction";
        int finalRow = currentRow, finalColumn = currentColumn;
        if (vDirection[0] != null) finalRow += vCount * (vDirection[0].equals("up") ? 1 : -1);
        if (hDirection[0] != null) finalColumn += hCount * (hDirection[0].equals("right") ? 1 : -1);
        if (controller.getGame().getCurrentMatch().areCoordinatesNotValid(finalRow, finalColumn))
            return "invalid move!";

        currentRow = finalRow;
        currentColumn = finalColumn;
        return showMap(currentRow, currentColumn);
    }

    public String showCellDetails(int row, int column) {
        StringBuilder details = new StringBuilder();
        Building building = controller.getGame().getCurrentMatch().getCell(row, column).getBuilding();
        details.append("Land Type: ").append(controller.getGame().getCurrentMatch().getCell(row, column).getLandType().toString()).append("\n");
        if (building != null) details.append("Building: ").append(building.getType()).append("\n");
        HashMap<String, Integer> troopsPassing = controller.getGame().getCurrentMatch().getCell(row, column).getTroopsPassing();
        for (Map.Entry<String, Integer> entry : troopsPassing.entrySet()) {
            details.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
        }
        details.deleteCharAt(details.length() - 1);
        return details.toString();
    }
}