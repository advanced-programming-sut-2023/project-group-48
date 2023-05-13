package controller;

import model.Buildings.Building;
import model.Buildings.BuildingType;
import model.Match.*;
import model.People.People;
import model.People.PeopleType;
import model.People.State;
import model.People.Troop;

import java.util.Map;

public class MatchMenuController {
    private final Controller controller;
    private Match match;

    public MatchMenuController(Controller controller) {
        this.controller = controller;
        this.match = null;
    }

    public void setMatch(Match match) {
        this.match = match;
    }

    public String enterMapMenu() {
        return controller.enterMapMenu();
    }

    private boolean isEntryNotValid() {
    } // TODO: implement

    public String showMyInfo() {
    } // TODO: implement

    public String showCurrentPlayer() {
        return "current player : " + controller.getGame().getCurrentMatch().getCurrentPlayer().getUsername();
    }

    public String showRoundsPlayed() {
        return "rounds played : " + controller.getGame().getCurrentMatch().getRoundsPlayed();
    }

    public String showPopularityFactors() {
    } // TODO: implement

    public String showPopularity() {
    } // TODO: implement

    public String showFoodList() {
        StringBuilder result = new StringBuilder();
        for (Map.Entry<Food, Integer> entry : controller.getGame().getCurrentMatch().getCurrentPlayer().getGovernance().getFoods().entrySet()) {
            if (entry.getValue() != 0) result.append(entry.getKey().getName()).append(": ").append(entry.getValue()).append("\n");
        }
        if (result.toString().equals("")) return "no food!";
        return result.toString();
    }

    public String setFoodRate(int foodRate) {
    } // TODO: implement

    public String showFoodRate() {
        return "food rate : " + controller.getGame().getCurrentMatch().getCurrentPlayer().getGovernance().getFoodRate();
    }

    public String setTaxRate(int taxRate) {
    } // TODO: implement

    public String showTaxRate() {
        return "tax rate : " + controller.getGame().getCurrentMatch().getCurrentPlayer().getGovernance().getTaxRate();
    }

    public String setFearRate(int fearRate) {
    } // TODO: implement

    public String showFearRate() {
        return "fear rate : " + controller.getGame().getCurrentMatch().getCurrentPlayer().getGovernance().getFearRate();
    }

    // TODO : print which property is not enough ?
    public String dropBuilding(int row, int column, String type, String... direction) {
        BuildingType buildingType = BuildingType.getBuildingType(type);
        if (controller.getGame().getCurrentMatch().areCoordinatesNotValid(row, column))
            return "invalid coordinates!";
        if (buildingType == null) return "invalid building type!";
        if (direction.length != 0 && Direction.getDirection(direction[0]) == null)
            return "invalid direction!";
        if (controller.getGame().getCurrentMatch().getCell(row, column).getBuilding() != null)
            return "there is already a building here!";
        if (!Building.isLandTypeValidForBuilding(type, controller.getGame().getCurrentMatch().getCell(row, column).getLandType()))
            return "this building cannot be built on this land type!";
        if (controller.getGame().getCurrentMatch().getCurrentPlayer().getGovernance().areResourcesEnoughToBuild(type))
            return "you don't have enough resources to build this building!";

        Direction dir = direction.length == 0 ? null : Direction.getDirection(direction[0]);
        Governance buildingGovernance = controller.getGame().getCurrentMatch().getCurrentPlayer().getGovernance();

        Building building = Building.createBuildingByType(buildingGovernance, row, column, type, buildingType, dir);
        buildingGovernance.payForBuilding(type);
        controller.getGame().getCurrentMatch().getCell(row, column).setBuilding(building);
        return "building dropped successfully!";
    }

    public String selectBuilding(int row, int column) {
        if (controller.getGame().getCurrentMatch().areCoordinatesNotValid(row, column))
            return "invalid coordinates!";
        if (controller.getGame().getCurrentMatch().getCell(row, column).getBuilding() == null)
            return "there is no building here!";
        if (!controller.getGame().getCurrentMatch().getCell(row, column).getBuilding().getGovernance().equals(controller.getGame().getCurrentMatch().getCurrentPlayer().getGovernance()))
            return "this building is not yours!";

        controller.getGame().getCurrentMatch().setSelectedBuilding(controller.getGame().getCurrentMatch().getCell(row, column).getBuilding());
        return "building selected successfully!";
    }

    public String createUnit(String type, int count) {
        PeopleType peopleType = PeopleType.getPersonType(type);
        Building building = controller.getGame().getCurrentMatch().getSelectedBuilding();
        if (peopleType == null) return "invalid People type!";
        if (count <= 0) return "invalid count!";
        if (!building.canCreateUnit(type)) return "this building cannot create this unit!";
        int row = building.getRow();
        int column = building.getColumn();
        if (People.isLandTypeNotValidForCreatingUnit(type, controller.getGame().getCurrentMatch().getCell(row, column).getLandType()))
            return "this unit cannot be created on this land type!";
        if (!controller.getGame().getCurrentMatch().getSelectedBuilding().getGovernance().areResourcesEnoughToCreateUnit(type, count))
            return "you don't have enough resources to create this unit!";
        if (!controller.getGame().getCurrentMatch().getCurrentPlayer().getGovernance().isPopularityEnoughToCreateUnit(type))
            return "your popularity is not enough to create this unit!";

        controller.getGame().getCurrentMatch().placePeople(building.getRow(), building.getColumn(), type, peopleType, count);
        controller.getGame().getCurrentMatch().getSelectedBuilding().getGovernance().payForCreatingUnit(type, count);
        return "unit created successfully!";
    }

    public String repair() {
        Building building = controller.getGame().getCurrentMatch().getSelectedBuilding();
        if (building == null) return "no building selected!";
        if (!BuildingType.isCastle(controller.getGame().getCurrentMatch().getSelectedBuilding().getType()))
            return "selected building is not a castle!";
        if (controller.getGame().getCurrentMatch().getSelectedBuilding().getHp() == BuildingType.getHP(controller.getGame().getCurrentMatch().getSelectedBuilding().getType()))
            return "building is already at full health!";
        if (!building.getGovernance().areResourcesEnoughToRepair(building))
            return "you don't have enough resources to repair this building!";
        if (match.getNearByEnemy(building.getRow(), building.getColumn(), 1).size() != 0)
            return "there is an enemy nearby!";

        controller.getGame().getCurrentMatch().getSelectedBuilding().repair();
        controller.getGame().getCurrentMatch().getSelectedBuilding().getGovernance().payForRepair(building.getType());
        return "building repaired successfully!";
    }

    public String setTexture(int row, int column, String type) {
        LandType landType = LandType.getLandType(type);
        if (controller.getGame().getCurrentMatch().areCoordinatesNotValid(row, column))
            return "invalid coordinates!";
        if (landType == null) return "invalid texture type!";
        if (controller.getGame().getCurrentMatch().getCell(row, column).getLandType().equals(type))
            return "this texture is already set!";

        controller.getGame().getCurrentMatch().getCell(row, column).setLandType(landType);
        return "texture set successfully!";
    }

    public String setTexture(int row1, int column1, int row2, int column2, String type) {
        LandType landType = LandType.getLandType(type);
        if (controller.getGame().getCurrentMatch().areCoordinatesNotValid(row1, column1) ||
                controller.getGame().getCurrentMatch().areCoordinatesNotValid(row2, column2) ||
                row1 > row2 || column1 > column2)
            return "invalid coordinates!";
        if (landType == null) return "invalid texture type!";

        for (int i = row1; i <= row2; i++) {
            for (int j = column1; j <= column2; j++) {
                controller.getGame().getCurrentMatch().getCell(i, j).setLandType(landType);
            }
        }
        return "texture set successfully!";
    }

    public String clear(int row, int column) {
        if (controller.getGame().getCurrentMatch().areCoordinatesNotValid(row, column))
            return "invalid coordinates!";

        controller.getGame().getCurrentMatch().getCell(row, column).clearCell();
        controller.getGame().getCurrentMatch().getCell(row, column).setLandType(LandType.LAND);
        return "cell cleared successfully!";
    }

    public String dropRockOrTree(int row, int column, String type, Direction direction) {
        if (controller.getGame().getCurrentMatch().areCoordinatesNotValid(row, column))
            return "invalid coordinates!";
        if (controller.getGame().getCurrentMatch().getCell(row, column).getBuilding() != null)
            return "there is a building here!";

        Building building = Building.createBuildingByType(controller.getGame().getCurrentMatch().getCurrentPlayer().getGovernance(), row, column, type, BuildingType.ENVIRONMENT, direction);
        controller.getGame().getCurrentMatch().getCell(row, column).setBuilding(building);
        return "rock dropped successfully!";
    }

    public String enterTradeMenu() {
        return controller.enterTradeMenu();
    }

    public String selectUnit(int row, int column) {
        if (controller.getGame().getCurrentMatch().areCoordinatesNotValid(row, column))
            return "invalid coordinates!";

        controller.getGame().getCurrentMatch().setSelectedUnit(controller.getGame().getCurrentMatch().getCell(row, column).getPeople());
        return "unit selected successfully!";
    }

    public String moveUnit(int row, int column) {
        for (People people : controller.getGame().getCurrentMatch().getSelectedUnit()) {
            people.setPath(controller.getGame().getCurrentMatch().givePath(people.getRow(), people.getColumn(), row, column));
        }
        return "unit path set successfully!";
    }

    public String patrolUnit(int row1, int column1, int row2, int column2) {
        for (People people : controller.getGame().getCurrentMatch().getSelectedUnit()) {
            Troop troop = (Troop) people;
            troop.setPath(controller.getGame().getCurrentMatch().givePath(row1 , column1, row2, column2));
            troop.setPatrolMode(true);
            troop.setPatrolPoints(new int[][]{{row1, column1}, {row2, column2}});
        }
        return "unit patrol path set successfully!";
    }

    public String stopPatrolUnit(int row, int column) {
        // TODO: 5/29/2018
    }

    public String setTroopsState(int row, int column, String state) {
        State state1 = State.getStateFromString(state);
        if (controller.getGame().getCurrentMatch().areCoordinatesNotValid(row, column))
            return "invalid coordinates!";
        if (state1 == null) return "invalid state!";

        Cell cell = controller.getGame().getCurrentMatch().getCell(row, column);
        for (People person : cell.getPeople()) {
            if (person instanceof Troop) {
                ((Troop) person).setState(state1);
            }
        }
        return "troops state set successfully!";
    }

    public String attackEnemy(int enemyRow, int enemyColumn) {
    }

    public String attack(int row, int column) {
    }

    public String pourOil(String direction) {
    } // TODO: 5/29/2018

    public String digTunnel(int row, int column) {
        if (controller.getGame().getCurrentMatch().areCoordinatesNotValid(row, column))
            return "invalid coordinates!";
        Building building = Building.createBuildingByType(controller.getGame().getCurrentMatch().getCurrentPlayer().getGovernance(), row, column, "Tunnel", BuildingType.TRAP, null);
        controller.getGame().getCurrentMatch().getCell(row, column).setBuilding(building);
        return "tunnel dug successfully!";
    }

    public String buildEquipment(String equipmentName) {
        // TODO: 5/29/2018
    } // TODO: 5/29/2018

    public String disbandUnit() {
        controller.getGame().getCurrentMatch().setSelectedUnit(null);
        return "unit disbanded successfully!";
    }

    public String nextTurn() {
    }
}