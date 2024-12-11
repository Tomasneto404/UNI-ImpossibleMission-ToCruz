package Game;

import Game.Entitys.Player;
import Game.Menu.Menu;
import Game.Mission.Mission;

public class Run {
    public static void main(String[] args) {

        Game game = new Game(new Player("Tó Cruz"), "file.json");

        Menu menu = new Menu(game);
        menu.mainMenu();

    }
}