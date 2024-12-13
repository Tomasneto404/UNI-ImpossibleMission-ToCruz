package Game.Menu;

import Game.Entitys.Enemy;
import Game.Entitys.Player;

/**
 * The PrintLines class provides a collection of utility methods to print
 * predefined messages.
 *
 * @author Tânia Morais
 * @author Tomás Neto
 */
public final class PrintLines {

    /**
     * Prints a message indicating the presence of enemies and a clash scenario.
     */
    public void scenario1() {
        System.out.println("Scenario 1 - Watch out! Clash of enemies!");
    }

    /**
     * Prints a message indicating no enemies are in the current division.
     */
    public void scenario2() {
        System.out.println("Scenario 2 - No enemies is this division.");
    }

    /**
     * Prints a message indicating that enemies have arrived in the player's current division.
     */
    public void scenario3() {
        System.out.println("Scenario 3 - Watch Out: Enemies arrived to your current divisionr!");
    }

    /**
     * Prints a message indicating the player has used recovery items.
     *
     * @param player the player who used the recovery item
     */
    public void scenario4(Player player) {
        System.out.println("Scenario 4 - " + player.getName() + " used recovery items.");
    }

    /**
     * Prints a message indicating the player has found the target but there are enemies nearby.
     *
     * @param player the player who found the target
     */
    public void scenario5(Player player) {
        System.out.println("Scenario 5 - " + player.getName() + " finds the target, but there are enemies in the division.");
    }

    /**
     * Prints a message indicating the player has found the target without enemies nearby.
     *
     * @param player the player who found the target
     */
    public void scenario6(Player player) {
        System.out.println("Scenario 6 - " + player.getName() + " finds the target without enemies in the division.");
    }

    /**
     * Prints a message indicating there are no enemies in the room.
     */
    public void withoutEnemies() {
        System.out.println("Without enemies in this room");
    }

    /**
     * Prints a message indicating the player has interacted with the target.
     */
    public void interactedTarget() {
        System.out.println("Interacted target");
    }

    /**
     * Prints a message indicating the player has not interacted with the target.
     */
    public void notInteractedTarget() {
        System.out.println(" Not Interacted target");
    }

    /**
     * Prints a success message for completing the mission.
     */
    public void success() {
        System.out.println("Mission sucessful");
    }

    /**
     * Prints a message indicating an invalid scenario.
     */
    public void invalidScenario() {
        System.out.println("Invalid scenario");
    }

    /**
     * Prints a message indicating an enemy has been defeated.
     *
     * @param enemy the enemy that was defeated
     */
    public void enemyDefeated(Enemy enemy) {
        System.out.println(enemy.getName() + " was defeated");
    }

    /**
     * Prints a message indicating the player has taken damage.
     *
     * @param player      the player who received damage
     * @param totalDamage the amount of damage taken
     */
    public void playerDamage(Player player, int totalDamage) {
        System.out.println(player.getName() + " received " + totalDamage + " points of damage");
    }

    /**
     * Prints a message indicating the player is in danger.
     *
     * @param player the player in danger
     */
    public void danger(Player player) {
        System.out.println(player.getName() + " is in danger");
    }

    /**
     * Prints a message indicating the player has attacked an enemy.
     *
     * @param player the player attacking
     * @param enemy  the enemy being attacked
     */
    public void attackEnemy(Player player, Enemy enemy) {
        System.out.println(player.getName() + " attacked " + enemy.getName() + " (Health:" + enemy.getHealth() + ")");
    }

    /**
     * Prints the available action options for the player.
     */
    public void options() {
        System.out.println("Options: [1 - Attack | 2 - Use recovery item]");
    }

    /**
     * Prints the option to leave the building.
     */
    public void optionLeave() {
        System.out.println("Leave the building? [1 - Yes | 2 - No]");
    }

    /**
     * Prints a message indicating that the path was not found.
     */
    public void pathNotFound() {
        System.out.println("Path not found!");
    }

    /**
     * Prints a message indicating that the path is being calculated.
     */
    public void calculatePath() {
        System.out.println("Calculating path!");
    }

    /**
     * Prints a blank line.
     */
    public void printLine() {
        System.out.println("\n");
    }

    /**
     * Prints a message indicating the player has secured the target.
     *
     * @param player the player who secured the target
     */
    public void targetSecured(Player player) {
        System.out.println(player.getName() + " secured the target!");
    }

    /**
     * Prints a message indicating the player has collected a recovery item.
     *
     * @param player the player who collected the recovery item
     */
    public void recoveryItemAdded(Player player) {
        System.out.println(player.getName() + " catched a recovery item!");
    }

    /**
     * Prints a message indicating the player has equipped a bulletproof vest.
     *
     * @param player the player equipping the vest
     */
    public void dressBullterProffVest(Player player) {
        System.out.println(player.getName() + " dressed a bullet proof vest!");
    }

    /**
     * Prints a message indicating the player has left the building.
     *
     * @param player the player leaving the building
     */
    public void playerLeftBuilding(Player player) {
        System.out.println(player.getName() + " left the building.");
    }
}
