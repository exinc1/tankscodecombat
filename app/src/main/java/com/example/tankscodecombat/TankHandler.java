package com.example.tankscodecombat;

import android.util.Log;

public class TankHandler {
    private final Tank[] m_tanks;
    private Board m_board;

    public TankHandler(Tank[] tanks) {
        m_tanks = tanks;
        m_board = new Board(m_tanks);
    }

    public int action(int tankId, Action action) {
        if (tankId < 0 || tankId >= m_tanks.length) {
            Log.d("debug", "Invalid tankId: " + tankId);
            return 0; // illegal
        }

        Tank tank = m_tanks[tankId];
        boolean success = true; // assume legal, Board will validate
        int result = 3; // default = ongoing

        // Ensure params are safe
        int param = action.getParam();
        switch (action.getType()) {
            case MOVE:
                Tank.Speed[] speeds = Tank.Speed.values();
                if (param < 0 || param > speeds.length) param = 0;
                tank.set_speed(param);
                break;

            case RELOAD:
                Tank.Ammo[] ammoTypes = Tank.Ammo.values();
                if (param < 0 || param >= ammoTypes.length) param = 0;
                break;

            case ROTATE:
                m_tanks[tankId].set_direction(action.getParam());
                break;

            case ROTATE_TURRET:
                m_tanks[tankId].set_turretDirection(action.getParam());
                break;

            case FIRE:
                // no change, param is degrees or unused
                break;

            default:
                Log.d("debug", "Unknown action type: " + action.getType());
                return 0; // illegal
        }

        // Call Board to actually perform the action
        result = m_board.tankActionToBoard(tankId, action, success);

        // Logging
        if (result == 0) Log.d("debug", "Tank " + tankId + " action was illegal on the board: " + action.getType());
        else if (result == 1) Log.d("debug", "Tank 1 wins!");
        else if (result == 2) Log.d("debug", "Tank 2 wins!");

        return result;
    }
}
