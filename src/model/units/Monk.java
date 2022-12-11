package model.units;

import model.Vector2;

import java.util.List;
import java.util.Random;

public class Monk extends Unit {
    private boolean magic;

    public Monk(List<Unit> gang, List<Unit> enemies, Vector2 initPosition) {
        super(12, 7, new DamageInfo(-4, -4), 30, 5, UnitState.STAND, UnitName.MONK, gang, enemies);
        magic = true;
        setPosition(initPosition);
        setQuantity(new Random().nextInt(2, 5));
    }

    @Override
    public String getInfo() {
        return "Monk\t\t\t" + super.getInfo() + ", magic" ;
    }
}
