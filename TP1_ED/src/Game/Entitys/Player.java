package Game.Entitys;

import ClassesAulas.ArrayStack;
import ExceptionsAulas.EmptyCollectionException;
import ExceptionsAulas.InvalidElementException;
import Game.Items.Item;
import Game.Mission.Division;
import Game.Interfaces.*;
import Game.Items.BulletProofVest;
import Game.Mission.Target;

/**
 * The player class represents a player in the game.
 * The class implements the Entity interface and provides methods to damage the player's state, interact with targets, and use items.
 *
 * @author Tânia Morais
 * @author Tomás Neto
 */
public class Player implements Entity {

    private final int DEFAULT_HEALTH = 100;
    private final int DEFAULT_POWER = 10;
    private String name;
    private int power;
    private Division division;
    private ArrayStack<Item> backpack;
    private int health;
    private Target target;
    private boolean usedItem;
    private boolean inTheBuilding;

    {
        this.target = null;
        this.power = DEFAULT_POWER;
        this.division = null;
        this.backpack = new ArrayStack<>();
        this.health = DEFAULT_HEALTH;
    }

    /**
     * Constructors a player with only a name
     *
     * @param name the name of the player
     */
    public Player(String name) {
        this.name = name;
    }

    /**
     * Gets the name of the player.
     *
     * @return the name of the player.
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * Gets the division of the player.
     *
     * @return the division of the player.
     */
    @Override
    public Division getDivision() {
        return this.division;
    }

    /**
     * Sets the division to which the player belongs.
     *
     * @param division the new division for the player
     */
    @Override
    public void setDivision(Division division) {
        this.division = division;
    }

    /**
     * Sets the name to which the player belongs.
     *
     * @param name the new name for the player
     */
    @Override
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the power of the player.
     *
     * @return the power of the player.
     */
    @Override
    public int getPower() {
        return this.power;
    }

    /**
     * Sets the power to which the player belongs.
     *
     * @param power the new power for the player
     */
    @Override
    public void setPower(int power) {
        this.power = power;
    }

    /**
     * Gets the health of the player.
     *
     * @return the health of the player.
     */
    public int getHealth() {
        return health;
    }

    /**
     * Sets the health to which the player belongs.
     *
     * @param health the new health for the player
     */
    public void setHealth(int health) {
        this.health = health;
    }

    /**
     * Calculates the remaining health of the player after taking damage based on the opposing power.
     *
     * @param OpositePower the opposing entity's attack power
     * @return the remaining health of the player
     * @throws InvalidElementException if the opposing power is less than or equals to zero
     */
    @Override
    public int calculateHealth(int OpositePower) throws InvalidElementException {
        if (OpositePower <= 0) {
            throw new InvalidElementException("The oposite power is invalid");
        }

        return power - OpositePower;
    }

    /**
     * User a recovery item from the backpack to resource health.
     *
     * @throws EmptyCollectionException if the backpack is empty and there are no recovery items available.
     */
    public void useRecoveryItem() throws EmptyCollectionException {
        if (backpack.isEmpty()) {
            throw new EmptyCollectionException("There's no items in backpack.");
        }
        Item item = backpack.pop();

        this.setHealth(this.health += item.getPoints());

        usedItem = true;
    }

    /**
     * Adds an item to the player's backpack.
     *
     * @param item the item to be added
     */
    public void addItemToBackpack(Item item) {
        backpack.push(item);
    }

    /**
     * Moves the player to a specified division.
     *
     * @param newDivision the division to which the player will move
     */
    public void move(Division newDivision) {

        if (newDivision != null) {
            this.setDivision(newDivision);
        }

    }

    /**
     * Captures a target and associates it with the player.
     *
     * @param target the target to be captured
     */
    public void catchTarget(Target target) {
        target.secure();
        this.target = target;
    }

    /**
     * Returns the current target associated with the player.
     *
     * @return the target entity
     */
    public Target getTarget() {
        return this.target;
    }

    /**
     * Checks if the player is still alive by verifying their health status.
     *
     * @return true if the player's health is greater than zero, false otherwise
     */
    public boolean isAlive() {
        return this.health > 0;
    }

    /**
     * Resets the 'usedItem' flag for occasional item interactions.
     */
    public void resetOcasionalVariables() {
        this.usedItem = false;
    }

    /**
     * Checks if the player has used an item during gameplay interactions.
     *
     * @return true if an item has been used, false otherwise
     */
    public boolean hasUsedItem() {
        return usedItem;
    }

    /**
     * Reduces the player's health by the specified damage value.
     *
     * @param damage the amount of damage to inflict on the player
     */
    @Override
    public void takeDamage(int damage) {
        this.health -= damage;
    }

    /**
     * Equips a bulletproof vest to increase the player's health.
     *
     * @param item the bulletproof vest
     */
    public void dressBulletProffVest(BulletProofVest item) {
        this.health += item.getPoints();
    }

    /**
     * Resets the player's state to the initial default values.
     */
    public void reset() {
        this.target = null;
        this.power = DEFAULT_POWER;
        this.division = null;
        this.backpack = new ArrayStack<>();
        this.health = DEFAULT_HEALTH;
    }

    /**
     * Checks if the player is inside the building.
     *
     * @return true if inside, false otherwise
     */
    public boolean isInTheBuilding() {
        return inTheBuilding;
    }

    /**
     * Sets whether the player is inside the building.
     *
     * @param inTheBuilding true if inside, false otherwise
     */
    public void setInTheBuilding(boolean inTheBuilding) {
        this.inTheBuilding = inTheBuilding;
    }

    /**
     * Checks if the player has an active target.
     *
     * @return true if the player has a target, false otherwise
     */
    public boolean hasTarget() {
        return this.target != null;
    }
}
