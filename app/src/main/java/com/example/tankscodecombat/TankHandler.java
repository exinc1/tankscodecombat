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
            return 0;
        }

        Tank tank = m_tanks[tankId];
        boolean canAct = true;
        int result = 3;

        int param = action.getParam();
        switch (action.getType()) {
            case MOVE:
                Tank.Speed[] speeds = Tank.Speed.values();
                if (param < 0 || param >= speeds.length) param = 0;
                tank.set_speed(param);
                break;

            case RELOAD:
                Tank.Ammo[] ammoTypes = Tank.Ammo.values();
                if (param < 0 || param >= ammoTypes.length) param = 0;
                tank.set_ammo(ammoTypes[param]);
                tank.reload();
                break;

            case ROTATE:
                m_tanks[tankId].set_direction(action.getParam());
                break;

            case ROTATE_TURRET:
                m_tanks[tankId].set_turretDirection(action.getParam());
                break;

            case FIRE:
                if (!tank.canFire()) {
                    Log.d("debug", "Tank " + tankId + " cannot fire - no ammo");
                    return 0;
                }
                tank.fire();
                break;

            default:
                Log.d("debug", "Unknown action type: " + action.getType());
                return 0;
        }

        result = m_board.tankActionToBoard(tankId, action, canAct);

        if (result == 0) Log.d("debug", "Tank " + tankId + " action was illegal on the board: " + action.getType());
        else if (result == 1) Log.d("debug", "Tank 1 wins!");
        else if (result == 2) Log.d("debug", "Tank 2 wins!");

        return result;
    }
}
