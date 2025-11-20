package com.example.tankscodecombat.bots;

import com.example.tankscodecombat.*;

public class RandomBot1 extends Tank {
    @Override
    public Action run(Direction direction) {
        return new Action(Action.ActionType.MOVE, Speed.SLOW.getSpeedVal());
    }
}
