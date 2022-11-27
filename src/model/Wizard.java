package model;

public class Wizard extends Unit {
    private boolean magic;

    public Wizard() {
        super(17, 12, new DamageInfo(-5, -5), 30, 9, UnitState.STAND);
        magic = true;
    }

    @Override
    public String toString() {
        return "Wizard " + super.toString() + ", magic, " + getState();
    }
}
