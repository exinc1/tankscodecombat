package com.example.tankscodecombat;

public abstract class Tank {
    public enum Ammo {
        NONE(0),
        BULLET(1),
        MISSILE(3);

        private final int range;

        Ammo(int range) {
            this.range = range;
        }

        public int getRange() {
            return range;
        }
    }
    public enum Speed {
        REVERSE(-1), STOP(0), SLOW(1), FAST(2);
        private int speedVal;
        Speed(int speedVal) {
            this.speedVal = speedVal;
        }

        public int getSpeedVal() {
            return speedVal;
        }

        public void setSpeedVal(int speedVal) {
            this.speedVal = speedVal;
        }
    }
    private Speed _speed;
    private Direction _direction;
    private Direction _turretDirection;
    private Ammo _ammo;
    // Constructor
    public Tank() {
        _speed = Speed.STOP;
        _ammo = Ammo.BULLET;
        _direction = new Direction(0);
        _turretDirection = new Direction(0);
    }

    // Methods

    public Ammo get_ammo() {
        return _ammo;
    }

    public Direction get_direction() {
        return _direction;
    }

    public Direction get_turretDirection() {
        return _turretDirection;
    }

    public Speed get_speed() {
        return _speed;
    }

    public void set_direction(int direction) {
        _direction.setDegrees(direction);
    }

    public void set_turretDirection(int direction) {
        _turretDirection.setDegrees(direction);
    }

    public void set_speed(int speed) {
        _speed.setSpeedVal(speed);
    }

    TankState getState(Direction radar) {
        return new TankState(radar, _turretDirection, _direction, _speed, _ammo);
    }

    public abstract Action run(TankState state);
}
