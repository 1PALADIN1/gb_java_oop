package view;

import model.Game;
import model.Vector2;
import model.units.Unit;

import java.util.Collections;

public class ConsoleView {
    private static final String topRow = formatDiv("a") + String.join("",
            Collections.nCopies(9, formatDiv("-b"))) + formatDiv("-c");
    private static final String midRow = formatDiv("d") + String.join("",
            Collections.nCopies(9, formatDiv("-e"))) + formatDiv("-f");
    private static final String bottomRow = formatDiv("g") + String.join("",
            Collections.nCopies(9, formatDiv("-h"))) + formatDiv("-i");

    private final Game game;

    public ConsoleView(Game game) {
        this.game = game;
    }

    public void drawMap() {
        if (game.isFirstStep()) {
            System.out.println(AnsiColors.ANSI_RED + "Game started! First turn." + AnsiColors.ANSI_RESET);
        } else {
            System.out.println(AnsiColors.ANSI_RED + "Turn №" + game.getTurnNumber() + AnsiColors.ANSI_RESET);
        }

        System.out.println(topRow);

        for (int i = 1; i <= game.getGangSize() - 1; i++) {
            for (int j = 1; j <= game.getGangSize(); j++) {
                System.out.print(getCharAtPosition(new Vector2(j, i)));
            }
            System.out.println("|");
            System.out.println(midRow);
        }

        for (int j = 1; j <= game.getGangSize(); j++) {
            System.out.print(getCharAtPosition(new Vector2(j, game.getGangSize())));
        }
        System.out.println("|");

        System.out.println(bottomRow);
        System.out.println("Press Enter.");
    }

    private String getCharAtPosition(Vector2 position) {
        String str = "| ";

        for (int i = 0; i < game.getGangSize(); i++) {
            Unit darkUnit = game.getDarkSide().get(i);
            if (darkUnit.getPosition().isEquals(position)) {
                str = "|" + AnsiColors.ANSI_BLUE + darkUnit.getName().toUpperCase().charAt(0) + AnsiColors.ANSI_RESET;
            }

            Unit whiteUnit = game.getWhiteSide().get(i);
            if (whiteUnit.getPosition().isEquals(position)) {
                str = "|" + AnsiColors.ANSI_GREEN + whiteUnit.getName().toUpperCase().charAt(0) + AnsiColors.ANSI_RESET;
            }
        }

        return str;
    }

    private static String formatDiv(String str) {
        return str.replace('a', '\u250c')
                .replace('b', '\u252c')
                .replace('c', '\u2510')
                .replace('d', '\u251c')
                .replace('e', '\u253c')
                .replace('f', '\u2524')
                .replace('g', '\u2514')
                .replace('h', '\u2534')
                .replace('i', '\u2518')
                .replace('-', '\u2500');
    }
}
