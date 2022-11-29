package model.units;

import model.Vector2;

import java.util.List;

public class Xbowman extends Unit {
    private int shoots;

    public Xbowman(List<Unit> gang, Vector2 initPosition) {
        super(6, 3, new DamageInfo(2, 3), 10, 4, UnitState.STAND, "Xbowman");
        shoots = 16;
        setGang(gang);
        setPosition(initPosition);
    }

    @Override
    public String getInfo() {
        return "Xbowman " + super.getInfo() + ", shoots: " + shoots + ", " + getState();
    }
}
