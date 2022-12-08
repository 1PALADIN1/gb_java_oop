package model.units;

import model.Vector2;

import java.util.List;
import java.util.Random;

public class Peasant extends Unit {
    private boolean delivery;

    public Peasant(List<Unit> gang, List<Unit> enemies, Vector2 initPosition) {
        super(1, 1, new DamageInfo(0, 1), 1, 3, UnitState.STAND, UnitName.PEASANT, gang, enemies);
        delivery = true;
        setPosition(initPosition);
        setQuantity(new Random().nextInt(1, 5));
    }

    @Override
    public String getInfo() {
        return "Peasant " + super.getInfo() + ", delivery";
    }

    @Override
    public void step() {
        if (getState() == UnitState.BUSY) {
            setState(UnitState.STAND);
        }
    }
}
