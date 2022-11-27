package model;

public class Peasant extends Unit {
    private boolean delivery;

    public Peasant() {
        super(1, 1, new DamageInfo(0, 1), 1, 3, UnitState.STAND);
        delivery = true;
    }

    @Override
    public String getInfo() {
        return "Peasant " + super.getInfo() + ", delivery, " + getState();
    }
}
