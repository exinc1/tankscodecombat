package com.example.tankscodecombat;

public class Logs {
    int _tankId;
    Action _action;
    Location _location;
    public Logs(int tankId, Action action, Location location) {
        _tankId = tankId;
        _action = action;
        _location = location;
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

    @Override
    public String toString() {
        return "Log{" +
                "_tankId=" + _tankId +
                ", _action=" + _action +
                ", _location=" + _location +
                '}';
    }
}
