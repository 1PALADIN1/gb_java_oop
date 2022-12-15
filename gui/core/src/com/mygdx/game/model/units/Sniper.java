package com.mygdx.game.model.units;

import com.badlogic.gdx.math.Vector2;

import java.util.List;
import java.util.Random;

public class Sniper extends Unit {
    private int shoots;

    public Sniper(List<Unit> gang, List<Unit> enemies, Vector2 initPosition) {
        super(12, 10, new DamageInfo(8, 10), 15, 9, UnitState.STAND, UnitName.SNIPER, gang, enemies);
        shoots = 32;
        setPosition(initPosition);
        setQuantity(new Random().nextInt(2) + 2);
    }

    @Override
    public String getInfo() {
        return "Sniper\t\t" + super.getInfo() + ", shoots: " + shoots;
    }

    @Override
    public void step() {
        for (Unit unit : getGang()) {
            if (unit.getName().equals(UnitName.PEASANT)) {
                shoots++;
                unit.setState(UnitState.BUSY);
                break;
            }
        }

        if (shoots <= 0) {
            return;
        }

        double dist = Double.MAX_VALUE;
        int index = -1;
        for (int i = 0; i < getEnemies().size(); i++) {
            Unit enemy = getEnemies().get(i);
            double tmp = enemy.getPosition().dst(getPosition());
            if (dist > tmp && enemy.getState() != UnitState.DEAD) {
                dist = tmp;
                index = i;
            }
        }

        if (index >= 0) {
            Unit enemy = getEnemies().get(index);
            enemy.performHit(getSpeed() > dist ? calcDamage(enemy) : calcDamage(enemy) / 2);
            shoots--;
        }
    }
}
