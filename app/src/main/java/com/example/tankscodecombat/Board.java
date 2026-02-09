package com.example.tankscodecombat;

import java.util.Random;

public class Board {
    Random ran = new Random();
    private final int SIZE = 100;
    private final Tank[] m_tanks;
    private static Location[] m_tanksLocation;
    public Board(Tank[] tanks) {
        m_tanks = tanks;

        m_tanksLocation = new Location[2];
        m_tanksLocation[0] = new Location(ran.nextInt(SIZE/2) + SIZE/4, 0);
        m_tanksLocation[1] = new Location(0, ran.nextInt(SIZE/2) + SIZE/4);
    }
    public int tankActionToBoard(int tankId, Action action, boolean isLegal) {
        if (!isLegal) return 0;

        int idx = tankId - 1;

        m_tanksLocation[idx].move(
                m_tanks[idx].get_direction(),
                m_tanks[idx].get_speed()
        );

        switch (action.getType()) {
            case FIRE:
                if (getRadar(idx).equals(m_tanks[idx].get_direction())) {
                    return tankId; // winner
                }
                break;
            default:
                break;
        }
        return 3;
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
