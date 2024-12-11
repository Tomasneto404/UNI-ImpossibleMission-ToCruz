package Game.Mission;

import ClassesAulas.ArrayUnorderedList;
import ExceptionsAulas.*;
import Game.Entitys.Enemy;
import Game.Entitys.Player;
import Game.Exceptions.EnemiesStillAliveException;
import Game.Menu.PrintLines;

import java.util.Iterator;
import java.util.Scanner;

public class Scenario {

    private int type;

    public Scenario() {

    }

    public Scenario(int type) {
        if (type >= 1 && type <= 6) {
            this.type = type;
        }
    }

    public int getType() {
        return type;
    }

    public void executeScenario(Player player, Map map) throws EmptyCollectionException {
        PrintLines print = new PrintLines();

        int sceneCode = analyzeSituation(player);

        switch (sceneCode) {
            case 1:
                print.scenario1();
                handleConfrontation(player, player.getDivision().getEnemies());
                break;

            case 2:
                print.scenario2();
                map.moveEnemies();
                break;

            case 3:
                print.scenario3();
                handleEnemyEntry(player);
                break;

            case 4:
                print.scenario4(player);
                break;

            case 5:
                print.scenario5(player);
                handleConfrontation(player, player.getDivision().getEnemies());
                break;

            case 6:
                print.scenario6(player);
                player.catchTarget(map.getTarget());
                print.targetSecured(player);
                break;

        }

    }

    private int analyzeSituation(Player player) {

        if (player.getDivision().hasEnemies()) {

            if (player.getDivision().hasTarget()) {
                return 5;
            }

            return 1;

        } else if (!player.getDivision().hasEnemies()) {

            if (player.getDivision().hasTarget()) {
                return 6;
            }

            return 2;

        } else if (player.getDivision().hasNewEnemies()) {

            return 3;

        } else if (player.hasUsedItem()) {

            return 4;

        }

        return 0;
    }

    private void handleConfrontation(Player player, ArrayUnorderedList<Enemy> enemies) throws EnemiesStillAliveException, EmptyCollectionException {
        PrintLines print = new PrintLines();
        int totalDamage = 0;
        int deadEnemiesCounter = 0;
        Scanner scanner = new Scanner(System.in);
        String option;

        if (enemies == null || enemies.isEmpty()) {
            print.withoutEnemies();
            return;
        }

        print.options();
        option = scanner.nextLine();

        switch (option) {
            case "1":
                for (Iterator<Enemy> iterator = enemies.iterator(); iterator.hasNext(); ) {
                    Enemy enemy = iterator.next();

                    enemy.takeDamage(player.getPower());
                    print.attackEnemy(player, enemy);

                    if (!enemy.isAlive()) {
                        iterator.remove();
                        print.enemyDefeated(enemy);
                    } else {
                        totalDamage += enemy.getPower();
                    }
                }
                break;

            case "2":
                player.useRecoveryItem();
                break;

            default:


        }


        if (totalDamage > 0) {
            player.takeDamage(totalDamage);
            print.playerDamage(totalDamage);
        }

        if (deadEnemiesCounter != enemies.size()) {
            throw new EnemiesStillAliveException("=There are still enemies in this division.=");
        }
    }

    private void handleEnemyEntry(Player player) {
        int totalDamage = 0;
        PrintLines print = new PrintLines();
        ArrayUnorderedList<Enemy> enemies = player.getDivision().getEnemies();


        for (Enemy enemy : enemies) {
            totalDamage += enemy.getPower();
        }

        try {

            player.setHealth(player.calculateHealth(totalDamage));
            print.playerDamage(totalDamage);

            if (player.isAlive()) {
                //player ataca ou usa item
            }

        } catch (InvalidElementException e) {
            e.printStackTrace();
        }

    }

    private void handleTargetFind(Player player) throws EmptyCollectionException {


    }
}
