package com.example.tankscodecombat.bots;

import com.example.tankscodecombat.*;

public class RandomBot2 extends Tank {
    @Override
    public Action run(Direction direction) {
        return new Action(Action.ActionType.FIRE);
    }
}
