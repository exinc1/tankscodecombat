package com.example.tankscodecombat;

public class TankHandler {
    private Tank[] m_tanks;
    public TankHandler(Tank[] tanks){
        m_tanks = tanks;
    }
   public boolean action(int tankId, Action action){
       if (tankId < 0 || tankId >= m_tanks.length) return false;
       Tank tank = m_tanks[tankId];
        switch (action.getType())
        {
            case MOVE:
                return tank.move(Tank.Speed.values()[action.getParam()]);
            case FIRE:
                return tank.fire();
            case RELOAD:
                return tank.reload(Tank.Ammo.values()[action.getParam()]);
            case ROTATE:
                return tank.rotate(Tank.Direction.values()[action.getParam()]);
            case ROTATE_TURRET:
                return tank.rotateTurret(Tank.Direction.values()[action.getParam()]);
        }
       return false;
   }
}
