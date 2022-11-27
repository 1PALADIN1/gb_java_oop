package model;

public class Spearman extends Unit {
    public Spearman() {
        super(4, 5, new DamageInfo(1, 3), 10, 4, UnitState.STAND);
    }

    @Override
    public String getInfo() {
        return "Spearman " + super.getInfo() + ", " + getState();
    }
}
