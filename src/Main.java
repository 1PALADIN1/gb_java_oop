import model.Game;
import view.ConsoleView;

import java.util.Scanner;

public class Main {
    private static final int GANG_SIZE = 10;

    public static void main(String[] args) {
        Game game = new Game(GANG_SIZE);
        ConsoleView view = new ConsoleView(game);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            view.drawMap();
            scanner.nextLine();
            game.nextTurn();
        }
    }
}