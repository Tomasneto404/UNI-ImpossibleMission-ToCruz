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

public class AutomaticSimulation implements Simulation {

    private Game game;
    private ArrayUnorderedList<Division> pathToExport;

    public AutomaticSimulation(Game game) {
        this.game = game;
    }

    @Override
    public void start() {

        Player player = game.getPlayer();
        Map<Division> map = game.getMap();
        pathToExport = new ArrayUnorderedList<>();

        Division startDivision = game.selectEntrancesExits();
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


                while (!scenarioCompleted) {
                    try {
                        scene.executeScenario(player, map);
                        scenarioCompleted = true;
                    } catch (EnemiesStillAliveException e) {
                        System.out.println(e.getMessage());
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

    @Override
    public void exportData() {
        JSONExporter exporter = new JSONExporter("automaticSimulation.json");
        exporter.missionPath(pathToExport);
    }

}