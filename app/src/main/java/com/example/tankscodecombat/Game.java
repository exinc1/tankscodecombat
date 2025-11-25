package com.example.tankscodecombat;

import java.io.File;
import java.util.Map;
import java.util.HashMap;

public class Game {
    private Tank[] m_tanks;
    private Map<Integer, Action> m_document;

    private TankHandler tankHandler;

    // Constructor
    public Game(File file1, File file2) throws Exception {
        // load users tanks
        m_tanks = new Tank[2];
        m_tanks[0] = BotLoader.loadBot("tank1", file1);
        m_tanks[1] = BotLoader.loadBot("tank2", file2);

        tankHandler = new TankHandler(m_tanks);
        m_document = new HashMap<>();
    }

    // Run method
    public int run(int turns) {
        if(turns <= 0) return 0;

        int game_state = 0;
        for (int i = 0; i < turns; i++)
        {
            // run users code
            Action action1 = m_tanks[0].run(Board.getRadar(0));
            Action action2 = m_tanks[1].run(Board.getRadar(1));

            // if action is illegal or game ends break loop else document
            // 0 = illegal, 1 = tank 1 wins, 2 = tank 2 wins, 3 = nothing
            game_state = tankHandler.action(1, action1);
            if (game_state != 3) {
                if (game_state != 0) document(1, action1);
                break;
            }
            else {
                document(1, action1);
            }

            game_state = tankHandler.action(2, action2);
            if (game_state != 3) {
                if (game_state != 0) document(2, action2);
                break;
            }
            else {
                document(2, action2);
            }
        }
        return game_state;
    }
    private void document(int tankId, Action action)
    {
        m_document.put(tankId, action);
    }
    public Map<Integer, Action> getDocument(){
        return m_document;
    }
}
