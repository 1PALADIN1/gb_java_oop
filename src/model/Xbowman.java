package model;

public class Xbowman extends Unit {
    private int shoots;

    public Xbowman() {
        super(6, 3, new DamageInfo(2, 3), 10, 4, UnitState.STAND);
        shoots = 16;
    }

    @Override
    public String getInfo() {
        return "Xbowman " + super.getInfo() + ", shoots: " + shoots + ", " + getState();
    }
}
