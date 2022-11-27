package model;

public class Monk extends Unit {
    private boolean magic;

    public Monk() {
        super(12, 7, new DamageInfo(-4, -4), 30, 5, UnitState.STAND);
        magic = true;
    }

    @Override
    public String toString() {
        return "Monk " + super.toString() + ", magic, " + getState();
    }
}
