package model.units;

import model.Vector2;

import java.util.List;

public class Sniper extends Unit {
    private int shoots;

    public Sniper(List<Unit> gang, Vector2 initPosition) {
        super(12, 10, new DamageInfo(8, 10), 15, 9, UnitState.STAND, "Sniper");
        shoots = 32;
        setGang(gang);
        setPosition(initPosition);
    }

    @Override
    public String getInfo() {
        return "Sniper " + super.getInfo() + ", shoots: " + shoots + ", " + getState();
    }
}
