package Game;

import ClassesAulas.ArrayUnorderedList;
import ExceptionsAulas.EmptyCollectionException;
import Game.Entitys.Player;
import Game.Exceptions.DeadPlayerException;
import Game.Exceptions.EnemiesStillAliveException;
import Game.Exceptions.LeaveGameException;
import Game.Mission.Division;
import Game.Mission.Map;
import Game.Mission.Mission;
import Game.Mission.Scenario;

import java.util.Scanner;

import static java.lang.System.exit;

public class Game {

    private Player player;
    private Map<Division> map;
    private Mission mission;


    public Game(Player player, String filename) {
        this.player = player;
        this.mission = new Mission(filename);
        this.map = mission.getMap();

    }

    public void start() {

        Scanner scanner = new Scanner(System.in);
        String command;

        System.out.println("\n=== Mission Simulator ===");
        do {
            System.out.println("\nChoose an option:");
            System.out.println("1. Play");
            System.out.println("2. View Map");
            System.out.println("3. Shortest path to Target (Dijkstra)");
            System.out.println("0. Exit");
            System.out.print("> ");

            command = scanner.nextLine();

            switch (command) {
                case "1":
                    play();
                    break;

                case "2":
                    System.out.println(map.toString());
                    break;

                case "3":
                    try {
                        showShortestPath();
                    } catch (EmptyCollectionException e) {
                        System.out.println(e.getMessage());
                    }

                    break;

                case "0":
                    System.out.println("Leaving program...");
                    break;

                default:
                    System.out.println("Invalid option.");
            }

        } while (!command.equals("0"));

    }

    private void stop() {
        //Guardar dados em ficheiro e fechar
        exit(0);
    }

    private void play() {
        Division startDivision = chooseEntrancesExits();
        Boolean canJoinNewDivision = false;

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

                if (!player.isAlive()){
                    throw new DeadPlayerException(player.getName() + " died! Leaving ...");
                }

                if (currentDivision == null) {
                    System.out.println("No division chosen. Exiting...");
                    return;
                }

                System.out.println("You are in <" + currentDivision + ">");
                player.resetOcasionalVariables();
                displayImportantInfo();

                try {
                    scene.executeScenario(player, map);
                } catch (EnemiesStillAliveException | EmptyCollectionException e) {
                    System.out.println(e.getMessage());
                    canJoinNewDivision = false;
                }

                if (canJoinNewDivision) {
                    player.move(chooseNewDivision(currentDivision));
                }

            }
        } catch (LeaveGameException | DeadPlayerException e) {
            System.out.println(e.getMessage());
        }
    }

    private Division chooseEntrancesExits() {
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

    private Division chooseNewDivision(Division oldDivision) {

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

    private void displayImportantInfo(){
        System.out.println("[HP: " + player.getHealth() + "| Pwr: " + player.getPower() + "]");
    }

    public void showShortestPath() throws EmptyCollectionException {
        Division startDivision = chooseEntrancesExits();
        Division endDivision = map.getTarget().getDivision();

        ArrayUnorderedList<Division> divisions = map.findShortestPath(startDivision, endDivision);

        for (Division division : divisions) {
            if (division != null) {
                System.out.println(division.toString());
            }
        }
    }

    public static void main(String[] args) {

        Player player1 = new Player("Tó Cruz");

        Game game = new Game(player1, "file.json");
        game.start();

    }


}
