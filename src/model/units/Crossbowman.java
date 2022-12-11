package model.units;

import model.Vector2;

import java.util.List;
import java.util.Random;

public class Crossbowman extends Unit {
    private int shoots;

    public Crossbowman(List<Unit> gang, List<Unit> enemies, Vector2 initPosition) {
        super(6, 3, new DamageInfo(2, 3), 10, 4, UnitState.STAND, UnitName.CROSSBOWMAN, gang, enemies);
        shoots = 16;
        setPosition(initPosition);
        setQuantity(new Random().nextInt(2, 7));
    }

    @Override
    public String getInfo() {
        return "Crossbowman\t" + super.getInfo() + ", shoots: " + shoots;
    }

    @Override
    public void step() {
        for (Unit unit : getGang()) {
            if (unit.getName().equals(UnitName.PEASANT)) {
                if (unit.getState() != UnitState.BUSY && unit.getState() != UnitState.DEAD) {
                    shoots++;
                    unit.setState(UnitState.BUSY);
                    break;
                }
            }
        }

        if (shoots <= 0) {
            return;
        }

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
            Unit enemy = getEnemies().get(index);
            enemy.performHit(getSpeed() > dist ? calcDamage(enemy) : calcDamage(enemy) / 2);
            shoots--;
        }
    }
}
