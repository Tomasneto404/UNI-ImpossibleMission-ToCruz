package Game.Items;

import Game.Mission.Division;

/**
 * The Item class represents an abstract base class for items in the game.
 * Each item has a name, a division where it is located, and an associated
 * points value.
 *
 * @author Tânia Morais
 * @author Tomás Neto
 */
public abstract class Item {

    private Division division;
    private int points;
    private String name;

    /**
     * Constructs an Item with the specified division, points, and name.
     *
     * @param division the division where the item is located
     * @param points   the points associated with the item (e.g., health points)
     * @param name     the name of the item
     */
    public Item(Division division, int points, String name) {
        this.division = division;
        this.points = points;
        this.name = name;
    }

    /**
     * Gets the division where the item is located.
     *
     * @return the item's division
     */
    public Division getDivision() {
        return division;
    }

    /**
     * Sets the division where the item is located.
     *
     * @param division the new division for the item
     */
    public void setDivision(Division division) {
        this.division = division;
    }

    /**
     * Gets the points associated with the item.
     *
     * @return the item's points
     */
    public int getPoints() {
        return points;
    }

    /**
     * Sets the points associated with the item.
     *
     * @param points the new points for the item
     */
    public void setPoints(int points) {
        this.points = points;
    }

    /**
     * Gets the name of the item.
     *
     * @return the item's name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the item.
     *
     * @param name the new name for the item
     */
    public void setName(String name) {
        this.name = name;
    }
}
