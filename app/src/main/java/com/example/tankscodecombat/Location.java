package com.example.tankscodecombat;
public class Location {
    private int x;
    private int y;

    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void move(Tank.Direction direction, Tank.Speed speed) {
        int amount = speed.getSpeedVal(); // FAST=2, SLOW=1, REVERSE=-1, STOP=0

        switch (direction) {
            case NORTH:
                y -= amount;
                break;
            case SOUTH:
                y += amount;
                break;
            case EAST:
                x += amount;
                break;
            case WEST:
                x -= amount;
                break;
        }
    }
}
