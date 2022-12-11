package model.units;

import model.Vector2;

import java.util.List;
import java.util.Random;

public abstract class Unit implements UnitInterface {
    private final int attack;
    private final int defense;
    private final DamageInfo damage;
    private final float maxHealth;
    private final int speed;
    private final String name;
    private final List<Unit> gang;
    private final List<Unit> enemies;

    private UnitState state;
    private float health;
    private Vector2 position;
    private int quantity;

    public Unit(int attack, int defense, DamageInfo damage, float health, int speed, UnitState state, String name,
                List<Unit> gang, List<Unit> enemies) {
        this.attack = attack;
        this.defense = defense;
        this.damage = damage;
        this.health = health;
        this.maxHealth = health;
        this.speed = speed;
        this.state = state;
        this.name = name;
        this.gang = gang;
        this.enemies = enemies;
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
        return "\uD83D\uDDE1️:" + attack + ", \uD83D\uDEE1️:" + defense + ", \uD83C\uDFAF:" + damage.getAverage() +
                ", \uD83E\uDDE1:" + health + ", \uD83E\uDD7E:" + speed + ", state:" + state;
    }

    @Override
    public void step() {}

    public float calcDamage(Unit unit) {
        if (unit.getDefense() - attack == 0) {
            return damage.getAverage() * quantity;
        }

        if (unit.getDefense() - attack < 0) {
            return damage.getMax() * quantity;
        }

        return damage.getMin() * quantity;
    }

    public void performHit(float damage) {
        float tmpHealth = (quantity - 1) * maxHealth + health;
        tmpHealth -= damage;
        if (tmpHealth <= 0) {
            health = 0;
            state = UnitState.DEAD;
        } else {
            quantity = (int) (tmpHealth / maxHealth);
            health = maxHealth;
            if (tmpHealth % maxHealth > 0) {
                quantity++;
                health = tmpHealth % maxHealth;
            }
        }

    }

    protected List<Unit> getGang() {
        return gang;
    }

    protected List<Unit> getEnemies() {
        return enemies;
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

    protected void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getSpeed() {
        return speed;
    }

    public UnitState getState() {
        return state;
    }

    public void setState(UnitState state) {
        this.state = state;
    }

    public int getDefense() {
        return defense;
    }
}
