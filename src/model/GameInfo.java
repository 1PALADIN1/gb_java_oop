package model;

import model.units.Unit;

import java.util.List;

public interface GameInfo {
    List<Unit> getWhiteSide();
    List<Unit> getDarkSide();
    int getGangSize();
    boolean isFirstStep();
    int getTurnNumber();
}
