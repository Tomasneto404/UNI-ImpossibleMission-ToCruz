package Game.Mission;

import ClassesAulas.ArrayUnorderedList;
import ExceptionsAulas.EmptyCollectionException;
import Game.Entitys.Enemy;
import Game.Items.Item;

/**
 * The Division class represents a division within the game.
 *
 * @author Tânia Morais
 * @author Tomás Neto
 */
public class Division {

    private String name;
    private ArrayUnorderedList<Enemy> enemies;
    private ArrayUnorderedList<Item> items;
    private boolean hasTarget;
    private boolean hasNewEnemies;
    private boolean isEntranceExit;


    {
        this.hasNewEnemies = false;
        this.hasTarget = false;
        this.isEntranceExit = false;
    }

    /**
     * Default constructor.
     */
    public Division() {
    }

    /**
     * Constructs a Division with the specified name.
     *
     * @param name the name of the division
     */
    public Division(String name) {
        this.name = name;
    }

    /**
     * Gets the name of the division.
     *
     * @return the division's name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the division.
     *
     * @param name the new name for the division
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Checks if the division currently has a target.
     *
     * @return true if there is a target, false otherwise
     */
    public boolean hasTarget() {
        return hasTarget;
    }

    /**
     * Sets the target status of the division.
     *
     * @param hasTarget true if a target is present, false otherwise
     */
    public void setHasTarget(boolean hasTarget) {
        this.hasTarget = hasTarget;
    }

    /**
     * Gets the list of enemies in the division.
     *
     * @return the list of enemies in the division
     */
    public ArrayUnorderedList<Enemy> getEnemies() {
        return enemies;
    }

    /**
     * Sets the list of enemies in the division.
     *
     * @param enemies the list of enemies
     */
    public void setEnemies(ArrayUnorderedList<Enemy> enemies) {
        this.enemies = enemies;
    }

    /**
     * Sets new enemies in the division and marks that new enemies have arrived.
     *
     * @param enemies the new enemies to be added to the division
     */
    public void setNewEnemies(ArrayUnorderedList<Enemy> enemies) {
        this.hasNewEnemies = true;
        this.enemies = enemies;
    }

    /**
     * Checks if new enemies have appeared in the division.
     *
     * @return true if new enemies are present, false otherwise
     */
    public boolean hasNewEnemies() {
        return hasNewEnemies;
    }

    /**
     * Checks if there are enemies in the division.
     *
     * @return true if enemies are present, false otherwise
     */
    public boolean hasEnemies() {
        return enemies != null && !enemies.isEmpty();
    }

    /**
     * Checks if there are items present in the division.
     *
     * @return true if items are present, false otherwise
     */
    public boolean hasItems() {
        return items != null && !items.isEmpty();
    }

    /**
     * Sets the status indicating the presence of new enemies.
     *
     * @param hasNewEnemies true if new enemies have appeared, false otherwise
     */
    public void setHasNewEnemies(boolean hasNewEnemies) {
        this.hasNewEnemies = hasNewEnemies;
    }

    /**
     * Checks if the division acts as an entrance or exit point in the game map.
     *
     * @return true if it's an entrance/exit, false otherwise
     */
    public boolean isEntranceExit() {
        return isEntranceExit;
    }

    /**
     * Sets the status indicating the division is an entrance or exit point.
     *
     * @param entranceExit true if it's an entrance/exit, false otherwise
     */
    public void setEntranceExit(boolean entranceExit) {
        isEntranceExit = entranceExit;
    }

    /**
     * Gets the list of items in the division.
     *
     * @return the list of items present in the division
     */
    public ArrayUnorderedList<Item> getItems() {
        return items;
    }

    /**
     * Sets the list of items in the division.
     *
     * @param items the new collection of items
     */
    public void setItems(ArrayUnorderedList<Item> items) {
        this.items = items;
    }

    /**
     * Generates a formatted string representing the division's data.
     *
     * @return a string containing the division's name, enemies, and items
     */
    public String dataToString() {
        String result = "[" + name + "] \n";

        if (hasEnemies()) {
            result += "Enemies: \n";
            for (Enemy enemy : enemies) {
                result += "- " + enemy.getName() + "\n";
            }
        }

        if (hasItems()) {
            result += "Items: \n";
            for (Item item : items) {
                result += "- " + item.getName() + "\n";
            }
        }

        return result;
    }

    /**
     * Provides a string representation of the division's name.
     *
     * @return the division's name
     */
    @Override
    public String toString() {
        return name;
    }

    /**
     * Compares this Division object with another object to determine equality.
     *
     * @param obj the object to compare with
     * @return true if the divisions have the same name, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Division) {
            Division division = (Division) obj;
            return name.equals(division.getName());
        }
        return false;
    }

    public void removeEnemy(Enemy enemy) throws EmptyCollectionException {
        if ( enemies != null && enemies.contains(enemy)){
            enemies.remove(enemy);
        }
    }

    public void addEnemy(Enemy enemy) {
        if (enemies != null) {
            enemies.addToRear(enemy);
        }
    }
}
