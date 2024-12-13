package Game.Simulations;

import ClassesAulas.ArrayUnorderedList;
import ExceptionsAulas.EmptyCollectionException;
import Game.Entitys.Player;
import Game.Exceptions.*;
import Game.ImportExport.JSONExporter;
import Game.Interfaces.Simulation;
import Game.Game;
import Game.Mission.Division;
import Game.Mission.Map;
import Game.Mission.Scenario;

public class ManualSimulation implements Simulation {

    private Game game;
    private Player player;
    private ArrayUnorderedList<Division> pathToExport;

    public ManualSimulation(Game game) {
        this.game = game;
    }

    public void start() {

        player = game.getPlayer();
        player.reset();
        Map<Division> map = game.getMap();
        pathToExport = new ArrayUnorderedList<>();

        boolean success = false;

        try {
            showSimulationInfo();
            Division startDivision = game.selectEntrancesExits();
            boolean canJoinNewDivision;

            if (startDivision == null) {
                System.out.println("No starting division was chosen. Exiting...");
                return;
            }

            player.move(startDivision);
            player.setInTheBuilding(true);

            while (player.isInTheBuilding()) {

                Scenario scene = new Scenario();
                canJoinNewDivision = true;

                Division currentDivision = player.getDivision();
                pathToExport.addToRear(currentDivision);

                if (!player.isAlive()) {
                    throw new DeadPlayerException(player.getName() + " died! Leaving ...");
                }

                if (currentDivision == null) {
                    System.out.println("No division chosen. Exiting...");
                    return;
                }

                if (game.playerInsideBuilding()) {

                    System.out.println("You are in <" + currentDivision + ">");
                    player.resetOcasionalVariables();
                    game.displayImportantInfo();

                    try {
                        scene.executeScenario(player, map);
                    } catch (EnemiesStillAliveException | EmptyCollectionException | InvalidOptionException |
                             NoItemsInBackpackException | LeaveBuildingException e) {
                        System.out.println(e.getMessage());
                        canJoinNewDivision = false;
                    }

                    if (canJoinNewDivision) {

                        if (!player.hasTarget()){
                            game.showShortestPathToTarget(currentDivision);
                        } else {
                            game.showShortestPathToExit(currentDivision, startDivision);
                        }

                        player.move(game.selectNewDivision(currentDivision));
                    }
                }

                success = game.wasMissionSuccessfull();

            }

        } catch (LeaveGameException | DeadPlayerException | LeaveBuildingException e) {
            System.out.println(e.getMessage());
        }

        if (success) {
            System.out.println("Congratulations! Mission Complete!");
        } else {
            System.out.println("Mission was not complete. Try again.");
        }

        exportData();
    }

    @Override
    public void exportData() {
        JSONExporter exporter = new JSONExporter("manualSimulation.json");
        exporter.missionPath(pathToExport);
    }

    private void showSimulationInfo(){
        System.out.println("*MANUAL SIMULATION*");
        System.out.println("Mission: " + game.getMission().getMissionCode());
        System.out.println("Player: " + game.getPlayer().getName());
        System.out.println("Target division: " + game.getMap().getTarget().getDivision());
        System.out.println();
    }

}
