package com.example.tankscodecombat;

public class Logs {
    private int tankId;
    private Action action;
    private Location location;
    private Direction tankDirection;
    private Direction turretDirection;
    private int health;

    public Logs() {
        // default constructor for loading
    }

    public Logs(int tankId, Action action, Location location, Direction tankDirection, Direction turretDirection, int health) {
        this.tankId = tankId;
        this.action = action;
        this.location = location;
        this.tankDirection = tankDirection;
        this.turretDirection = turretDirection;
        this.health = health;
    }

    public int get_tankId() {
        return tankId;
    }

    public Action get_action() {
        return action;
    }

    public Location get_location() {
        return location;
    }

    public Direction get_tankDirection() {
        return tankDirection;
    }

    public Direction get_turretDirection() {
        return turretDirection;
    }

    public int get_health() {
        return health;
    }

    public void set_tankId(int tankId) {
        this.tankId = tankId;
    }

    public void set_action(Action action) {
        this.action = action;
    }

    public void set_location(Location location) {
        this.location = location;
    }

    public void set_tankDirection(Direction tankDirection) {
        this.tankDirection = tankDirection;
    }

    public void set_turretDirection(Direction turretDirection) {
        this.turretDirection = turretDirection;
    }

    public void set_health(int health) {
        this.health = health;
    }

    @Override
    public String toString() {
        return "Log{" +
                "tankId=" + tankId +
                ", action=" + action +
                ", location=" + location +
                ", tankDirection=" + tankDirection.getDegrees() +
                ", turretDirection=" + turretDirection.getDegrees() +
                ", health=" + health +
                '}';
    }
}
