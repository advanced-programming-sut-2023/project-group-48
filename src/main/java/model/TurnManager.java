package model;

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

    public TurnManager(Match match) {
        this.match = match;
        deadPeople = new ArrayList<>();
    }

    public void nextTurn() {
    }

    private void doFights() {
        for (People people : match.getAllPeople()) {
            int row = people.getRow();
            int column = people.getColumn();
            if (people instanceof Troop) {
                Troop attacker = (Troop) people;
                for (People target : match.getNearByEnemy(row, column, attacker.getFireRange())) {
                    attacker.attack(target);
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
                }
            }
        }
    }

    private boolean areMovesFinished() {
        for (People movingPerson : match.getMovingPeople()) {
            if (movingPerson.getPath().size() > 0) return false;
        }
        return true;
    }

    public void run() {
        while (!areMovesFinished()) {
            doFights();
            removeDeadPeople();
            moveAllPeople();
            // TODO update popularity
        }
        useFoods();
    }

    public void nextRound() {
    }
}