package com.example.tankscodecombat;

import java.util.Map;
import java.util.HashMap;
import com.example.tankscodecombat.Action.ActionType;
import com.example.tankscodecombat.Tank.Direction;

public class Game {
    private Tank[] m_tanks;
    private Map<Integer, Action> m_document;

    // Constructor
    public Game(Tank[] tanks) {
        this.m_tanks = tanks;
        this.m_document = new HashMap<>();
    }

    // Run method
    public int run() {
        // TODO: implement this func
        return 0;
    }
    private Action getAction(int tankId, Direction direction){
        // TODO: implement this func
        return null;
    }
    private void document(int tankId, ActionType actionType)
    {
        // TODO: implement this func
    }
    public Map<Integer, Action> getDocument(){
        return m_document;
    }
}
