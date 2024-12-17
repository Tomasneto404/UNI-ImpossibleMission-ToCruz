package Game;

import ClassesAulas.ArrayUnorderedList;
import ExceptionsAulas.EmptyCollectionException;
import Game.Entitys.Player;
import Game.Exceptions.LeaveGameException;
import Game.Menu.PrintLines;
import Game.Mission.Division;
import Game.Mission.Map;
import Game.Mission.Mission;
import Game.Mission.Target;

import java.util.Scanner;

/**
 * The main class representing the game.
 *
 * @author Tânia Morais
 * @author Tomás Neto
 */
public class Game {

    private Player player;
    private Map<Division> map;
    private Mission mission;
    private ArrayUnorderedList<Division> listDivisionsWithMoreThanOneItem;

    /**
     * Constructs a Game object with a given mission.
     *
     * @param mission The mission containing information about the map, divisions, and objectives.
     */
    public Game(Mission mission) {
        this.mission = mission;
    }

    /**
     * Constructs a Game object with a player and a mission file.
     *
     * @param player   The `Player` object representing the player.
     * @param filename The filename containing the mission data.
     */
    public Game(Player player, String filename) {
        this.player = player;
        this.mission = new Mission(filename);
        this.map = mission.getMap();
        this.listDivisionsWithMoreThanOneItem = new ArrayUnorderedList<>();
    }

    /**
     * Allows the player to select an entrance or exit division.
     * Displays a list of entrance/exit divisions on the console and allows the player to select one.
     *
     * @return The selected Division object representing the chosen entrance or exit.
     * @throws LeaveGameException If the player chooses to exit the game.
     */
    public Division selectEntrancesExits() {
        ArrayUnorderedList<Division> ee = map.getDivisions();
        Scanner scanner = new Scanner(System.in);
        String command;

        do {
            System.out.println("=== Choose Division to start ===");
            for (Division division : ee) {

                if (division.isEntranceExit()) {
                    System.out.println(division.toString());
                }

            }

            System.out.println("0. Exit");
            System.out.print("> ");
            command = scanner.nextLine();

            Division tmpDivision = new Division(command);
            Division startDivision = verifyDivision(tmpDivision, ee);

            if (startDivision != null) {
                return startDivision;
            } else if (command.equals("0")) {
                throw new LeaveGameException("Leaving ...");
            } else {
                System.out.println("Invalid division name.");
            }

        } while (!ee.isEmpty());

        return null;
    }


    /**
     * Verifies if a specified division exists in the list of divisions.
     *
     * @param divisionToCompare The division to search for in the list.
     * @param divisions         The list of divisions to be checked.
     * @return The Division object if it matches an existing division, otherwise null.
     */
    private Division verifyDivision(Division divisionToCompare, ArrayUnorderedList<Division> divisions) {
        if (divisionToCompare != null && divisions != null) {

            if (divisions.contains(divisionToCompare)) {
                try {
                    return divisions.remove(divisionToCompare);
                } catch (EmptyCollectionException e) {
                    e.printStackTrace();
                }
            }

        }
        return null;
    }

    /**
     * Allows the player to select a new division adjacent to the current one.
     * Displays adjacent divisions and enables manual traversal to another division.
     *
     * @param oldDivision The current division the player is located in.
     * @return The newly selected `Division` object.
     * @throws LeaveGameException If the player chooses to exit the game.
     */
    public Division selectNewDivision(Division oldDivision) {

        ArrayUnorderedList<Division> adjDivisions = map.getAdjacentDivisions(oldDivision);

        Scanner scanner = new Scanner(System.in);
        String command;

        do {
            System.out.println("=== Choose Division New Division ===");
            for (Division division : adjDivisions) {
                System.out.println(division.toString());
            }
            System.out.println("0. Exit");
            System.out.print("> ");
            command = scanner.nextLine();

            Division tmpDivision = new Division(command);
            Division newDivision = verifyDivision(tmpDivision, adjDivisions);

            if (newDivision != null) {
                return newDivision;
            } else if (command.equals("0")) {
                throw new LeaveGameException("Leaving ...");
            } else {
                System.out.println("Invalid division name.");
            }

        } while (!adjDivisions.isEmpty());

        return null;
    }

    /**
     * Displays essential player information, including health and power stats.
     */
    public void displayImportantInfo() {
        System.out.println("[HP: " + player.getHealth() + "| Pwr: " + player.getPower() + "]");
    }

    /**
     * Checks if the player is inside a building and interacts accordingly.
     * Offers an option to leave the building and updates the player's status.
     *
     * @return true if the player is still inside the building, otherwise false.
     */
    public boolean playerInsideBuilding() {

        if (player.getDivision().isEntranceExit()) {

            PrintLines print = new PrintLines();
            Scanner scanner = new Scanner(System.in);
            String option;

            print.optionLeave();
            option = scanner.nextLine();

            if (option.equals("1")) {
                player.setInTheBuilding(false);
            }

        }

        return player.isInTheBuilding();
    }

    /**
     * Displays the current game map to the console.
     */
    public void showMap() {
        System.out.println(map.toString());
    }

    /**
     * Returns the Player object representing the current player in the game.
     *
     * @return The Player instance.
     */
    public Player getPlayer() {
        return this.player;
    }

    /**
     * Returns the Map object representing the game map.
     *
     * @return The Map containing divisions and related game locations.
     */
    public Map<Division> getMap() {
        return this.map;
    }

    /**
     * Returns the current mission object containing objectives and divisions.
     *
     * @return The Mission instance containing map and mission details.
     */
    public Mission getMission() {
        return this.mission;
    }

    /**
     * Checks if the mission was successfully completed.
     * The mission is successful if the player is not in the building and the target division is secured.
     *
     * @return true if the mission objectives have been met, otherwise false.
     */
    public boolean wasMissionSuccessfull() {

        boolean success = false;
        Target target = player.getTarget();

        if (!player.isInTheBuilding() && target != null && target.isSecured()) {

            success = true;

        }

        return success;
    }

    /**
     * Reloads the game map and mission data.
     * Updates the map to reflect the current mission state and divisions.
     */
    public void reloadData() {
        map = mission.getMap();
    }

    /**
     * Displays the shortest path to the target division on the console.
     * Provides a visual representation of the traversal path through the divisions to the target.
     *
     * @param startDivision The starting division from which the path to the target is calculated.
     */
    public void showShortestPathToTarget(Division startDivision) {

        Division targetDivision = map.getTarget().getDivision();
        ArrayUnorderedList<Division> pathToTarget = map.findShortestPath(startDivision, targetDivision);

        System.out.println("\n+SHORTEST PATH TO TARGET+");
        for (Division division : pathToTarget) {
            if (division != null) {

                if (division.hasTarget()) {
                    System.out.print("[" + division.toString() + "]");
                } else {
                    System.out.print("[" + division.toString() + "] -> ");
                }

            }
        }
        System.out.println("\n");

    }

    /**
     * Displays the shortest path back to the entrance division on the console.
     * Provides a visual representation of the traversal path back to the game's starting point.
     *
     * @param currentDivision The division currently traversed by the player.
     * @param startDivision   The starting division where the game began.
     */
    public void showShortestPathToExit(Division currentDivision, Division startDivision) {
        ArrayUnorderedList<Division> pathToExit = map.findShortestPath(currentDivision, startDivision);

        System.out.println("\n+SHORTEST PATH TO EXIT+");
        for (Division division : pathToExit) {
            if (division != null) {

                if (division.hasTarget()) {
                    System.out.print("[" + division.toString() + "]");
                } else {
                    System.out.print(" -> [" + division.toString() + "]");
                }

            }
        }
        System.out.println("\n");
    }

    public void showDivisionsWithMoreThanOneItem(){
        ArrayUnorderedList<Division> divisions = mission.getDivisionsMoreThanOneItem();

        for (Division division : divisions) {
            System.out.println(division.dataToString());
        }
    }
}
