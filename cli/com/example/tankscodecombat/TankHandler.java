package com.example.tankscodecombat;

public class TankHandler {
    private final Tank[] m_tanks;
    private Board m_board;

    public TankHandler(Tank[] tanks) {
        m_tanks = tanks;
        m_board = new Board(m_tanks);
    }

    public int action(int tankId, Action action) {
        if (tankId < 0 || tankId >= m_tanks.length) return 0;

        Tank tank = m_tanks[tankId];
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
                tank.set_direction(action.getParam());
                break;
            case ROTATE_TURRET:
                tank.set_turretDirection(action.getParam());
                break;
            case FIRE:
                if (!tank.canFire()) {
                    return 0;
                }
                tank.fire();
                break;
        }

        result = m_board.tankActionToBoard(tankId, action, true);
        return result;
    }
}
