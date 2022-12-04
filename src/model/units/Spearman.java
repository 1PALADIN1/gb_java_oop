package model.units;

import model.Vector2;

import java.util.List;

public class Spearman extends Unit {
    public Spearman(List<Unit> gang, List<Unit> enemies, Vector2 initPosition) {
        super(4, 5, new DamageInfo(1, 3), 10, 4, UnitState.STAND, UnitName.SPEARMAN, gang, enemies);
        setPosition(initPosition);
    }

    @Override
    public String getInfo() {
        return "Spearman " + super.getInfo();
    }
}
