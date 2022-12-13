package com.mygdx.game.model.units;

public class DamageInfo {
    private final int min;
    private final int max;

    public DamageInfo(int min, int max) {
        this.min = min;
        this.max = max;
    }

    public float getAverage() {
        return (min + max) / 2.0f;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }
}
