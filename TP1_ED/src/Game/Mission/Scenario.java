package Game.Mission;

import ClassesAulas.ArrayUnorderedList;
import ExceptionsAulas.*;
import Game.Entitys.Enemy;
import Game.Entitys.Player;
import Game.Exceptions.EnemiesStillAliveException;
import Game.Exceptions.InvalidOptionException;
import Game.Exceptions.NoItemsInBackpackException;
import Game.Items.BulletProofVest;
import Game.Items.Item;
import Game.Items.RecoveryItem;
import Game.Menu.PrintLines;

import java.util.Iterator;
import java.util.Scanner;

/**
 * The 'Scenario' class is responsible for managing and executing different game scenarios.
 *
 * @author Tânia Morais
 * @author Tomás Neto
 */
public class Scenario {

    /**
     * Default constructor for creating a `Scenario` object.
     */
    public Scenario() {
    }

    /**
     * Executes the scenario for the player based on their current situation in the game map.
     *
     * @param player The `Player` object representing the player in the game.
     * @param map    The `Map` object representing the divisions and their connections.
     * @throws EmptyCollectionException If there are issues accessing a collection.
     */
    public void executeScenario(Player player, Map<Division> map) throws EmptyCollectionException {
        PrintLines print = new PrintLines();

        handleItems(player);

        int sceneCode = analyzeSituation(player);

        switch (sceneCode) {
            case 1:
                print.scenario1();
                handleConfrontation(player, player.getDivision().getEnemies());
                map.moveEnemies();
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

    /**
     * Analyzes the player's current situation in their division and determines the scenario code.
     *
     * @param player The `Player` object representing the player.
     * @return An integer representing the scenario code.
     */
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

    /**
     * Handles confrontations with enemies in a division by managing player attacks and recovery options.
     *
     * @param player  The `Player` object representing the player.
     * @param enemies The `ArrayUnorderedList` of enemies present in the division.
     * @throws EnemiesStillAliveException If enemies remain after a confrontation.
     * @throws NoItemsInBackpackException If recovery items are not present in the player's backpack.
     */
    private void handleConfrontation(Player player, ArrayUnorderedList<Enemy> enemies) throws EnemiesStillAliveException, NoItemsInBackpackException {
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
                try {
                    player.useRecoveryItem();
                } catch (EmptyCollectionException e) {
                    throw new NoItemsInBackpackException(e.getMessage());
                }
                break;

            default:
                throw new InvalidOptionException("Invalid option.");
        }


        if (totalDamage > 0) {
            player.takeDamage(totalDamage);
            print.playerDamage(player, totalDamage);
        }

        if (deadEnemiesCounter != enemies.size()) {
            throw new EnemiesStillAliveException("=There are still enemies in this division.=");
        }

    }

    /**
     * Handles enemy entry into the division, calculating and applying the damage to the player.
     *
     * @param player The `Player` object representing the player.
     */
    private void handleEnemyEntry(Player player) {
        int totalDamage = 0;
        PrintLines print = new PrintLines();
        ArrayUnorderedList<Enemy> enemies = player.getDivision().getEnemies();

        for (Enemy enemy : enemies) {
            totalDamage += enemy.getPower();
        }

        player.takeDamage(totalDamage);
        print.playerDamage(player, totalDamage);

        if (player.isAlive()) {

            Scanner scanner = new Scanner(System.in);
            String option;

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
                    try {
                        player.useRecoveryItem();
                    } catch (EmptyCollectionException e) {
                        throw new NoItemsInBackpackException(e.getMessage());
                    }
                    break;

                default:
                    throw new InvalidOptionException("Invalid option.");
            }
        }

    }

    /**
     * Handles item interactions by allowing the player to acquire and use items in the current division.
     *
     * @param player The `Player` object representing the player.
     */
    private void handleItems(Player player) {

        PrintLines printer = new PrintLines();

        Division currentDivision = player.getDivision();

        if (currentDivision.hasItems()) {

            ArrayUnorderedList<Item> items = currentDivision.getItems();

            for (Iterator<Item> iterator = items.iterator(); iterator.hasNext(); ) {
                Item item = iterator.next();

                if (item instanceof RecoveryItem) {

                    player.addItemToBackpack(item);
                    printer.recoveryItemAdded(player);

                } else if (item instanceof BulletProofVest) {

                    player.dressBulletProffVest((BulletProofVest) item);
                    printer.dressBullterProffVest(player);

                }

                iterator.remove();
            }
        }

    }

}
