package Game.Entitys;

import ClassesAulas.ArrayUnorderedList;
import ExceptionsAulas.EmptyCollectionException;
import ExceptionsAulas.InvalidElementException;
import Game.Mission.Division;
import Game.Interfaces.Entity;
import Game.Mission.Map;

import java.util.Iterator;

/***
 * The Enemy class represents an enemy entity in the game, with properties such as name, power, health and division.
 * It implements the Entity interface and provide methods for managing the enemy's state.
 * @author Tânia Morais
 * @author Tomás Neto
 */
public class Enemy implements Entity {

    private String name;
    private int power;
    private Division division;
    private int health;
    private int counter;

    /***
     * Contructs an Enemy with a specified name, power, division and default health.
     * @param name the name of the enemy
     * @param power the power of the enemy
     * @param division the division to which enemy belongs.
     */
    public Enemy(String name, int power, Division division) {
        this.name = name;
        this.power = power;
        this.division = division;
        this.health = 20;
        this.counter = 0;
    }

    /**
     * Contructs an Enemy with a specified name, power, division and health.
     *
     * @param name     the name of the enemy
     * @param power    the power of the enemy
     * @param division the division to which the enemy belongs.
     * @param health   the health of the enemy
     */
    public Enemy(String name, int power, Division division, int health) {
        this.name = name;
        this.power = power;
        this.division = division;
        this.health = health;
        this.counter = 0;
    }


    /**
     * Sets the division of the enemy
     *
     * @param division the new division for the enemy
     * @throws EmptyCollectionException if the division is null or invalid.
     */
    public void newDivision(Division division) throws EmptyCollectionException {
        this.division = division;
    }

    /**
     * Reduces the enemy's health by a specified damage amount. If the health drops below zero, it is set to zero
     *
     * @param damage the amount of damage to be inflicted on the enemy
     */
    public void takeDamage(int damage) {
        this.health -= damage;
        if (this.health < 0) {
            this.health = 0;
        }
    }

    /**
     * Gets the name of the enemy.
     *
     * @return the name of the enemy
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * Gets the division of the enemy.
     *
     * @return the division of the enemy
     */
    @Override
    public Division getDivision() {
        return this.division;
    }

    /**
     * Sets the division for the enemy.
     *
     * @param division the new division for the enemy.
     */
    @Override
    public void setDivision(Division division) {
        this.division = division;
    }

    /**
     * Sets the name for the enemy.
     *
     * @param name the new name for the enemy.
     */
    @Override
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the power of the enemy.
     *
     * @return the power of the enemy
     */
    @Override
    public int getPower() {
        return this.power;
    }

    /**
     * Sets the power for the enemy.
     *
     * @param power the new power for the enemy.
     */
    @Override
    public void setPower(int power) {
        this.power = power;
    }

    /**
     * Gets the health of the enemy.
     *
     * @return the health of the enemy
     */
    @Override
    public int getHealth() {
        return this.health;
    }

    /**
     * Sets the health for the enemy.
     *
     * @param health the new health for the enemy.
     */
    @Override
    public void setHealth(int health) {
        this.health = health;
    }

    /***
     * Calculates the remaining health of the enemy after taking damage based on the opposite power.
     * @param OpositePower the power of the opposing entity, which is used to calculate the damage.
     * @return the remaining health of the enemy after taking the damage
     * @throws InvalidElementException if the opposite power is less than or equal to zero
     */
    @Override
    public int calculateHealth(int OpositePower) throws InvalidElementException {
        if (OpositePower <= 0) {
            throw new InvalidElementException("Oposite power must be greater than zero");
        }
        this.health = this.health - OpositePower;
        return this.health;
    }

    /***
     * Checks whether the enemy is still alive.
     * @return true if the enemy's health is greater than zero, false otherwise.
     */
    @Override
    public boolean isAlive() {
        return this.health > 0;
    }
}