package Game.Menu;

import Game.Entitys.Enemy;
import Game.Entitys.Player;

public final class PrintLines {

    public void scenario1() {
        System.out.println("Scenario 1 - Watch out! Clash of enemies!");
    }

    public void scenario2() {
        System.out.println("Scenario 2 - No enemies is this division.");
    }

    public void scenario3() {
        System.out.println("Scenario 3 - Watch Out: Enemies arrived to your current divisionr!");
    }

    public void scenario4(Player player) {
        System.out.println("Scenario 4 - " + player.getName() + " used recovery items.");
    }

    public void scenario5(Player player) {
        System.out.println("Scenario 5 - " + player.getName() + " finds the target, but there are enemies in the division.");
    }

    public void scenario6(Player player) {
        System.out.println("Scenario 6 - " + player.getName() + " finds the target without enemies in the division.");
    }

    public void withoutEnemies() {
        System.out.println("Without enemies in this room");
    }

    public void interactedTarget() {
        System.out.println("Interacted target");
    }

    public void notInteractedTarget() {
        System.out.println(" Not Interacted target");
    }

    public void success() {
        System.out.println("Mission sucessful");
    }

    public void invalidScenario() {
        System.out.println("Invalid scenario");
    }

    public void enemyDefeated(Enemy enemy) {
        System.out.println(enemy.getName() + " was defeated");
    }

    public void playerDamage(Player player, int totalDamage) {
        System.out.println(player.getName() + " received " + totalDamage + " points of damage");
    }

    public void danger(Player player) {
        System.out.println( player.getName() + " is in danger");
    }

    public void attackEnemy(Player player, Enemy enemy){
        System.out.println(player.getName() + " attacked " + enemy.getName() + " (Health:" + enemy.getHealth() + ")");
    }

    public void options(){
        System.out.println("Options: [1 - Attack | 2 - Use recovery item]");
    }

    public void optionLeave(){
        System.out.println("Leave the building? [1 - Yes | 2 - No]");
    }

    public void pathNotFound() {
        System.out.println("Path not found!");
    }

    public void calculatePath() {
        System.out.println("Calculating path!");
    }

    public void printLine() {
        System.out.println("\n");
    }

    public void targetSecured(Player player) {
        System.out.println(player.getName() + " secured the target!");
    }

    public void recoveryItemAdded(Player player) {
        System.out.println(player.getName() + " catched a recovery item!");
    }

    public void dressBullterProffVest(Player player) {
        System.out.println(player.getName() + " dressed a bullet proof vest!");
    }

    public void playerLeftBuilding(Player player) {
        System.out.println(player.getName() + " left the building.");
    }
}
