package model.units;

import model.Vector2;

import java.util.ArrayList;
import java.util.List;

public abstract class Unit implements UnitInterface {
    private final int attack;
    private final int defense;
    private final DamageInfo damage;
    private final float maxHealth;
    private final int speed;
    private final String name;

    private UnitState state;
    private float health;
    private Vector2 position;
    private List<Unit> gang = new ArrayList<>();

    public Unit(int attack, int defense, DamageInfo damage, float health, int speed, UnitState state, String name) {
        this.attack = attack;
        this.defense = defense;
        this.damage = damage;
        this.health = health;
        this.maxHealth = health;
        this.speed = speed;
        this.state = state;
        this.name = name;
        position = new Vector2();
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public Vector2 getPosition() {
        return position;
    }

    public String getName() {
        return name;
    }

    public char getDisplayChar() {
        return getName().toUpperCase().charAt(0);
    }

    @Override
    public String getInfo() {
        return "Attack:" + attack + ", defence:" + defense + ", damage:" + damage.getAverage() +
                ", health:" + health + ", speed:" + speed;
    }

    @Override
    public void step(List<Unit> opponents) {}

    protected List<Unit> getGang() {
        return gang;
    }

    protected void setGang(List<Unit> gang) {
        this.gang = gang;
    }

    protected float getMaxHealth() {
        return maxHealth;
    }

    protected float getHealth() {
        return health;
    }

    protected void setHealth(float health) {
        this.health = health;
    }

    protected DamageInfo getDamage() {
        return damage;
    }

    protected UnitState getState() {
        return state;
    }
}
