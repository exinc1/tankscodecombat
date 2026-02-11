package com.example.tankscodecombat;

public class TankState {

    public Direction radar;
    public Direction turret;
    public Direction direction;
    public Tank.Speed speed;
    public Tank.Ammo ammo;

    public TankState(Direction radar,
                     Direction turret,
                     Direction direction,
                     Tank.Speed speed,
                     Tank.Ammo ammo) {

        this.radar = radar;
        this.turret = turret;
        this.direction = direction;
        this.speed = speed;
        this.ammo = ammo;
    }
}
