package com.example.tankscodecombat;

public class Direction {
    private int degrees;

    public Direction(int degrees) {
        this.degrees = ((degrees % 360) + 360) % 360;
    }

    public int getDegrees() {
        return degrees;
    }

    public void setDegrees(int degrees) {
        this.degrees = ((degrees % 360) + 360) % 360;
    }

    public Direction rotate(int delta) {
        return new Direction((((degrees + delta) % 360) + 360) % 360);
    }

    @Override
    public String toString() {
        return degrees + "\u00b0";
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Direction direction = (Direction) o;
        return degrees == direction.degrees;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(degrees);
    }
}
