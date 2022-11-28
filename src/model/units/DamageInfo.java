package model.units;

public class DamageInfo {
    private final int min;
    private final int max;

    public DamageInfo(int min, int max) {
        this.min = min;
        this.max = max;
    }

    public int getAverage() {
        return (min + max) / 2;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }
}
