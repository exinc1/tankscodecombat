package com.example.tankscodecombat;

import android.util.Log;
import java.util.Random;

public class Board {
    Random ran = new Random();
    private final int SIZE = 100;
    private final Tank[] m_tanks;
    private static Location[] m_tanksLocation;
    public Board(Tank[] tanks) {
        m_tanks = tanks;

        m_tanksLocation = new Location[2];
        m_tanksLocation[0] = new Location(ran.nextInt(SIZE / 2) + SIZE / 4, 0);
        m_tanksLocation[1] = new Location(0, ran.nextInt(SIZE / 2) + SIZE / 4);

        Log.d("debug", "Initial tank positions: Tank0=" + m_tanksLocation[0] +
                " Tank1=" + m_tanksLocation[1]);
    }

    public int tankActionToBoard(int tankId, Action action, boolean isLegal) {

        if (!isLegal) return 0;

        Tank tank = m_tanks[tankId];
        Location loc = m_tanksLocation[tankId];

        if (action.getType() == Action.ActionType.MOVE) {
            loc.move(tank.get_direction(), tank.get_speed());

            Log.d("debug",
                    "Tank " + tankId +
                            " MOVE to " + loc +
                            " dir=" + tank.get_direction().getDegrees() +
                            " speed=" + tank.get_speed()
            );
        }
        else {
            Log.d("debug",
                    "Tank " + tankId +
                            " performed " + action.getType() +
                            " at " + loc
            );
        }

        if (action.getType() == Action.ActionType.FIRE) {
            Direction radar = getRadar(tankId);
            Direction turret = tank.get_turretDirection();

            int diff = Math.abs(radar.getDegrees() - turret.getDegrees()) % 360;
            if (diff <= 5 || diff >= 355) {
                Log.d("debug", "Tank " + tankId + " HIT!");
                return tankId + 1;
            }
        }

        return 3;
    }


    public static Location getLocation(int tankId) {
        return m_tanksLocation[tankId];
    }

    public static Direction getRadar(int tankId) {
        int enemy = 1 - tankId;
        double dx = m_tanksLocation[enemy].getX() - m_tanksLocation[tankId].getX();
        double dy = m_tanksLocation[enemy].getY() - m_tanksLocation[tankId].getY();
        return new Direction((int)Math.toDegrees(Math.atan2(dy, dx)));
    }
}
