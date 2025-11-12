package com.example.tankscodecombat;

public abstract class Tank {
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
    private boolean _canAct = true;
    private Speed _speed;
    private Direction _direction;
    private Direction _turretDirection;
    private Ammo _ammo;
    // Constructor
    public Tank() {
        _speed = Speed.STOP;
        _ammo = Ammo.BULLET;
        _direction = Direction.NORTH;
        _turretDirection = Direction.NORTH;
    }

    // Methods
    public boolean move(Speed speed) {
        if (!_canAct) return false;

        _speed = speed;
        _canAct = false;
        return true;
    }

    public boolean rotate(Direction direction) {
        if (!_canAct) return false;

        _direction = direction;
        _canAct = false;
        return true;
    }

    public boolean rotateTurret(Direction direction) {
        if (!_canAct) return false;

        _turretDirection = direction;
        _canAct = false;
        return true;
    }

    public boolean fire() {
        if (!_canAct || _ammo == null) return false;

        // implement fire
        _canAct = false;
        return true;
    }

    public boolean reload(Ammo ammo) {
        if (!_canAct) return false;

        _ammo = ammo;
        _canAct = false;
        return true;
    }

    // user tank dont! touch this
    public Direction radar() {
        _canAct = true;
        return Direction.NORTH;
    }

    abstract Action run(Direction direction);
}
