package com.example.tankscodecombat;
import java.util.Random;
import java.util.random.*;
public class Board {
    Random ran = new Random();
    private final int SIZE = 100;
    private final Tank[] m_tanks;
    private Location[] m_tanksLocation;
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

            case RELOAD:
            case ROTATE:
            case ROTATE_TURRET:
            default:
                break;
        }

        return 3;
    }
}
