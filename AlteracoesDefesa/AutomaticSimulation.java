package Game.Simulations;

import ClassesAulas.ArrayUnorderedList;
import ExceptionsAulas.EmptyCollectionException;
import Game.Entitys.Player;
import Game.Exceptions.DeadPlayerException;
import Game.Exceptions.EnemiesStillAliveException;
import Game.Exceptions.LeaveGameException;
import Game.ImportExport.JSONExporter;
import Game.Interfaces.Simulation;
import Game.Game;
import Game.Mission.Division;
import Game.Mission.Map;
import Game.Mission.Scenario;

/**
 * Represents an automatic simulation of the game where the player automatically navigates through
 * the map to achieve objectives, encounter scenarios, and secure targets.
 * This class implements the Simulation interface.
 *
 * @author Tânia Morais
 * @author Tomás Neto
 */
public class AutomaticSimulation implements Simulation {

    private Game game;
    private ArrayUnorderedList<Division> pathToExport;

    /**
     * Constructs an 'AutomaticSimulation' object associated with the specified game instance.
     *
     * @param game The 'Game' object representing the current game state.
     */
    public AutomaticSimulation(Game game) {
        this.game = game;
    }

    /**
     * Starts the automatic simulation, navigating through the map to achieve objectives.
     * The method traverses the path, encounters scenarios, handles confrontations, and ensures
     * proper handling of enemy encounters and challenges.
     */
    @Override
    public void start() {

        Player player = game.getPlayer();
        Map<Division> map = game.getMap();
        pathToExport = new ArrayUnorderedList<>();

        Division startDivision = getNearestDivision(player.getDivision(), map);
        if (startDivision == null) {
            System.out.println("No starting division chosen. Exiting...");
            return;
        }

        ArrayUnorderedList<Division> divisionsPath = map.findShortestPath(startDivision, map.getTarget().getDivision());

        if (divisionsPath == null) {
            System.out.println("No valid path to the target found.");
            return;
        }

        System.out.println("Starting path traversal...");

        try {
            for (Division division : divisionsPath) {
                player.move(division);
                pathToExport.addToRear(division);

                System.out.println("You are now in division: " + division);

                Scenario scene = new Scenario();

                boolean scenarioCompleted = false;

                // Loop para resolver o cenário até que todos os problemas sejam resolvidos
                while (!scenarioCompleted) {
                    try {
                        scene.executeScenario(player, map);
                        scenarioCompleted = true;  // Caso o cenário seja resolvido com sucesso
                    } catch (EnemiesStillAliveException e) {
                        System.out.println("Enemies still present! Let's try again...");
                    } catch (EmptyCollectionException e) {
                        throw new RuntimeException(e);
                    }
                }

                if (!player.isAlive()) {
                    throw new DeadPlayerException(player.getName() + " died! Game over...");
                }

                game.displayImportantInfo();

                if (division.equals(map.getTarget().getDivision())) {
                    System.out.println("Traget captured successfully in division: " + division);
                }
            }
            //reverse path
            pathToExport.addToRear(new Division("RETURN_PATH_MARKER"));

            ArrayUnorderedList<Division> reversePath = map.findShortestPath(map.getTarget().getDivision(), startDivision);
            if (reversePath == null) {
                System.out.println("No valid path to the target found. Exiting...");
            }
            System.out.println("Starting path back to the entrance ...");

            for (Division division : reversePath) {
                player.move(division);
                pathToExport.addToRear(division);

                System.out.println("Returning to : " + division);

                if (!player.isAlive()) {
                    throw new DeadPlayerException(player.getName() + "died during the scape!");
                }
            }
            System.out.println("Mission accomplished!! ");
        } catch (LeaveGameException | DeadPlayerException e) {
            System.out.println(e.getMessage());
        }

        exportData();
    }

    /**
     * Finds the nearest division to the player's current division on the map.
     *
     * @param currentDivision The Division where the player is currently located.
     * @param map             The Map representing the game map.
     * @return The nearest Division to the current division.
     */
    private Division getNearestDivision(Division currentDivision, Map<Division> map) {
        Division nearestDivision = null;
        int minDistance = Integer.MAX_VALUE;

        ArrayUnorderedList<Division> divisions = map.getDivisions();
        int index = 0;

        for (Division division : map.getDivisions()) {
            if (division != currentDivision) {
                int distance = index++;
                if (distance < minDistance) {
                    minDistance = distance;
                    nearestDivision = division;
                }
            }
        }
        return nearestDivision;
    }

    /**
     * Exports the traversal path data to a JSON file for logging and later analysis.
     */
    @Override
    public void exportData() {
        JSONExporter exporter = new JSONExporter("automaticSimulation.json");
        exporter.missionPath(pathToExport);
    }

}