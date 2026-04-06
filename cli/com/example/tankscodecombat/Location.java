package com.example.tankscodecombat;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class Location {
    private int x;
    private int y;

    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() { return x; }
    public int getY() { return y; }
    public void setX(int x) { this.x = x; }
    public void setY(int y) { this.y = y; }

    public void move(Direction direction, Tank.Speed speed) {
        int amount = speed.getSpeedVal();
        double rad = Math.toRadians(direction.getDegrees());
        setX(getX() + (int)((amount * 10) * cos(rad)));
        setY(getY() + (int)((amount * 10) * sin(rad)));
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
