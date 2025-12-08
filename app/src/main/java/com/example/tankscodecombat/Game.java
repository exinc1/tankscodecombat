package com.example.tankscodecombat;

import android.content.Context;
import android.util.Log;

import java.io.File;

public class Game {
    private Context context;
    public final int MAX_NUMBER_OF_TURNS = 100;
    private final Tank[] m_tanks;
    private Logs[] m_logs;
    private final TankHandler m_tankHandler;

    // Constructor
    public Game(Context context, File file1, File file2) throws Exception {
        Log.d("debug", "game has started");

        this.context = context;

        // load users tanks
        m_tanks = new Tank[2];
        m_tanks[0] = BotLoader.loadBot(context, "tank1", file1);
        m_tanks[1] = BotLoader.loadBot(context, "tank2", file2);
        m_tankHandler = new TankHandler(m_tanks);
    }

    // Run method
    public int run() {
        m_logs = new Logs[MAX_NUMBER_OF_TURNS * 2];

        int game_state = 0;
        for (int i = 0; i < MAX_NUMBER_OF_TURNS; i++)
        {
            // run users code
            Action action1 = m_tanks[0].run(Board.getRadar(0));
            Action action2 = m_tanks[1].run(Board.getRadar(1));

            // if action is illegal or game ends break loop else document
            // 0 = illegal, 1 = tank 1 wins, 2 = tank 2 wins, 3 = nothing
            game_state = m_tankHandler.action(1, action1);
            if (game_state != 3) {
                if (game_state != 0) document(i, 1, action1);
                break;
            }
            else {
                document(i, 1, action1);
            }

            game_state = m_tankHandler.action(2, action2);
            if (game_state != 3) {
                if (game_state != 0) document(i, 2, action2);
                break;
            }
            else {
                document(i, 2, action2);
            }
        }
        return game_state;
    }
    private void document(int index, int tankId, Action action)
    {
        m_logs[index] = new Logs(tankId, action, Board.getLocation(tankId));
    }
    public Logs[] getLog() {
        return m_logs;
    }
}
