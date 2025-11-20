package com.example.tankscodecombat;

public class TankHandler {
    private final Tank[] m_tanks;
    private Board m_board;
    public TankHandler(Tank[] tanks){
        m_tanks = tanks;
        m_board = new Board(m_tanks);
    }
   public int action(int tankId, Action action) {
       if (tankId < 0 || tankId >= m_tanks.length) return 0;
       Tank tank = m_tanks[tankId];

       switch (action.getType())
       {
           case MOVE:
               return m_board.tankActionToBoard(tankId, action, tank.move(Tank.Speed.values()[action.getParam()]));
           case FIRE:
               return m_board.tankActionToBoard(tankId, action, tank.fire());
           case RELOAD:
               return m_board.tankActionToBoard(tankId, action, tank.reload(Tank.Ammo.values()[action.getParam()]));
           case ROTATE:
               return m_board.tankActionToBoard(tankId, action, tank.rotate(Tank.Direction.values()[action.getParam()]));
           case ROTATE_TURRET:
               return m_board.tankActionToBoard(tankId, action, tank.rotateTurret(Tank.Direction.values()[action.getParam()]));
        }
       return 3;
   }
}
