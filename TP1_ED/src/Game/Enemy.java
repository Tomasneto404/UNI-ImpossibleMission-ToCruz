package Game;

import ClassesAulas.ArrayList;
import ClassesAulas.ArrayUnorderedList;
import ClassesAulas.LinkedList;
import ClassesAulas.LinkedQueue;
import ExceptionsAulas.EmptyCollectionException;
import ExceptionsAulas.InvalidElementException;
import Game.Interfaces.CommonDetails;

import java.util.Iterator;

public class Enemy implements CommonDetails {



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


    public void moveEnemy(Map map, Enemy enemy) throws EmptyCollectionException {
        //Comparar a division do enemy e ver quais sao adjacentes num máximo de graus e mover aleatoriamente
    }

    public void moveEnemies(Map map, ArrayUnorderedList<Enemy> enemies) throws EmptyCollectionException {
        Iterator<Enemy> iterator = enemies.iterator();
        while (iterator.hasNext()) {
            Enemy enemy = iterator.next();
            try {
                moveEnemy(map, enemy);
            } catch (EmptyCollectionException e) {
                throw new EmptyCollectionException("It's impossible move enemies");
            }
        }
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