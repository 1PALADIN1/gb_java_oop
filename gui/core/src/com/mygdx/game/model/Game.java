package com.mygdx.game.model;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.model.units.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game implements GameInfo {
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
                case 0:
                    whiteSide.add(new Peasant(whiteSide, darkSide, new Vector2(x, y++)));
                case 1:
                    whiteSide.add(new Robber(whiteSide, darkSide, new Vector2(x, y++)));
                case 2:
                    whiteSide.add(new Sniper(whiteSide, darkSide, new Vector2(x, y++)));
                default:
                    whiteSide.add(new Monk(whiteSide, darkSide, new Vector2(x, y++)));
            }
        }

        x = this.gangSize;
        y = 1;
        for (int i = 0; i < this.gangSize; i++) {
            switch (rand.nextInt(4)) {
                case 0:
                    darkSide.add(new Peasant(darkSide, whiteSide, new Vector2(x, y++)));
                case 1:
                    darkSide.add(new Spearman(darkSide, whiteSide, new Vector2(x, y++)));
                case 2:
                    darkSide.add(new Crossbowman(darkSide, whiteSide, new Vector2(x, y++)));
                default:
                    darkSide.add(new Wizard(darkSide, whiteSide, new Vector2(x, y++)));
            }
        }
    }

    public void nextTurn() {
        List<Unit> sortedUnits = new ArrayList<>(whiteSide);
        sortedUnits.addAll(darkSide);
        sortedUnits.sort((o1, o2) -> o2.getSpeed() - o1.getSpeed());
        for (Unit unit : sortedUnits) {
            if (unit.getState() != UnitState.DEAD) {
                unit.step();
            }
        }
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
