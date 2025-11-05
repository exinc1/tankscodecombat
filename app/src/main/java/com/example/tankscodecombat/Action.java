package com.example.tankscodecombat;

public class Action {
    public enum ActionType { MOVE, ROTATE, ROTATE_TURRET, FIRE, RELOAD }
    private int m_param;
    private ActionType m_type;

    public Action(ActionType type, int param) {
        m_type = type;
        m_param = param;
    }

    public Action(ActionType type) {
        this(type, 0);
    }

    public ActionType getType() {
        return m_type;
    }

    public int getParam() {
        return m_param;
    }
}
