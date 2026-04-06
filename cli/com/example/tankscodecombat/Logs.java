package com.example.tankscodecombat;

public class Logs {
    private int tankId;
    private Action action;
    private Location location;
    private Direction tankDirection;
    private Direction turretDirection;
    private int health;

    public Logs(int tankId, Action action, Location location, Direction tankDirection, Direction turretDirection, int health) {
        this.tankId = tankId;
        this.action = action;
        this.location = location;
        this.tankDirection = tankDirection;
        this.turretDirection = turretDirection;
        this.health = health;
    }

    public int get_tankId() { return tankId; }
    public Action get_action() { return action; }
    public Location get_location() { return location; }
    public Direction get_tankDirection() { return tankDirection; }
    public Direction get_turretDirection() { return turretDirection; }
    public int get_health() { return health; }
}
