package model;

public class Sniper extends Unit {
    private int shoots;

    public Sniper() {
        super(12, 10, new DamageInfo(8, 10), 15, 9, UnitState.STAND);
        shoots = 32;
    }

    @Override
    public String toString() {
        return "Sniper " + super.toString() + ", shoots: " + shoots + ", " + getState();
    }
}
