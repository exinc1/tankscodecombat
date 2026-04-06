package com.example.tankscodecombat;

import org.mozilla.javascript.*;

public class JSBotTank extends Tank {
    private final Scriptable scope;
    private final Function runFunction;

    public JSBotTank(String jsCode) {
        Context cx = Context.enter();
        try {
            cx.setOptimizationLevel(-1);
            scope = cx.initStandardObjects();
            cx.evaluateString(scope, jsCode, "bot", 1, null);

            Object obj = scope.get("run", scope);
            if (!(obj instanceof Function)) {
                throw new RuntimeException("JS bot must define function run()");
            }
            runFunction = (Function) obj;
        } finally {
            Context.exit();
        }
    }

    @Override
    public Action run(TankState state) {
        Context cx = Context.enter();
        try {
            cx.setOptimizationLevel(-1);

            NativeObject jsState = new NativeObject();
            jsState.put("radar", jsState, state.radar.getDegrees());
            jsState.put("direction", jsState, state.direction.getDegrees());
            jsState.put("turret", jsState, state.turret.getDegrees());
            jsState.put("ammo", jsState, state.ammo.ordinal());
            jsState.put("ammoName", jsState, state.ammo.name());
            jsState.put("ammoCount", jsState, state.ammoCount);
            jsState.put("speed", jsState, state.speed.getSpeedVal());
            jsState.put("health", jsState, state.health);
            jsState.put("distance", jsState, (int)state.distance);

            Object result = runFunction.call(cx, scope, scope, new Object[]{ jsState });
            return parseAction(result);
        } catch (Exception e) {
            return new Action(Action.ActionType.RELOAD, 0);
        } finally {
            Context.exit();
        }
    }

    private Action parseAction(Object result) {
        if (!(result instanceof NativeObject)) {
            return new Action(Action.ActionType.RELOAD, 0);
        }

        NativeObject obj = (NativeObject) result;
        Object typeObj = obj.get("type", obj);
        if (typeObj == null) {
            return new Action(Action.ActionType.RELOAD, 0);
        }

        String type = typeObj.toString();
        int param = 0;

        if (obj.has("param", obj)) {
            Object p = obj.get("param", obj);
            if (p instanceof Number) {
                param = ((Number) p).intValue();
            }
        }

        try {
            return new Action(Action.ActionType.valueOf(type), param);
        } catch (IllegalArgumentException e) {
            return new Action(Action.ActionType.RELOAD, 0);
        }
    }
}
