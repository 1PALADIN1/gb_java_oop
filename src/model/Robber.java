package model;

public class Robber extends Unit {
    public Robber() {
        super(8, 3, new DamageInfo(2, 4), 10, 6, UnitState.STAND);
    }

    @Override
    public String getInfo() {
        return "Robber " + super.getInfo() + ", " + getState();
    }
}
