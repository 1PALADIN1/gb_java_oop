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
        List<Unit> unitsAtPositions = new ArrayList<>();

        for (int i = 1; i <= gameInfo.getGangSize() - 1; i++) {
            unitsAtPositions.clear();
            for (int j = 1; j <= gameInfo.getGangSize(); j++) {
                System.out.print(getCharAtPosition(new Vector2(j, i), unitsAtPositions));
            }
            System.out.print("|");
            System.out.println("\t\t" + AnsiColors.ANSI_GREEN + unitsAtPositions.get(0).getDisplayChar() + AnsiColors.ANSI_RESET
                    + ": " + unitsAtPositions.get(0).getInfo());
            System.out.print(midRow);
            System.out.println("\t\t" + AnsiColors.ANSI_BLUE + unitsAtPositions.get(1).getDisplayChar() + AnsiColors.ANSI_RESET
                    + ": " + unitsAtPositions.get(1).getInfo());
        }

        unitsAtPositions.clear();
        for (int j = 1; j <= gameInfo.getGangSize(); j++) {
            System.out.print(getCharAtPosition(new Vector2(j, gameInfo.getGangSize()), unitsAtPositions));
        }
        System.out.print("|");
        System.out.println("\t\t" + AnsiColors.ANSI_GREEN + unitsAtPositions.get(0).getDisplayChar() + AnsiColors.ANSI_RESET
                + ": " + unitsAtPositions.get(0).getInfo());
        System.out.print(bottomRow);
        System.out.println("\t\t" + AnsiColors.ANSI_BLUE + unitsAtPositions.get(1).getDisplayChar() + AnsiColors.ANSI_RESET
                + ": " + unitsAtPositions.get(1).getInfo());

        System.out.println("Press Enter.");
    }

    private String getCharAtPosition(Vector2 position, List<Unit> unitsAtPositions) {
        String str = "| ";

        for (int i = 0; i < gameInfo.getGangSize(); i++) {
            Unit darkUnit = gameInfo.getDarkSide().get(i);
            if (darkUnit.getPosition().isEquals(position)) {
                String color = darkUnit.getState() == UnitState.DEAD ? AnsiColors.ANSI_RED : AnsiColors.ANSI_BLUE;
                str = "|" + color + darkUnit.getDisplayChar() + AnsiColors.ANSI_RESET;
                unitsAtPositions.add(darkUnit);
            }

            Unit whiteUnit = gameInfo.getWhiteSide().get(i);
            if (whiteUnit.getPosition().isEquals(position)) {
                String color = whiteUnit.getState() == UnitState.DEAD ? AnsiColors.ANSI_RED : AnsiColors.ANSI_GREEN;
                str = "|" + color + whiteUnit.getDisplayChar() + AnsiColors.ANSI_RESET;
                unitsAtPositions.add(whiteUnit);
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
