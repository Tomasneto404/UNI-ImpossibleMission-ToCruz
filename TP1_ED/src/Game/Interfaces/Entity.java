package Game.Interfaces;

import ExceptionsAulas.InvalidElementException;
import Game.Mission.Division;


/**
 * The Entity interface defines the common properties and behaviors
 * for entities in the game, such as players or other interactable objects.
 *
 * @author Tânia Morais
 * @author Tomás Neto
 */
public interface Entity {

    /**
     * Gets the name of the entity.
     *
     * @return the name of the entity
     */
    public String getName();

    /**
     * Gets the division where the entity is currently located.
     *
     * @return the current division of the entity
     */
    public Division getDivision();

    /**
     * Sets the division for the entity.
     *
     * @param division the new division for the entity
     */
    public void setDivision(Division division);

    /**
     * Sets the name of the entity.
     *
     * @param name the new name of the entity
     */
    public void setName(String name);

    /**
     * Gets the power level of the entity.
     *
     * @return the power of the entity
     */
    public int getPower();

    /**
     * Sets the power level of the entity.
     *
     * @param power the new power of the entity
     */
    public void setPower(int power);

    /**
     * Gets the health of the entity.
     *
     * @return the health of the entity
     */
    public int getHealth();

    /**
     * Sets the health of the entity.
     *
     * @param health the new health of the entity
     */
    public void setHealth(int health);

    /**
     * Calculates the remaining health of the entity after taking damage
     * based on the opposing entity's power.
     *
     * @param OpositePower the attack power of the opposing entity
     * @return the remaining health of the entity
     * @throws InvalidElementException if the opposing power is invalid
     */
    public int calculateHealth(int OpositePower) throws InvalidElementException;

    /**
     * Checks if the entity is still alive based on its health.
     *
     * @return true if the entity's health is greater than zero, false otherwise
     */
    public boolean isAlive();

    /**
     * Applies damage to the entity by reducing its health.
     *
     * @param damage the amount of damage to be applied
     */
    public void takeDamage(int damage);
}
