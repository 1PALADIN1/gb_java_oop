package com.mygdx.game.model;

import com.mygdx.game.model.units.Unit;

import java.util.List;

public interface GameInfo {
    List<Unit> getWhiteSide();
    List<Unit> getDarkSide();
    int getGangSize();
    boolean isFirstStep();
    int getTurnNumber();
}
