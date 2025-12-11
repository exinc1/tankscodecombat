package com.example.tankscodecombat;

import java.lang.reflect.Method;

public class BotTankWrapper extends Tank {
    private final Object bot;
    private final Method runMethod;

    public BotTankWrapper(Object botInstance) throws Exception {
        this.bot = botInstance;
        this.runMethod = botInstance.getClass().getMethod("run", int.class);
    }

    @Override
    public Action run(Direction direction) {
        try {
            int degrees = direction.getDegrees();
            String actionStr = (String) runMethod.invoke(bot, degrees);
            return parseAction(actionStr);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private Action parseAction(String actionStr) {
        String[] parts = actionStr.split(":");
        Action.ActionType type = Action.ActionType.valueOf(parts[0]);
        int param = parts.length > 1 ? Integer.parseInt(parts[1]) : 0;
        return new Action(type, param);
    }
}

