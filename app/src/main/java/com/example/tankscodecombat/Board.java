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
        if (!isLegal) {
            Log.d("debug", "Tank " + tankId + " tried illegal action: " + action.getType());
            return 0;
        }

        // MOVE / ROTATE / RELOAD update the board
        m_tanksLocation[tankId].move(
                m_tanks[tankId].get_direction(),
                m_tanks[tankId].get_speed()
        );

        Log.d("debug", "Tank " + tankId + " performed " + action.getType() +
                " at location " + m_tanksLocation[tankId] +
                " facing " + m_tanks[tankId].get_direction().getDegrees());

        // FIRE check
        if (action.getType() == Action.ActionType.FIRE) {
            Direction radar = getRadar(tankId);
            Log.d("debug", "Tank " + tankId + " fired. Radar angle: " + radar.getDegrees() +
                    ", Turret facing: " + m_tanks[tankId].get_direction().getDegrees());

            if (radar.equals(m_tanks[tankId].get_direction())) {
                Log.d("debug", "Tank " + tankId + " HIT enemy! Winner!");
                return tankId; // winner
            } else {
                Log.d("debug", "Tank " + tankId + " FIRE missed.");
            }
        }

        return 3; // game ongoing
    }

    public static Direction getRadar(int idx) {
        int enemy = 1 - idx;

        double dx = m_tanksLocation[enemy].getX() - m_tanksLocation[idx].getX();
        double dy = m_tanksLocation[enemy].getY() - m_tanksLocation[idx].getY();

        double angle = Math.atan2(dy, dx);
        return new Direction((int) Math.toDegrees(angle));
    }

    public static Location getLocation(int tankId) {
        return m_tanksLocation[tankId];
    }
}
