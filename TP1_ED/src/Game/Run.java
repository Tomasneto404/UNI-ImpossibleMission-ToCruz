package Game;

import Game.Entitys.Player;
import Game.Menu.Menu;

/**
 * Main method to start the game.
 *
 * @author Tânia Morais
 * @author Tomás Neto
 */
public class Run {
    public static void main(String[] args) {

        Game game = new Game(new Player("Tó Cruz"), "file.json");

        Menu menu = new Menu(game);
        menu.mainMenu();

    }
}
