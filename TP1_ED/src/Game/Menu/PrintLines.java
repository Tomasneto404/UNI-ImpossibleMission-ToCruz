package Game.Menu;

import Game.Entitys.Enemy;

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

    public void scenario4() {
        System.out.println("Scenario 4 - Tó cruz used recovery items.");
    }

    public void scenario5() {
        System.out.println("Scenario 5 - Tó Cruz finds the target, but there are enemies in the division.");
    }

    public void scenario6() {
        System.out.println("Scenario 6 - Tó Cruz finds the target without enemies in the division.");
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

    public void playerDamage(int totalDamage) {
        System.out.println("Tó Cruz received " + totalDamage + "  points of damage");
    }

    public void danger() {
        System.out.println("Tó cruz is in danger");
    }

    public void attackEnemy(Enemy enemy){
        System.out.println("Tó Cruz attacked " + enemy.getName());
    }

    public void options(){
        System.out.println("Options: [1 - Attack | 2 - Use recovery item]");
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
}
