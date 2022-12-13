package com.mygdx.game.model.units;

import com.mygdx.game.model.Vector2;

import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

public class Robber extends Unit {
    public Robber(List<Unit> gang, List<Unit> enemies, Vector2 initPosition) {
        super(8, 3, new DamageInfo(2, 4), 10, 6, UnitState.STAND, UnitName.ROBBER, gang, enemies);
        setPosition(initPosition);
        setQuantity(new Random().nextInt(4) + 6);
    }

    @Override
    public String getInfo() {
        return "Robber\t\t" + super.getInfo();
    }

    @Override
    public void step() {
        double dist = Double.MAX_VALUE;
        int index = -1;
        for (int i = 0; i < getEnemies().size(); i++) {
            Unit enemy = getEnemies().get(i);
            double tmp = enemy.getPosition().distance(getPosition());
            if (dist > tmp && enemy.getState() != UnitState.DEAD) {
                dist = tmp;
                index = i;
            }
        }

        if (index >= 0) {
            if (dist < 2) {
                Unit enemy = getEnemies().get(index);
                enemy.performHit(calcDamage(enemy));
            } else {
                Vector2 enemyPos = getEnemies().get(index).getPosition();
                Vector2 newPos = new Vector2();
                if (enemyPos.y == getPosition().y) {
                    newPos.y = getPosition().y;
                    if (getPosition().x - enemyPos.x < 0) {
                        newPos.x = getPosition().x + 1;
                    } else {
                        newPos.x = getPosition().x - 1;
                    }
                } else {
                    newPos.x = getPosition().x;
                    if (enemyPos.y - getPosition().y > 0) {
                        newPos.y = getPosition().y + 1;
                    } else {
                        newPos.y = getPosition().y - 1;
                    }
                }

                AtomicBoolean empty = new AtomicBoolean(true);
                for (Unit unit : getGang()) {
                    if (unit.getPosition().isEquals(newPos)) {
                        empty.set(false);
                    }
                }

                if (empty.get()) {
                    setPosition(newPos);
                }
            }
        }
    }
}
