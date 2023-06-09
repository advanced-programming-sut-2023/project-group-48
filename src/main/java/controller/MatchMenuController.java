package controller;

import model.Buildings.Building;
import model.Buildings.BuildingType;
import model.Buildings.Trap;
import model.Match.*;
import model.People.People;
import model.People.PeopleType;
import model.People.State;
import model.People.Troop;
import view.MatchMenuJFX;
import view.PeopleShape;

import java.util.ArrayList;
import java.util.Map;

public class MatchMenuController {
    private final Controller controller;
    private final MatchMenuJFX matchMenuJFX;
    private Match match;

    public MatchMenuController(Controller controller, MatchMenuJFX matchMenuJFX) {
        this.controller = controller;
        this.matchMenuJFX = matchMenuJFX;
        this.match = null;
    }

    public void setMatch() {
        this.match = controller.getGame().getCurrentMatch();
    }

    public String showMyInfo() {
        String result = "username : " + match.getCurrentPlayer().getUsername() + "\n";
        result += "coins : " + match.getCurrentPlayer().getGovernance().getPropertyCount(Property.COIN) + "\n";
        result += "popularity : " + match.getCurrentPlayer().getGovernance().getPopularity() + "\n";
        result += "population : " + match.getCurrentPlayer().getGovernance().getPopulation() + "\n";

        return result;
    }

    public String showCurrentPlayer() {
        return "current player : " + match.getCurrentPlayer().getUsername();
    }

    public String showRoundsPlayed() {
        return "rounds played : " + match.getRoundsPlayed();
    }

    public String showPopularityFactors() {
        String result = "your popularity factors are :\n";
        for (PopularityFactor popularityFactor : PopularityFactor.getPopularityFactors()) {
            result = result + popularityFactor.getPopularityFactorInString() + " : "
                    + match.getCurrentPlayer().getGovernance().getPopularityFactor(popularityFactor) + "\n";
        }
        result += showPopularity();
        return result;
    }

    public String showPopularity() {
        return "your total popularity is : " + match.getCurrentPlayer().getGovernance().getPopularity();
    }

    public String showFoodList() {
        StringBuilder result = new StringBuilder();
        for (Map.Entry<Property, Integer> entry : match.getCurrentPlayer().getGovernance().getFoods().entrySet()) {
            if (entry.getValue() != 0)
                result.append(entry.getKey().getPropertyInString()).append(": ").append(entry.getValue()).append("\n");
        }
        if (result.toString().equals("")) return "no food!";
        return result.toString();
    }

    public String setFoodRate(int foodRate) {
        if (foodRate < -2 || foodRate > 2) return "invalid food rate! (must be between -2 and 2)";
        match.getCurrentPlayer().getGovernance().setFoodRate(foodRate);
        return "food rate set successfully!";
    }

    public String showFoodRate() {
        return "food rate : " + match.getCurrentPlayer().getGovernance().getFoodRate();
    }

    public String setTaxRate(int taxRate) {
        if (taxRate < -3 || taxRate > 8) return "invalid tax rate! (must be between -3 and 8)";
        match.getCurrentPlayer().getGovernance().setTaxRate(taxRate);
        return "tax rate set successfully!";
    }

    public String showTaxRate() {
        return "tax rate : " + match.getCurrentPlayer().getGovernance().getTaxRate();
    }

    public String setFearRate(int fearRate) {
        if (fearRate < -5 || fearRate > 5) return "invalid fear rate! (must be between -5 and 5)";
        match.getCurrentPlayer().getGovernance().setFearRate(fearRate);
        return "fear rate set successfully!";
    }

    public String showFearRate() {
        return "fear rate : " + match.getCurrentPlayer().getGovernance().getFearRate();
    }

    public String dropBuilding(int row, int column, String type, String... direction) {
        BuildingType buildingType = BuildingType.getBuildingType(type);
        if (match.areCoordinatesNotValid(row, column))
            return "invalid coordinates!";
        if (buildingType == null) return "invalid building type!";
        if (direction.length != 0 && Direction.getDirection(direction[0]) == null)
            return "invalid direction!";
        if (match.getCell(row, column).getBuilding() != null)
            return "there is already a building here!";
        if (!Building.isLandTypeValidForBuilding(type, match.getCell(row, column).getLandType()))
            return "this building cannot be built on this land type!";
        if (!match.getCurrentPlayer().getGovernance().areResourcesEnoughToBuild(type))
            return "you don't have enough resources to build this building!";

        Direction dir = direction.length == 0 ? null : Direction.getDirection(direction[0]);
        Governance buildingGovernance = match.getCurrentPlayer().getGovernance();

        Building building = Building.createBuildingByType(buildingGovernance, row, column, type, buildingType, dir);
        buildingGovernance.payForBuilding(type);
        buildingGovernance.addBuilding(building);
        match.getCell(row, column).setBuilding(building);
        return "building dropped successfully!";
    }

    public String selectBuilding(int row, int column) {
        if (match.areCoordinatesNotValid(row, column))
            return "invalid coordinates!";
        if (match.getCell(row, column).getBuilding() == null)
            return "there is no building here!";
        if (!match.getCell(row, column).getBuilding().getGovernance().equals(match.getCurrentPlayer().getGovernance()))
            return "this building is not yours!";

        match.setSelectedBuilding(match.getCell(row, column).getBuilding());
        return "building selected successfully!";
    }

    public String createUnit(String type, int count) {
        PeopleType peopleType = PeopleType.getPersonType(type);
        Building building = match.getSelectedBuilding();
        if (building == null) return "no building selected!";
        if (peopleType == null) return "invalid People type!";
        if (count <= 0) return "invalid count!";
        if (!building.canCreateUnit(type)) return "this building cannot create this unit!";
        int row = building.getRow();
        int column = building.getColumn();
        if (People.isLandTypeNotValidForCreatingUnit(type, match.getCell(row, column).getLandType()))
            return "this unit cannot be created on this land type!";
        if (PeopleType.isTroop(type) && !match.getSelectedBuilding().getGovernance().areResourcesEnoughToCreateUnit(type, count))
            return "you don't have enough resources to create this unit!";
        if (!match.getCurrentPlayer().getGovernance().isPopularityEnoughToCreateUnit(type))
            return "your popularity is not enough to create this unit!";

        match.placePeople(building.getRow(), building.getColumn() + 1, type, peopleType, count);
        match.getSelectedBuilding().getGovernance().payForCreatingUnit(type, count);
        return "unit created successfully!";
    }

    public String repair() {
        Building building = match.getSelectedBuilding();
        if (building == null) return "no building selected!";
        if (!BuildingType.isCastle(match.getSelectedBuilding().getType()))
            return "selected building is not a castle!";
        if (match.getSelectedBuilding().getHp() == BuildingType.getHP(match.getSelectedBuilding().getType()))
            return "building is already at full health!";
        if (!building.getGovernance().areResourcesEnoughToRepair(building.getType()))
            return "you don't have enough resources to repair this building!";
        if (match.getNearByEnemy(match.getCurrentPlayer().getGovernance(), building.getRow(), building.getColumn(), 1).size() != 0)
            return "there is an enemy nearby!";

        match.getSelectedBuilding().repair();
        match.getSelectedBuilding().getGovernance().payForRepair(building.getType());
        return "building repaired successfully!";
    }

    public String setTexture(int row, int column, String type) {
        LandType landType = LandType.getLandType(type);
        if (match.areCoordinatesNotValid(row, column)) {
            System.out.println(row + " " + column);
            return "invalid coordinates!";
        }
        if (landType == null) return "invalid texture type!";
        if (match.getCell(row, column).getLandType().toString().equals(type))
            return "this texture is already set!";

        match.getCell(row, column).setLandType(landType);
        return "texture set successfully!";
    }

    public String setTexture(int row1, int column1, int row2, int column2, String type) {
        LandType landType = LandType.getLandType(type);
        if (match.areCoordinatesNotValid(row1, column1) ||
                match.areCoordinatesNotValid(row2, column2) ||
                row1 > row2 || column1 > column2)
            return "invalid coordinates!";
        if (landType == null) return "invalid texture type!";

        for (int i = row1; i <= row2; i++) {
            for (int j = column1; j <= column2; j++) {
                match.getCell(i, j).setLandType(landType);
            }
        }
        return "texture set successfully!";
    }

    public String clear(int row, int column) {
        if (match.areCoordinatesNotValid(row, column))
            return "invalid coordinates!";

        match.getCell(row, column).clearCell();
        match.getCell(row, column).setLandType(LandType.LAND);
        return "cell cleared successfully!";
    }

    public String dropRockOrTree(int row, int column, String type, Direction direction) {
        if (match.areCoordinatesNotValid(row, column))
            return "invalid coordinates!";
        if (match.getCell(row, column).getBuilding() != null)
            return "there is a building here!";

        Building building = Building.createBuildingByType(null, row, column, type, BuildingType.ENVIRONMENT, direction);
        match.getCell(row, column).setBuilding(building);
        return "rock dropped successfully!";
    }

    public String selectUnit(int row, int column) {
        if (match.areCoordinatesNotValid(row, column))
            return "invalid coordinates!";

        if (match.getCell(row, column).getPeople().size() == 0) return "there is no unit here!";

        match.setSelectedUnit(new ArrayList<>(match.getCell(row, column).getPeople()));
        return "unit selected successfully!";
    }

    public void selectUnit(ArrayList<PeopleShape> peopleShapes) {
        ArrayList<People> people = new ArrayList<>();
        for (PeopleShape peopleShape : peopleShapes) {
            people.add(peopleShape.getPeople());
        }
        match.setSelectedUnit(people);
    }

    public String disbandUnit() {
        match.setSelectedUnit(null);
        return "unit disbanded successfully!";
    }

    public String moveUnit(int row, int column) {
        if (match.getSelectedUnit() == null) return "no unit selected!";
        if (match.areCoordinatesNotValid(row, column)) return "invalid coordinates!";
        People firstPeople = match.getSelectedUnit().get(0);
        ArrayList<Direction> path = match.givePath(firstPeople.getRow(), firstPeople.getColumn(), row, column);
        for (People people : match.getSelectedUnit()) {
            people.setPath(new ArrayList<>(path));
            if (!match.getMovingPeople().contains(people)) match.getMovingPeople().add(people);
        }
        return "unit path set successfully!";
    }

    public String patrolUnit(int row1, int column1, int row2, int column2) {
        if (match.getSelectedUnit() == null) return "no unit selected!";
        if (match.areCoordinatesNotValid(row1, column1) || match.areCoordinatesNotValid(row2, column2))
            return "invalid coordinates!";
        ArrayList<Direction> path1 = match.givePath(row1, column1, row2, column2);
        ArrayList<Direction> path2 = match.givePath(row2, column2, row1, column1);
        for (People people : match.getSelectedUnit()) {
            if (people instanceof Troop) {
                Troop troop = (Troop) people;
                troop.setPath(new ArrayList<>(path1));
                if (!match.getMovingPeople().contains(troop)) match.getMovingPeople().add(troop);
                troop.setPatrolMode(true);
                troop.setPatrolPath1(new ArrayList<>(path1));
                troop.setPatrolPath2(new ArrayList<>(path2));
                troop.setPatrolPathIndex(1);
            }
        }
        return "unit patrol path set successfully!";
    }

    public String attack(int row, int column) {
        if (match.getSelectedUnit() == null) return "no unit selected!";
        if (match.areCoordinatesNotValid(row, column)) return "invalid coordinates!";
        People firstPeople = match.getSelectedUnit().get(0);
        ArrayList<Direction> path = match.givePath(firstPeople.getRow(), firstPeople.getColumn(), row, column);
        for (People people : match.getSelectedUnit()) {
            people.setPath(new ArrayList<>(path));
            if (!match.getMovingPeople().contains(people)) match.getMovingPeople().add(people);
        }
        return "unit path set successfully!";
    }

    public String stopPatrolUnit() {
        if (match.getSelectedUnit() == null) return "no unit selected!";
        for (People people : match.getSelectedUnit()) {
            if (people instanceof Troop) {
                Troop troop = (Troop) people;
                troop.setPatrolMode(false);
                troop.setPath(null);
                troop.setPatrolPath1(null);
                troop.setPatrolPath2(null);
            }
        }
        return "unit patrol stopped successfully!";
    }

    public String setTroopsState(int row, int column, String state) {
        State state1 = State.getStateFromString(state);
        if (match.areCoordinatesNotValid(row, column))
            return "invalid coordinates!";
        if (state1 == null) return "invalid state!";

        Cell cell = match.getCell(row, column);
        for (People person : cell.getPeople()) {
            if (person instanceof Troop) {
                ((Troop) person).setState(state1);
            }
        }
        return "troops state set successfully!";
    }

    public String digTunnel(int row, int column) {
        if (match.areCoordinatesNotValid(row, column))
            return "invalid coordinates!";

        match.getCell(row, column).setHasTunnel(true);
        return "tunnel dug successfully!";
    }

    public String collapseTunnel(int row, int column) {
        Cell cell = match.getCell(row, column);
        if (!cell.hasTunnel()) return "there is no tunnel here!";

        cell.clearCell();
        return "tunnel collapsed successfully!";
    }

    public String digDitch(int row, int column) {
        if (match.areCoordinatesNotValid(row, column))
            return "invalid coordinates!";
        if (match.getCell(row, column).getBuilding() != null)
            return "there is a building here!";

        match.getCell(row, column).setBuilding(new Trap(match.getCurrentPlayer().getGovernance(), row, column, "Pitch Ditch", BuildingType.TRAP, null));
        return "ditch dug successfully!";
    }

    public String pourOil(int row, int column) {
        if (match.areCoordinatesNotValid(row, column))
            return "invalid coordinates!";
        if (match.getCell(row, column).getBuilding() == null || !match.getCell(row, column).getBuilding().getType().equals("Pitch Ditch"))
            return "there is no ditch here!";

        Trap trap = (Trap) match.getCell(row, column).getBuilding();
        trap.setHasOil(true);
        return "oil poured successfully!";
    }

    public String fillDitch(int row, int column) {
        Building building = match.getCell(row, column).getBuilding();
        if (building == null) return "no building selected!";
        if (!building.getType().equals("Pitch Ditch"))
            return "this building is not a ditch!";

        match.getCell(building.getRow(), building.getColumn()).setBuilding(null);
        return "ditch filled successfully!";
    }

    public String nextTurn() {
        match.nextTurn();
        if (match.isRoundFinished())
            if (match.nextRound()) {
                if (match.getMatchWinner() != null) match.getMatchWinner().getOwner().setHighScore(match.getMatchWinner().getOwner().getHighScore() + (match.getGovernances().size()-match.getSultanCount())*100);
                String result = (match.getMatchWinner() == null) ? "now winner!" : match.getMatchWinner().getOwner().getUsername() + " won the match!\n";
                controller.getGame().setCurrentMatch(null);
                match = null;
                controller.enterMainMenuJFX();
                return "match is finished!\n" + result;
            }
        return (match.isRoundFinished() ? "round finished\n" : "") +
                match.getCurrentPlayer().getUsername() + " is now playing!";
    }

    public void isSick(People people) {
        matchMenuJFX.getMapJFX().setTileSicked(people.getRow(), people.getColumn());
    }

    public void isCured(People people) {
        matchMenuJFX.getMapJFX().setTileCured(people.getRow(), people.getColumn());
    }

    public String showPeopleDetails(People people) {
        return "type: " + people.getType() +
                "\nhp: " + people.getHp() +
                "\nhealth: " + people.isSick();
    }

    public void deselect() {
        disbandUnit();
    }
}