package com.example.tankscodecombat;

import java.io.File;
import java.util.Map;
import java.util.HashMap;
import com.example.tankscodecombat.Action.ActionType;
import com.example.tankscodecombat.Tank.Direction;

public class Game {
    private Tank[] m_tanks;
    private Map<Integer, Action> m_document;

    private TankHandler tankHandler;

    // Constructor
    public Game(File file1, File file2) throws Exception {
        m_tanks = new Tank[2];
        m_tanks[0] = BotLoader.loadBot("tank1", file1);
        m_tanks[1] = BotLoader.loadBot("tank2", file2);
        tankHandler = new TankHandler(m_tanks);
        m_document = new HashMap<>();
    }

    // Run method
    public int run() {
        while(true)
        {
            Action action1 = m_tanks[0].run(m_tanks[0].radar());
            Action action2 = m_tanks[1].run(m_tanks[1].radar());

            // if action is illegal or game ends break loop else document
            if (!tankHandler.action(1, action1)) {
                break;
            }
            else {
                document(1, action1);
            }
            if (!tankHandler.action(2, action2)) {
                break;
            }
            else {
                document(2, action2);
            }
        }
        return 0;
    }
    private Action getAction(int tankId, Direction direction){
        // TODO: implement this func
        // maybe delete this
        return null;
    }
    private void document(int tankId, Action action)
    {
        m_document.put(tankId, action);
    }
    public Map<Integer, Action> getDocument(){
        return m_document;
    }
}
