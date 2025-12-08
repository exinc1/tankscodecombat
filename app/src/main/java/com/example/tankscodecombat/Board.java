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
        if (!isLegal){ return 0; }

        // move tank
        m_tanksLocation[tankId].move(m_tanks[tankId].get_direction(), m_tanks[tankId].get_speed());

        switch (action.getType()) {
            case FIRE:
                if (getRadar(tankId).equals(m_tanks[tankId].get_direction())) {
                    return tankId + 1;
                }
            case RELOAD:
            case ROTATE:
            case ROTATE_TURRET:
            default:
                break;
        }

        return 3;
    }

    public static Direction getRadar(int tankId) {
        double angle = Math.atan2(m_tanksLocation[tankId].getY(), m_tanksLocation[tankId].getX());

        // convert radians to degrees
        return new Direction((int)Math.toDegrees(angle));
    }

    public static Location getLocation(int tankId) {
        return m_tanksLocation[tankId];
    }
}
