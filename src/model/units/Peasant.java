package model.units;

import model.Vector2;

import java.util.List;

public class Peasant extends Unit {
    private boolean delivery;

    public Peasant(List<Unit> gang, Vector2 initPosition) {
        super(1, 1, new DamageInfo(0, 1), 1, 3, UnitState.STAND, "Peasant");
        delivery = true;
        setGang(gang);
        setPosition(initPosition);
    }

    @Override
    public String getInfo() {
        return "Peasant " + super.getInfo() + ", delivery, " + getState();
    }
}
