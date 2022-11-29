package model.units;

import model.Vector2;

import java.util.List;

public class Wizard extends Unit {
    private boolean magic;

    public Wizard(List<Unit> gang, Vector2 initPosition) {
        super(17, 12, new DamageInfo(-5, -5), 30, 9, UnitState.STAND, "Wizard");
        magic = true;
        setGang(gang);
        setPosition(initPosition);
    }

    @Override
    public String getInfo() {
        return "Wizard " + super.getInfo() + ", magic, " + getState();
    }

    @Override
    public void step(List<Unit> opponents) {
        List<Unit> gang = getGang();
        float minHealth = Integer.MAX_VALUE;
        int minIndex = -1;

        for (int i = 0; i < gang.size(); i++) {
            Unit unit = gang.get(i);
            if (unit.getHealth() < unit.getMaxHealth() && unit.getHealth() < minHealth) {
                minHealth = unit.getHealth();
                minIndex = i;
            }
        }

        if (minIndex >= 0) {
            Unit healedUnit = gang.get(minIndex);
            float newHealth = healedUnit.getHealth() - getDamage().getMin();
            healedUnit.setHealth(Math.min(newHealth, healedUnit.getMaxHealth()));
        }
    }
}