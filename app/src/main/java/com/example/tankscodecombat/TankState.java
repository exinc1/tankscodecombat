package com.example.tankscodecombat;

public class TankState {

    public Direction radar;
    public Direction turret;
    public Direction direction;
    public Tank.Speed speed;
    public Tank.Ammo ammo;
    public int ammoCount;
    public int health;
    public double distance;

    public TankState(Direction radar,
                     Direction turret,
                     Direction direction,
                     Tank.Speed speed,
                     Tank.Ammo ammo,
                     int ammoCount,
                     int health,
                     double distance) {

        this.radar = radar;
        this.turret = turret;
        this.direction = direction;
        this.speed = speed;
        this.ammo = ammo;
        this.ammoCount = ammoCount;
        this.health = health;
        this.distance = distance;
    }
}
