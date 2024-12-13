package Game.Menu;


import java.util.Scanner;

import Game.Game;
import Game.Simulations.AutomaticSimulation;
import Game.Simulations.ManualSimulation;
import Game.Interfaces.Simulation;
import Game.Mission.Mission;

/**
 * The Menu class represents the main interface for interacting with the
 * game.
 *
 * @author Tânia Morais
 * @author Tomás Neto
 */
public class Menu {

    private Game game;

    /**
     * Constructs a Menu instance with the specified game instance.
     *
     * @param game the game instance associated with the menu
     */
    public Menu(Game game) {
        this.game = game;
    }

    /**
     * Displays the main menu and handles user input for navigating
     * through the available options. Options include starting a manual
     * or automatic simulation, viewing the map, and exiting the program.
     */
    public void mainMenu() {

        Simulation simulation;

        Scanner scanner = new Scanner(System.in);
        String command;

        System.out.println("\n=== Mission Simulator ===");

        do {
            game.reloadData();

            System.out.println("\nChoose an option:");
            System.out.println("1. Manual Simulation");
            System.out.println("2. Automatic Simulation");
            System.out.println("3. View Map");
            System.out.println("0. Exit");
            System.out.print("> ");

            command = scanner.nextLine();

            switch (command) {
                case "1":
                    simulation = new ManualSimulation(game);
                    simulation.start();
                    break;

                case "2":
                    simulation = new AutomaticSimulation(game);
                    simulation.start();
                    break;

                case "3":
                    game.showMap();
                    break;

                case "0":
                    System.out.println("Leaving program...");
                    break;

                default:
                    System.out.println("Invalid option.");
            }

        } while (!command.equals("0"));

    }


}

