package Game;

import ClassesAulas.ArrayUnorderedList;
import ExceptionsAulas.*;
import Game.Exceptions.EnemiesStillAliveException;

import java.util.Iterator;
import java.util.Scanner;

public class Scenario {

    private int type; //identifica o número do cenário, é necessário um menu para isto

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
                print.scenario4();
                break;

            case 5:
                print.scenario5();
                break;

            case 6:
                print.scenario6();
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

    public void executeScenario(Player player, ArrayUnorderedList<Enemy> enemies, Game game) throws InvalidElementException, EmptyCollectionException {
        PrintLines print = new PrintLines();

        switch (type) {
            case 1:
                print.scenario1();

                if (!enemies.isEmpty()) {
                    handleConfrontation(player, enemies);
                } else {
                    print.withoutEnemies();
                }
                break;

            case 2:
                print.scenario2();
                //simulation.moveEnemies(enemies);
                break;

            case 3:
                print.scenario3();
                handleEnemyEntry(player);
                break;

            case 4:
                print.scenario4();
                player.useRecoveryItem();
                break;

            case 5:
                print.scenario5();
                handleConfrontation(player, enemies);

                if (player.interactWithTarget(player.getTarget())) {
                    print.interactedTarget();
                } else {
                    print.notInteractedTarget();
                }
                break;

            case 6:
                print.scenario6();

                if (player.interactWithTarget(player.getTarget())) {
                    print.success();
                } else {
                    print.notInteractedTarget();
                }
                break;
        }
    }

    public void handleConfrontation(Player player, ArrayUnorderedList<Enemy> enemies) throws EnemiesStillAliveException, EmptyCollectionException {
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
                    print.attackEnemy(enemy);

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


    public void handleEnemyEntry(Player player) {
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
}
