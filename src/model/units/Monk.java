package model.units;

import model.Vector2;

import java.util.*;

public class Monk extends Unit {
    private final float healCheckValue = 0.75f;

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

    @Override
    public void step() {
        List<Unit> gang = getGang();
        Map<Integer, Float> healths = new HashMap<>();
        for (int i = 0; i < gang.size(); i++) {
            healths.put(i, gang.get(i).getHealth() / gang.get(i).getMaxHealth());
        }

        List<Float> sortedHealth = new ArrayList<>(healths.values().stream().toList());
        Collections.sort(sortedHealth);

        // маг стреляет
        if (sortedHealth.get(0) > healCheckValue) {
            double dist = 1;
            int index = -1;
            for (int i = 0; i < getEnemies().size(); i++) {
                Unit enemy = getEnemies().get(i);
                if (enemy.getState() == UnitState.DEAD) {
                    continue;
                }

                if (enemy.getHealth() / enemy.getMaxHealth() < dist) {
                    dist = enemy.getHealth() / enemy.getMaxHealth();
                    index = i;
                }
            }

            if (index < 0) {
                index = 0;
            }

            Unit enemy = getEnemies().get(index);
            enemy.performHit(-1 * getDamage().getMax());

            return;
        }

        // маг воскрешает
        float aproxZero = 0.0001f;
        if (sortedHealth.get(0) <= aproxZero) {
            for (Map.Entry<Integer, Float> entry : healths.entrySet()) {
                if (entry.getValue() > aproxZero) {
                    continue;
                }

                Unit unitToHeal = getGang().get(entry.getKey());
                if (unitToHeal.getName().equals(UnitName.SNIPER) || unitToHeal.getName().equals(UnitName.MONK)) {
                    unitToHeal.setHealth(1);
                    unitToHeal.setState(UnitState.STAND);
                    // вылечили коллегу, завершаем ход
                    return;
                }
            }
        }

        // маг лечит
        float minHealth = Integer.MAX_VALUE;
        int minIndex = -1;

        for (int i = 0; i < gang.size(); i++) {
            Unit unit = gang.get(i);
            if (unit.getState() == UnitState.DEAD) {
                continue;
            }

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
