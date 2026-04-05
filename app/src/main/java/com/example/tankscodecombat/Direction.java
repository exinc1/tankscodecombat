package com.example.tankscodecombat;

import java.util.Objects;

public class Direction {
    private int degrees; // always 0 - 360

    public Direction(int degrees) {
        // keep it between 0 - 360
        this.degrees = ((degrees % 360) + 360) % 360;
    }

    public int getDegrees() {
        return degrees;
    }

    public void setDegrees(int degrees) {
        this.degrees = ((degrees % 360) + 360) % 360;
    }

    public Direction rotate(int delta) {
        // keep it between 0 - 360
        return new Direction((((degrees + delta) % 360) + 360) % 360);
    }

    @Override
    public String toString() {
        return degrees + "°";
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Direction direction = (Direction) o;
        return degrees == direction.degrees;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(degrees);
    }
}