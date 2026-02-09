package com.example.tankscodecombat;

public class Logs {
    private int _tankId;
    private Action _action;
    private Location _location;
    private Direction _tankDirection;
    private Direction _turretDirection;

    public Logs(int tankId, Action action, Location location, Direction tankDirection, Direction turretDirection) {
        _tankId = tankId;
        _action = action;
        _location = location;
        _tankDirection = tankDirection;
        _turretDirection = turretDirection;
    }

    public int get_tankId() {
        return _tankId;
    }

    public Action get_action() {
        return _action;
    }

    public Location get_location() {
        return _location;
    }

    public Direction get_tankDirection() {
        return _tankDirection;
    }

    public Direction get_turretDirection() {
        return _turretDirection;
    }

    @Override
    public String toString() {
        return "Log{" +
                "_tankId=" + _tankId +
                ", _action=" + _action +
                ", _location=" + _location +
                ", _tankDirection=" + _tankDirection.getDegrees() +
                ", _turretDirection=" + _turretDirection.getDegrees() +
                '}';
    }
}
