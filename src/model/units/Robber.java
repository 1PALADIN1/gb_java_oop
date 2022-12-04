package model.units;

import model.Vector2;

import java.util.List;

public class Robber extends Unit {
    public Robber(List<Unit> gang, List<Unit> enemies, Vector2 initPosition) {
        super(8, 3, new DamageInfo(2, 4), 10, 6, UnitState.STAND, UnitName.ROBBER, gang, enemies);
        setPosition(initPosition);
    }

    @Override
    public String getInfo() {
        return "Robber " + super.getInfo();
    }
}
