package com.example.tankscodecombat;

import android.content.Context;
import android.util.Log;

public class Game {
    private final Context context;
    public final int MAX_NUMBER_OF_TURNS = 100;
    private final Tank[] m_tanks;
    private Logs[] m_logs;
    private final TankHandler m_tankHandler;

    // Constructor
    public Game(Context context, Tank bot1, Tank bot2) throws Exception {
        Log.d("debug", "Game has started");

        this.context = context;

        // Load user tanks
        m_tanks = new Tank[2];
        m_tanks[0] = bot1;
        m_tanks[1] = bot2;
        m_tankHandler = new TankHandler(m_tanks);
    }

    // Run method
    public int run() {
        m_logs = new Logs[MAX_NUMBER_OF_TURNS * 2]; // two logs per turn

        int game_state = 3; // 3 = ongoing
        for (int turn = 0; turn < MAX_NUMBER_OF_TURNS; turn++) {

            // Run tank 1
            Action action1 = m_tanks[0].run(Board.getRadar(0));
            game_state = m_tankHandler.action(1, action1);
            document(turn, 1, action1);

            if (game_state != 3) break; // game ended

            // Run tank 2
            Action action2 = m_tanks[1].run(Board.getRadar(1));
            game_state = m_tankHandler.action(2, action2);
            document(turn, 2, action2);

            if (game_state != 3) break; // game ended
        }

        return game_state;
    }

    // Document each tank action in logs
    private void document(int turnIndex, int tankId, Action action) {
        int logIndex = turnIndex * 2 + (tankId - 1); // turn 0: tank1=0, tank2=1
        m_logs[logIndex] = new Logs(tankId, action, Board.getLocation(tankId));
    }

    public Logs[] getLog() {
        return m_logs;
    }
}
