package com.example.tankscodecombat;

public class Action {
        public enum ActionType { MOVE, ROTATE, ROTATE_TURRET, FIRE, RELOAD }
    private int param;
    private ActionType type;

    public Action(ActionType type, int param) {
        this.type = type;
        this.param = param;
    }

    public Action(ActionType type) {
        this(type, 0);
    }

    public ActionType getType() {
        return type;
    }

    public int getParam() {
        return param;
    }

    public void setType(ActionType type) {
        this.type = type;
    }

    public void setParam(int param) {
        this.param = param;
    }

    @Override
    public String toString() {
        return "Action{" +
                "type: " + type +
                ", param: " + param +
                '}';
    }
}
