package Game.Items;

import Game.Mission.Division;

/**
 * The BulletProofVest class represents a protective item that can be used
 * by a player to increase their health. It extends the Item class and
 * has predefined properties such as its name and the division where it is located.
 * @author Tânia Morais
 * @author Tomás Neto
 */
public class BulletProofVest extends Item {

    /**
     * Constructs a BulletProofVest with the specified division and health points.
     *
     * @param division the division where the bulletproof vest is located
     * @param points   the amount of health points the vest provides
     */
    public BulletProofVest(Division division, int points) {
        super(division, points, "Bullet Proof Vest");
    }

}
