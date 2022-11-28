import model.Game;
import view.ConsoleView;

import java.util.Scanner;

public class Main {
    private static final int GANG_SIZE = 10;
    private static Game game;
    private static ConsoleView view;

    public static void main(String[] args) {
        game = new Game(GANG_SIZE);
        view = new ConsoleView(game);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            view.drawMap();
            scanner.nextLine();
            game.nextTurn();
        }
    }
}