package Game.Items;

import Game.Mission.Division;

/**
 * The RecoveryItem class represents an item used to restore health points
 * to a player. It extends the Item class and has predefined properties such as
 * its name and the division where it is located.
 *
 * @author Tânia Morais
 * @author Tomás Neto
 */
public class RecoveryItem extends Item {

    /**
     * Constructs a RecoveryItem with the specified division and health points.
     *
     * @param division the division where the recovery item is located
     * @param points   the amount of health points the item restores
     */
    public RecoveryItem(Division division, int points) {
        super(division, points, "Recovery Item");
    }

}
