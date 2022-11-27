package model;

import java.util.List;

public class Wizard extends Unit {
    private boolean magic;

    public Wizard(List<Unit> gang) {
        super(17, 12, new DamageInfo(-5, -5), 30, 9, UnitState.STAND);
        magic = true;
        setGang(gang);
    }

    @Override
    public String getInfo() {
        return "Wizard " + super.getInfo() + ", magic, " + getState();
    }

    @Override
    public void step() {
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
