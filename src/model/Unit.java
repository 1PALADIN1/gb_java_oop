package model;

public class Unit {
    private final int attack;
    private final int defense;
    private final DamageInfo damage;
    private final float health;
    private final int speed;
    private UnitState state;

    public Unit(int attack, int defense, DamageInfo damage, float health, int speed, UnitState state) {
        this.attack = attack;
        this.defense = defense;
        this.damage = damage;
        this.health = health;
        this.speed = speed;
        this.state = state;
    }

    @Override
    public String toString() {
        return "Attack:" + attack + ", defence:" + defense + ", damage:" + damage.getAverage() +
                ", health:" + health + ", speed:" + speed;
    }

    protected UnitState getState() {
        return state;
    }
}
