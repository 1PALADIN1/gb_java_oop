package model;

public class Xbowman extends Unit {
    private int shoots;

    public Xbowman() {
        super(6, 3, new DamageInfo(2, 3), 10, 4, UnitState.STAND);
        shoots = 16;
    }

    public String toString() {
        return "Xbowman " + super.toString() + ", shoots: " + shoots + ", " + getState();
    }
}
