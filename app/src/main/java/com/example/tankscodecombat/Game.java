package com.example.tankscodecombat;

import android.content.Context;
import android.util.Log;

public class Game {
    private final Context context;
    public final int MAX_NUMBER_OF_TURNS = 100;
    private final Tank[] m_tanks;
    private Logs[] m_logs;
    private final TankHandler m_tankHandler;

    public Game(Context context, Tank bot1, Tank bot2) throws Exception {
        Log.d("debug", "Game has started");

        this.context = context;

        m_tanks = new Tank[2];
        m_tanks[0] = bot1;
        m_tanks[1] = bot2;
        m_tankHandler = new TankHandler(m_tanks);
    }

    public int run() {
        m_logs = new Logs[MAX_NUMBER_OF_TURNS * 2];

        int turn = 0;
        int game_state = 3;
        for (turn = 0; turn < MAX_NUMBER_OF_TURNS; turn++) {

            Action action1;
            try {
                TankState state1 = m_tanks[0].getState(Board.getRadar(0), Board.getDistance(0));
                action1 = m_tanks[0].run(state1);
            } catch (Exception e) {
                action1 = new Action(Action.ActionType.RELOAD, 0);
            }
            game_state = m_tankHandler.action(0, action1);
            document(turn, 0, action1);

            if (game_state != 3) break;

            Action action2;
            try {
                TankState state2 = m_tanks[1].getState(Board.getRadar(1), Board.getDistance(1));
                action2 = m_tanks[1].run(state2);
            } catch (Exception e) {
                action2 = new Action(Action.ActionType.RELOAD, 0);
            }
            game_state = m_tankHandler.action(1, action2);
            document(turn, 1, action2);

            if (game_state != 3) break;
        }

        if (turn >= MAX_NUMBER_OF_TURNS)
        {
            Log.d("debug", "MAX_NUMBER_OF_TURNS reached");
            game_state = -1;
        }
        return game_state;
    }

    private void document(int turnIndex, int tankId, Action action) {
        int logIndex = turnIndex * 2 + tankId;
        if (logIndex >= 0 && logIndex < m_logs.length) {
            m_logs[logIndex] = new Logs(tankId, action, Board.getLocation(tankId), m_tanks[tankId].get_direction(), m_tanks[tankId].get_turretDirection(), m_tanks[tankId].getHealth());
        }
    }

    public Logs[] getLog() {
        return m_logs;
    }
}
