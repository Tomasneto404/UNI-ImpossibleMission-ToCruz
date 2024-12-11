package Game.Simulations;

import ExceptionsAulas.EmptyCollectionException;

import java.util.Scanner;

import static java.lang.System.exit;

public class ManualSimulation implements Simulation {

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
                    //play();
                    break;

                case "2":
                    //System.out.println(map.toString());
                    break;

                case "3":
                    /*try {
                        //showShortestPath();
                    } catch (EmptyCollectionException e) {
                        System.out.println(e.getMessage());
                    }
                    
                     */

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
}
