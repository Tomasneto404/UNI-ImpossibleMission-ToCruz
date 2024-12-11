package Game;

import ClassesAulas.ArrayUnorderedList;
import ExceptionsAulas.EmptyCollectionException;
import Game.Entitys.Player;
import Game.Exceptions.LeaveGameException;
import Game.Mission.Division;
import Game.Mission.Map;
import Game.Mission.Mission;

import java.util.Scanner;

public class Game {

    private Player player;
    private Map<Division> map;
    private Mission mission;


    public Game(Mission mission) {
        this.mission = mission;
    }

    public Game(Player player, String filename) {
        this.player = player;
        this.mission = new Mission(filename);
        this.map = mission.getMap();
    }

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

    public void displayImportantInfo(){
        System.out.println("[HP: " + player.getHealth() + "| Pwr: " + player.getPower() + "]");
    }

    public void showMap(){
        System.out.println(map.toString());
    }

    public Player getPlayer() {
        return this.player;
    }

    public Map<Division> getMap() {
        return this.map;
    }

    public Mission getMission() {
        return this.mission;
    }
}
