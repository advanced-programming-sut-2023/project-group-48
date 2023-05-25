package model;

import model.Buildings.Building;
import model.Buildings.BuildingType;
import model.Buildings.IndustrialCenter;
import model.Match.Direction;
import model.Match.Governance;
import model.Match.Match;
import model.Match.Property;
import model.People.People;
import model.People.State;
import model.People.Troop;

import java.util.ArrayList;
import java.util.Map;

public class TurnManager {
    private final Match match;
    private final ArrayList<People> deadPeople;
    private final ArrayList<Building> destroyedBuildings;

    public TurnManager(Match match) {
        this.match = match;
        this.deadPeople = new ArrayList<>();
        this.destroyedBuildings = new ArrayList<>();
    }

    private void doFights() {
        for (Troop troop : match.getAllTroops()) {
            int row = troop.getRow();
            int column = troop.getColumn();
            for (People target : match.getNearByEnemy(troop.getGovernance(), row, column, troop.getFireRange())) {
                troop.attackPeople(target);
                if (target.getHp() <= 0) deadPeople.add(target);
            }
        }
    }

    private void removeDeadPeople() {
        for (People deadPerson : deadPeople) {
            if(deadPerson.getType().equals("Sultan")){
                deadPerson.getGovernance().getOwner().setHighScore(deadPerson.getGovernance().getOwner().getHighScore() + (match.getGovernances().size()-match.getSultanCount())*100);
                match.removePlayer(deadPerson.getGovernance().getOwner());
                deadPerson.getGovernance().isSultanAlive = false;
            }
            match.removePeople(deadPerson);
        }
        deadPeople.clear();
    }

    private void movePeople(People people) {
        Direction direction = people.getPath().get(0);
        match.getCell(people.getRow(), people.getColumn()).removePeople(people);
        switch (direction) {
            case UP:
                people.setRow(people.getRow() + 1);
                break;
            case DOWN:
                people.setRow(people.getRow() - 1);
                break;
            case LEFT:
                people.setColumn(people.getColumn() - 1);
                break;
            case RIGHT:
                people.setColumn(people.getColumn() + 1);
                break;
        }
        match.getCell(people.getRow(), people.getColumn()).addPeople(people);
        people.getPath().remove(0);
    }

    private void moveAllPeople() {
        ArrayList<People> finishedPeople = new ArrayList<>();
        for (People people : match.getMovingPeople()) {
            if (people.getPath().size() > 0) {
                movePeople(people);
                if (people.getPath().size() == 0 && people instanceof Troop) {
                    Troop troop = (Troop) people;
                    if (troop.isInPatrolMode()) {
                        troop.setPath(troop.getPatrolPathIndex() == 1 ? troop.getPatrolPath1() : troop.getPatrolPath2());
                        troop.setPatrolPathIndex(troop.getPatrolPathIndex() == 1 ? 2 : 1);
                    } else {
                        finishedPeople.add(people);
                    }
                } else if (people.getPath().size() == 0) {
                    finishedPeople.add(people);
                }
            }
        }
        for (People people : finishedPeople) {
            match.getMovingPeople().remove(people);
        }
    }

    private void useFoods() {
        for (Governance governance : match.getGovernances()) {
            int usedFoodCount = governance.getPopulation() * ((governance.getFoodRate() + 2) / 2);
            for (Map.Entry<Property, Integer> entry : governance.getFoods().entrySet()) {
                if (usedFoodCount == 0) break;
                if (entry.getValue() > 0) {
                    int toUse = Math.min(usedFoodCount, entry.getValue());
                    usedFoodCount -= toUse;
                    entry.setValue(entry.getValue() - toUse);
                    governance.reduceProperty(entry.getKey(), toUse);
                }
            }
        }
    }

    private void updateAllIndustries() {
        for (Governance governance : match.getGovernances()) {
            for (Building building : governance.getBuildings()) {
                if (building.getBuildingType().equals(BuildingType.INDUSTRIAL_CENTER)) {
                    if (((IndustrialCenter) building).canProduce()) {
                        ((IndustrialCenter) building).produce();
                    }
                }
            }
        }
    }

    private void attackBuildings() {
        for (Troop troop : match.getAllTroops()) {
            int row = troop.getRow();
            int column = troop.getColumn();
            for (Building target : match.getNearByEnemyBuildings(troop, row, column, troop.getFireRange())) {
                troop.attackBuilding(target);
                if (target.getHp() <= 0) {
                    destroyedBuildings.add(target);
                }
            }
        }
    }

    private void removeDestroyedBuildings() {
        for (Building destroyedBuilding : destroyedBuildings) {
            destroyedBuilding.getGovernance().removeBuilding(destroyedBuilding);
            match.getCell(destroyedBuilding.getRow(), destroyedBuilding.getColumn()).setBuilding(null);
        }
        destroyedBuildings.clear();
    }

    private void updateAllPopularities() {
        for (Governance governance : match.getGovernances()) {
            governance.updatePopularity();
            governance.updatePopulation();
        }
    }

    private boolean areMovesFinished() {
        for (People movingPerson : match.getMovingPeople()) {
            if (movingPerson.getPath().size() > 0) return false;
        }
        return true;
    }

    private void addNonStandingTroops() {
        ArrayList<People> nearbyEnemy;
        for (Troop troop : match.getAllTroops()) {
            if (troop.getState().equals(State.DEFENSIVE) &&
                (nearbyEnemy = match.getNearByEnemy(troop.getGovernance(), troop.getRow(), troop.getColumn(), troop.getFireRange())).size() > 0 &&
                !match.getMovingPeople().contains(troop)) {
                People target = nearbyEnemy.get(0);
                troop.setPath(match.givePath(troop.getRow(), troop.getColumn(), target.getRow(), target.getColumn()));
                match.getMovingPeople().add(troop);
            }
            if (troop.getState().equals(State.AGGRESSIVE) &&
                (nearbyEnemy = match.getNearByEnemy(troop.getGovernance(), troop.getRow(), troop.getColumn(), 20)).size() > 0 &&
                !match.getMovingPeople().contains(troop)) {
                People target = nearbyEnemy.get(0);
                System.out.println(nearbyEnemy.size());
                System.out.println(troop.getRow() + " " + troop.getColumn() + " " + target.getRow() + " " + target.getColumn());
                ArrayList<Direction> path = match.givePath(troop.getRow(), troop.getColumn(), target.getRow(), target.getColumn());
                System.out.println(path.size());
                troop.setPath(path);
                match.getMovingPeople().add(troop);
            }
        }
    }

    public void run() {
        //while (!areMovesFinished()) {     OLD METHOD FOR TURNS
        addNonStandingTroops();
        for (int i = 0; i < 10; i++) {
            doFights();
            removeDeadPeople();
            attackBuildings();
            removeDestroyedBuildings();
            moveAllPeople();
        }
        updateAllPopularities();
        updateAllIndustries();
        useFoods();
    }
}