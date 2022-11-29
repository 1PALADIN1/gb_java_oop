package model.units;

import model.Vector2;

import java.util.List;

public class Monk extends Unit {
    private boolean magic;

    public Monk(List<Unit> gang, Vector2 initPosition) {
        super(12, 7, new DamageInfo(-4, -4), 30, 5, UnitState.STAND, "Monk");
        magic = true;
        setGang(gang);
        setPosition(initPosition);
    }

    @Override
    public String getInfo() {
        return "Monk " + super.getInfo() + ", magic, " + getState();
    }
}
