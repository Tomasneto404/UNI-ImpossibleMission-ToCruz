package Game.Entitys;

import ClassesAulas.ArrayUnorderedList;
import ExceptionsAulas.EmptyCollectionException;
import ExceptionsAulas.InvalidElementException;
import Game.Mission.Division;
import Game.Interfaces.Entity;
import Game.Mission.Map;

import java.util.Iterator;

public class Enemy implements Entity {



    private String name;
    private int power;
    private Division division;
    private int health;
    private int counter;

    public Enemy(String name, int power, Division division) {
        this.name = name;
        this.power = power;
        this.division = division;
        this.health = 20;
        this.counter = 0;
    }

    public Enemy(String name, int power, Division division, int health) {
        this.name = name;
        this.power = power;
        this.division = division;
        this.health = health;
        this.counter = 0;
    }


    public void newDivision(Division division) throws EmptyCollectionException {
        this.division=division;
    }


    public void takeDamage(int damage) {
        this.health -= damage;
        if (this.health < 0) {
            this.health = 0;
        }
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Division getDivision() {
        return this.division;
    }

    @Override
    public void setDivision(Division division) {
        this.division = division;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int getPower() {
        return this.power;
    }

    @Override
    public void setPower(int power) {
        this.power = power;
    }

    @Override
    public int getHealth() {
        return this.health;
    }

    @Override
    public void setHealth(int health) {
        this.health = health;
    }

    @Override
    public int calculateHealth(int OpositePower) throws InvalidElementException {
        if (OpositePower <= 0) {
            throw new InvalidElementException("Oposite power must be greater than zero");
        }
        this.health = this.health - OpositePower;
        return this.health;
    }

    @Override
    public boolean isAlive() {
        return this.health > 0;
    }
}