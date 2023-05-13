package model;

import model.Match.Direction;
import model.Match.Match;
import model.People.People;
import model.People.Troop;

import java.util.ArrayList;

public class TurnManager {
    private final Match match;
    private ArrayList<People> deadPeople;

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
    }

    private void moveAllPeople() {
        for (People people : match.getAllPeople()) {
            if (people.getPath().size() > 0) {
                movePeople(people);
            }
        }
    }

    public void run() {

    }

    public void nextRound() {
    }
}