package Game.Simulations;

import ClassesAulas.ArrayUnorderedList;
import ExceptionsAulas.EmptyCollectionException;
import Game.Entitys.Player;
import Game.Exceptions.DeadPlayerException;
import Game.Exceptions.EnemiesStillAliveException;
import Game.Exceptions.LeaveGameException;
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
        Map<Division> map = game.getMap();
        pathToExport = new ArrayUnorderedList<>();

        Division startDivision = game.selectEntrancesExits();
        boolean canJoinNewDivision = false;

        if (startDivision == null) {
            System.out.println("No starting division chosen. Exiting...");
            return;
        }

        player.move(startDivision);

        try {
            while (!player.isMissionSuccessful()) {
                Scenario scene = new Scenario();
                canJoinNewDivision = true;

                Division currentDivision = player.getDivision();
                pathToExport.addToRear(currentDivision);

                if (!player.isAlive()){
                    throw new DeadPlayerException(player.getName() + " died! Leaving ...");
                }

                if (currentDivision == null) {
                    System.out.println("No division chosen. Exiting...");
                    return;
                }

                System.out.println("You are in <" + currentDivision + ">");
                player.resetOcasionalVariables();
                game.displayImportantInfo();

                try {
                    scene.executeScenario(player, map);
                } catch (EnemiesStillAliveException | EmptyCollectionException e) {
                    System.out.println(e.getMessage());
                    canJoinNewDivision = false;
                }

                if (canJoinNewDivision) {
                    player.move(game.selectNewDivision(currentDivision));
                }

            }
        } catch (LeaveGameException | DeadPlayerException e) {
            System.out.println(e.getMessage());
        }
        exportData();
    }

    @Override
    public void exportData() {

    }


}
