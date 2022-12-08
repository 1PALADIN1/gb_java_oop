package view;

import model.GameInfo;
import model.Vector2;
import model.units.Unit;
import model.units.UnitState;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ConsoleView {
    private static final String topRow = formatDiv("a") + String.join("",
            Collections.nCopies(9, formatDiv("-b"))) + formatDiv("-c");
    private static final String midRow = formatDiv("d") + String.join("",
            Collections.nCopies(9, formatDiv("-e"))) + formatDiv("-f");
    private static final String bottomRow = formatDiv("g") + String.join("",
            Collections.nCopies(9, formatDiv("-h"))) + formatDiv("-i");

    private final GameInfo gameInfo;

    public ConsoleView(GameInfo gameInfo) {
        this.gameInfo = gameInfo;
    }

    public void drawMap() {
        if (gameInfo.isFirstStep()) {
            System.out.println(AnsiColors.ANSI_RED + "Game started! First turn." + AnsiColors.ANSI_RESET);
        } else {
            System.out.println(AnsiColors.ANSI_RED + "Turn №" + gameInfo.getTurnNumber() + AnsiColors.ANSI_RESET);
        }

        System.out.println(topRow);
        for (int i = 1; i <= gameInfo.getGangSize() - 1; i++) {
            for (int j = 1; j <= gameInfo.getGangSize(); j++) {
                System.out.print(getCharAtPosition(new Vector2(j, i)));
            }
            System.out.print("|");

            Unit unit = gameInfo.getWhiteSide().get(i - 1);
            System.out.println("\t\t" + AnsiColors.ANSI_GREEN + unit.getDisplayChar() + AnsiColors.ANSI_RESET
                    + ": " + unit.getInfo());

            System.out.print(midRow);

            unit = gameInfo.getDarkSide().get(i - 1);
            System.out.println("\t\t" + AnsiColors.ANSI_BLUE + unit.getDisplayChar() + AnsiColors.ANSI_RESET
                    + ": " + unit.getInfo());
        }

        for (int j = 1; j <= gameInfo.getGangSize(); j++) {
            System.out.print(getCharAtPosition(new Vector2(j, gameInfo.getGangSize())));
        }
        System.out.print("|");
        Unit unit = gameInfo.getWhiteSide().get(gameInfo.getWhiteSide().size() - 1);
        System.out.println("\t\t" + AnsiColors.ANSI_GREEN + unit.getDisplayChar() + AnsiColors.ANSI_RESET
                + ": " + unit.getInfo());
        System.out.print(bottomRow);

        unit = gameInfo.getDarkSide().get(gameInfo.getDarkSide().size() - 1);
        System.out.println("\t\t" + AnsiColors.ANSI_BLUE + unit.getDisplayChar() + AnsiColors.ANSI_RESET
                + ": " + unit.getInfo());

        System.out.println("Press Enter.");
    }

    private String getCharAtPosition(Vector2 position) {
        String str = "| ";

        for (int i = 0; i < gameInfo.getGangSize(); i++) {
            Unit darkUnit = gameInfo.getDarkSide().get(i);
            if (darkUnit.getPosition().isEquals(position)) {
                String color = darkUnit.getState() == UnitState.DEAD ? AnsiColors.ANSI_RED : AnsiColors.ANSI_BLUE;
                str = "|" + color + darkUnit.getDisplayChar() + AnsiColors.ANSI_RESET;
            }

            Unit whiteUnit = gameInfo.getWhiteSide().get(i);
            if (whiteUnit.getPosition().isEquals(position)) {
                String color = whiteUnit.getState() == UnitState.DEAD ? AnsiColors.ANSI_RED : AnsiColors.ANSI_GREEN;
                str = "|" + color + whiteUnit.getDisplayChar() + AnsiColors.ANSI_RESET;
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
