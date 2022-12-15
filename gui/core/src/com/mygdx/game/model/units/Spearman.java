package com.mygdx.game.model.units;

import com.badlogic.gdx.math.Vector2;

import java.util.List;
import java.util.Random;

public class Spearman extends Unit {
    public Spearman(List<Unit> gang, List<Unit> enemies, Vector2 initPosition) {
        super(4, 5, new DamageInfo(1, 3), 10, 4, UnitState.STAND, UnitName.SPEARMAN, gang, enemies);
        setPosition(initPosition);
        setQuantity(new Random().nextInt(10) + 10);
    }

    @Override
    public String getInfo() {
        return "Spearman\t\t" + super.getInfo();
    }

    @Override
    public void step() {
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

                boolean empty = true;
                for (Unit unit : getGang()) {
                    if (unit.getPosition().epsilonEquals(newPos)) {
                        empty = false;
                    }
                }

                if (empty) {
                    setPosition(newPos);
                }
            }
        }
    }
}
