package Game.Simulations;

import ClassesAulas.ArrayUnorderedList;
import Game.Interfaces.Simulation;
import Game.Game;
import Game.Mission.Division;
import Game.Mission.Map;

public class AutomaticSimulation implements Simulation {

    private Game game;
    private ArrayUnorderedList<Division> pathToExport;

    public AutomaticSimulation(Game game) {
        this.game = game;
    }

    @Override
    public void start() {
        Map<Division> map = game.getMap();

        Division startDivision = game.selectEntrancesExits();
        Division endDivision = map.getTarget().getDivision();

        ArrayUnorderedList<Division> divisions = map.findShortestPath(startDivision, endDivision);

        pathToExport = new ArrayUnorderedList<>();

        for (Division division : divisions) {
            if (division != null) {

                if (division.hasTarget()) {
                    System.out.print("[" + division.toString() + "]");
                } else {
                    System.out.print("[" + division.toString() + "] -> ");
                }

                pathToExport.addToRear(division);

            }
        }
        exportData();
    }

    @Override
    public void exportData() {

    }

}