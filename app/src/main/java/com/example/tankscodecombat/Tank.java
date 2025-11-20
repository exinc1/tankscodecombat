package com.example.tankscodecombat;

public abstract class Tank {
    // Enums
    public enum Direction {
        NORTH, EAST, SOUTH, WEST;

        public Direction left() {
            return values()[(ordinal() + 3) % 4];
        }

        public Direction right() {
            return values()[(ordinal() + 1) % 4];
        }

        public Direction opposite() {
            return values()[(ordinal() + 2) % 4];
        }
    }
    public enum Ammo {
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
        private final int speedVal;
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

    public abstract Action run(Direction direction);
}
