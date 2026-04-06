package com.example.tankscodecombat;

public abstract class Tank {
    public enum Ammo {
        NONE(0, 0),
        BULLET(5, 999),
        SHOTGUN(3, 3),
        MISSILE(15, 1);
        
        private final int damage;
        private final int magazineSize;
        
        Ammo(int damage, int magazineSize) {
            this.damage = damage;
            this.magazineSize = magazineSize;
        }
        public int getDamage() { return damage; }
        public int getMagazineSize() { return magazineSize; }
    }
    
    public enum Speed { REVERSE(-1), STOP(0), SLOW(1), FAST(2);
        private final int speedVal;
        Speed(int speedVal) { this.speedVal = speedVal; }
        public int getSpeedVal() { return speedVal; }
    }

    private Speed _speed = Speed.STOP;
    private Direction _direction = new Direction(0);
    private Direction _turretDirection = new Direction(0);
    private Ammo _ammo = Ammo.BULLET;
    private int ammoCount = 999;
    private int health = 50;

    public Ammo get_ammo() { return _ammo; }
    public int get_ammoCount() { return ammoCount; }
    public Direction get_direction() { return _direction; }
    public Direction get_turretDirection() { return _turretDirection; }
    public Speed get_speed() { return _speed; }

    public void set_direction(int direction) { _direction.setDegrees(direction); }
    public void set_turretDirection(int direction) { _turretDirection.setDegrees(direction); }
    public void set_speed(int speed) {
        Speed[] speeds = Speed.values();
        if (speed >= 0 && speed < speeds.length) _speed = speeds[speed];
    }
    public void set_ammo(Ammo ammo) { _ammo = ammo; }
    public void set_ammoCount(int count) { ammoCount = count; }
    public int getHealth() { return health; }
    public void setHealth(int health) { this.health = Math.max(0, health); }
    
    public boolean canFire() {
        if (_ammo == Ammo.NONE) return false;
        if (_ammo.getMagazineSize() == 999) return true;
        return ammoCount > 0;
    }
    
    public void fire() {
        if (_ammo.getMagazineSize() != 999) {
            ammoCount--;
        }
    }
    
    public void reload() {
        ammoCount = _ammo.getMagazineSize();
    }

    TankState getState(Direction radar, double distance) {
        return new TankState(radar, _turretDirection, _direction, _speed, _ammo, ammoCount, health, distance);
    }

    public abstract Action run(TankState state);
}
