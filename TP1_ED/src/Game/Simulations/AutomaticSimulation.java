package Game.Simulations;

import ClassesAulas.ArrayUnorderedList;
import ExceptionsAulas.EmptyCollectionException;
import ExceptionsAulas.InvalidElementException;
import Game.Entitys.Enemy;
import Game.Entitys.Player;
import Game.Interfaces.Simulation;
import Game.Menu.PrintLines;
import Game.Mission.Division;
import Game.Mission.Map;
import Game.Mission.Mission;

public class AutomaticSimulation implements Simulation {
    private Map<Division> map;
    private Player player;
    private Mission mission;


    public AutomaticSimulation(Player player, String filename) {
        this.player = player;
        this.mission = new Mission(filename);
        this.map = mission.getMap();
    }

    @Override
    public void start() {
        PrintLines print = new PrintLines();

        Division source = player.getDivision();
        Division destination = player.getTarget().getDivision();


        try {
            ArrayUnorderedList<Division> path = map.findShortestPath(source, destination);

            if (path.isEmpty()) {
                print.pathNotFound();
            }

            print.calculatePath();

            for (Division division : path) {
                System.out.println(division.getName() + "->");
            }

            print.printLine();

            for (Division division : path) {
                System.out.println("Move to " + division.getName());

                player.move(division);

                if (division.hasEnemies()) {
                    if (!handleEnemies(division)) {

                    }
                }

            }

        } catch (EmptyCollectionException | InvalidElementException e) {
            e.printStackTrace();
        }


    }

    private boolean handleEnemies(Division division) throws InvalidElementException {
        ArrayUnorderedList<Enemy> enemies = division.getEnemies();
        PrintLines print = new PrintLines();

        int totalEnemyPower = 0;

        for (Enemy enemy : enemies) {
            System.out.println("Confront " + enemy.getName());
            enemy.takeDamage(player.getHealth());

            if (enemy.isAlive()) {
                totalEnemyPower += enemy.getPower();
            }
        }

        player.takeDamage(totalEnemyPower);

        if (player.isAlive()) {
            print.danger();
            return false;
        }
        return true;
    }
}