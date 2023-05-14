package model;

import model.Buildings.Building;
import model.Buildings.BuildingType;
import model.Buildings.IndustrialCenter;
import model.Match.Direction;
import model.Match.Governance;
import model.Match.Match;
import model.Match.Property;
import model.People.People;
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
        for (People people : match.getAllTroops()) {
            int row = people.getRow();
            int column = people.getColumn();
            if (people instanceof Troop) {
                Troop attacker = (Troop) people;
                for (People target : match.getNearByEnemy(row, column, attacker.getFireRange())) {
                    attacker.attackPeople(target);
                    if (target.getHp() <= 0) deadPeople.add(target);
                }
            }
        }
    }

    private void removeDeadPeople() {
        for (People deadPerson : deadPeople) {
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
        for (People people : match.getMovingPeople()) {
            if (people.getPath().size() > 0) {
                movePeople(people);
            }
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
                    governance.unloadStorages(entry.getKey(), toUse);
                }
            }
        }
    }
    private void updateAllIndustries() {
        for (Governance governance : match.getGovernances()) {
            for (Building building : governance.getBuildings()) {
                if(building.getBuildingType().equals(BuildingType.INDUSTRIAL_CENTER)){
                    if(((IndustrialCenter)building).canProduce()){
                        ((IndustrialCenter)building).produce();
                    }
                }
            }
        }
    }
    private void attackBuildings() {
        for (People people : match.getAllTroops()) {
            int row = people.getRow();
            int column = people.getColumn();
            if (people instanceof Troop) {
                Troop attacker = (Troop) people;
                for (Building target : match.getNearByEnemyBuildings(row, column, attacker.getFireRange())) {
                    attacker.attackBuilding(target);
                    if (target.getHp() <= 0) destroyedBuildings.add(target);
                }
            }
        }
    }

    private void removeDestroyedBuildings() {
        for (Building destroyedBuilding : destroyedBuildings) {;
            destroyedBuilding.getGovernance().getBuildings().remove(destroyedBuilding);
            match.getCell(destroyedBuilding.getRow(), destroyedBuilding.getColumn()).setBuilding(null);
        }
        destroyedBuildings.clear();
    }

    private void updateAllPopularities() {
        for (Governance governance : match.getGovernances()) {
            governance.updatePopularity();
        }
    }

    private boolean areMovesFinished() {
        for (People movingPerson : match.getMovingPeople()) {
            if (movingPerson.getPath().size() > 0) return false;
        }
        return true;
    }

    public void run() {
        //while (!areMovesFinished()) {     OLD METHOD FOR TURNS
        for(int i=0;i<50;i++){
            doFights();
            removeDeadPeople();
            attackBuildings();
            removeDestroyedBuildings();
            moveAllPeople();
        }
        updateAllPopularities();
        useFoods();

    }

}