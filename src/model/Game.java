package model;

import model.units.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game {
    private static final int INIT_STEP = 1;

    private final int gangSize;
    private final List<Unit> whiteSide;
    private final List<Unit> darkSide;

    private int turnNumber;

    public Game(int gangSize) {
        this.gangSize = gangSize;
        whiteSide = new ArrayList<>();
        darkSide = new ArrayList<>();
        turnNumber = INIT_STEP;

        int x = 1;
        int y = 1;
        Random rand = new Random();
        for (int i = 0; i < this.gangSize; i++) {
            switch (rand.nextInt(4)) {
                case 0 -> whiteSide.add(new Peasant(whiteSide, new Vector2(x, y++)));
                case 1 -> whiteSide.add(new Robber(whiteSide, new Vector2(x, y++)));
                case 2 -> whiteSide.add(new Sniper(whiteSide, new Vector2(x, y++)));
                default -> whiteSide.add(new Monk(whiteSide, new Vector2(x, y++)));
            }
        }

        x = this.gangSize;
        y = 1;
        for (int i = 0; i < this.gangSize; i++) {
            switch (rand.nextInt(4)) {
                case 0 -> darkSide.add(new Peasant(darkSide, new Vector2(x, y++)));
                case 1 -> darkSide.add(new Spearman(darkSide, new Vector2(x, y++)));
                case 2 -> darkSide.add(new Xbowman(darkSide, new Vector2(x, y++)));
                default -> darkSide.add(new Wizard(darkSide, new Vector2(x, y++)));
            }
        }
    }

    public void nextTurn() {
        whiteSide.forEach(unit -> unit.step(darkSide));
        darkSide.forEach(unit -> unit.step(whiteSide));
        turnNumber++;
    }

    public boolean isFirstStep() {
        return turnNumber == INIT_STEP;
    }

    public int getGangSize() {
        return gangSize;
    }

    public int getTurnNumber() {
        return turnNumber;
    }

    public List<Unit> getWhiteSide() {
        return whiteSide;
    }

    public List<Unit> getDarkSide() {
        return darkSide;
    }
}
