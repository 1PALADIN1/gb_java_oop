package com.mygdx.game.model;

public class Vector2 {
    public int x;
    public int y;

    public Vector2() {
        this(0, 0);
    }

    public Vector2(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean isEquals(Vector2 other) {
        return x == other.x && y == other.y;
    }

    public double distance(Vector2 other) {
        return Math.sqrt(Math.pow(other.x - x, 2) + Math.pow(other.y - y, 2));
    }
}
