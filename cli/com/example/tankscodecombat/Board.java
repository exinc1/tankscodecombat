package com.example.tankscodecombat;

import java.util.Random;

public class Board {
    private final Random ran = new Random();
    private final int SIZE = 100;
    private final Tank[] m_tanks;
    private static Location[] m_tanksLocation;

    public Board(Tank[] tanks) {
        m_tanks = tanks;
        m_tanksLocation = new Location[2];
        int minDistance = 10;
        int maxDistance = 30;
        int x1, y1, x2, y2;
        do {
            x1 = ran.nextInt(SIZE);
            y1 = ran.nextInt(SIZE);
            x2 = ran.nextInt(SIZE);
            y2 = ran.nextInt(SIZE);
        } while (Math.hypot(x1 - x2, y1 - y2) < minDistance || Math.hypot(x1 - x2, y1 - y2) > maxDistance);
        m_tanksLocation[0] = new Location(x1, y1);
        m_tanksLocation[1] = new Location(x2, y2);
        
        m_tanks[0].set_direction(ran.nextInt(360));
        m_tanks[1].set_direction(ran.nextInt(360));
    }

    public int tankActionToBoard(int tankId, Action action, boolean canAct) {
        if (!canAct) return 0;

        Tank tank = m_tanks[tankId];
        Location loc = m_tanksLocation[tankId];

        if (action.getType() == Action.ActionType.MOVE) {
            loc.move(tank.get_direction(), tank.get_speed());
        }

        if (action.getType() == Action.ActionType.FIRE) {
            Direction radar = getRadar(tankId);
            Direction turret = tank.get_turretDirection();

            int diff = Math.abs(radar.getDegrees() - turret.getDegrees()) % 360;
            if (diff <= 10 || diff >= 350) {
                int enemy = 1 - tankId;
                int damage = tank.get_ammo().getDamage();
                m_tanks[enemy].setHealth(m_tanks[enemy].getHealth() - damage);
                if (m_tanks[enemy].getHealth() <= 0) {
                    return tankId + 1;
                }
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
    
    public static double getDistance(int tankId) {
        int enemy = 1 - tankId;
        double dx = m_tanksLocation[enemy].getX() - m_tanksLocation[tankId].getX();
        double dy = m_tanksLocation[enemy].getY() - m_tanksLocation[tankId].getY();
        return Math.hypot(dx, dy);
    }
}
