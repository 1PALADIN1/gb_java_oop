package com.mygdx.game.model.units;

import com.mygdx.game.model.Vector2;

import java.util.List;
import java.util.Random;

public class Peasant extends Unit {
    private boolean delivery;

    public Peasant(List<Unit> gang, List<Unit> enemies, Vector2 initPosition) {
        super(1, 1, new DamageInfo(0, 1), 1, 3, UnitState.STAND, UnitName.PEASANT, gang, enemies);
        delivery = true;
        setPosition(initPosition);
        setQuantity(new Random().nextInt(5) + 1);
    }

    @Override
    public String getInfo() {
        return "Peasant\t\t" + super.getInfo() + ", delivery";
    }

    @Override
    public void step() {
        if (getState() == UnitState.BUSY) {
            setState(UnitState.STAND);
        }
    }
}
