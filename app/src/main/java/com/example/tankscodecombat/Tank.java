package com.example.tankscodecombat;

public class Tank {
    // Enums
    public enum Direction { NORTH, EAST, SOUTH, WEST }
    public enum Ammo { BULLET, MISSILE }
    public enum Speed {
        REVERSE(-1), STOP(0), SLOW(1), FAST(2);
        int speedVal;
        Speed(int speedVal) {
            this.speedVal = speedVal;
        }

        public int getSpeedVal() {
            return speedVal;
        }
    }
    // Constructor
    public Tank() {
        // init tank state here
    }

    // Methods
    private boolean move(Speed speed) {
        // TODO: implement movement
        return true;
    }

    private boolean rotate(Direction direction) {
        // TODO: implement rotation
        return true;
    }

    private boolean rotateTurret(Direction direction) {
        // TODO: implement turret rotation
        return true;
    }

    private boolean fire() {
        // TODO: implement firing
        return true;
    }

    private boolean reload(Ammo ammo) {
        // TODO: implement reloading
        return true;
    }

    private Direction radar() {
        // TODO: implement radar scanning
        return Direction.NORTH;
    }
}
