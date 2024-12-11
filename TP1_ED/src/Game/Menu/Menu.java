package Game.Menu;

import Game.Mission.Division;
import Game.Mission.Map;

import java.util.Scanner;

public class Menu {

    private Map<Division> map;

    {

    }

    public Menu(Map<Division> map){
        this.map = map;
    }

    public void menu() {

        Scanner scanner = new Scanner(System.in);
        String command;

        System.out.println("\n=== Mission Simulator ===");
        do {
            System.out.println("\nChoose an option:");
            System.out.println("1. Play");
            System.out.println("2. View Map");
            System.out.println("3. Percorrer grafo (BFS)");
            System.out.println("4. Percorrer grafo (DFS)");
            System.out.println("0. Sair");
            System.out.print("> ");

            command = scanner.nextLine();

            switch (command) {
                case "1":
                    //listVertices();
                    break;
                case "2":
                    System.out.println(map.toString());
                    break;
                case "3":
                    //traverseGraphBFS(scanner);
                    break;
                case "4":
                    //traverseGraphDFS(scanner);
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

