package com.example.tankscodecombat;

import org.mozilla.javascript.*;

public class JSBotTank extends Tank {

    private final Scriptable scope;
    private final Function runFunction;

    public JSBotTank(String jsCode) {
        Context cx = Context.enter();
        try {
            cx.setOptimizationLevel(-1); // REQUIRED on Android
            cx.setClassShutter(new ClassShutter() {
                @Override
                public boolean visibleToScripts(String className) {
                    return false;
                }
            });

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

            NativeObject jsDir = new NativeObject();
            jsDir.put("radar", jsDir, state.radar.getDegrees());
            jsDir.put("direction", jsDir, state.direction.getDegrees());
            jsDir.put("turret", jsDir, state.turret.getDegrees());
            jsDir.put("ammo", jsDir, state.ammo.getRange());
            jsDir.put("speed", jsDir, state.speed.getSpeedVal());

            Object result = runFunction.call(cx, scope, scope, new Object[]{ jsDir });
            return parseAction(result);
        } catch (Exception e) {
            return new Action(Action.ActionType.RELOAD);
        } finally {
            Context.exit();
        }
    }

    private Action parseAction(Object result) {
        if (!(result instanceof NativeObject)) {
            return new Action(Action.ActionType.RELOAD);
        }

        NativeObject obj = (NativeObject) result;

        Object typeObj = obj.get("type", obj);
        if (typeObj == null) {
            return new Action(Action.ActionType.RELOAD);
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
            // JS returned an invalid action name
            return new Action(Action.ActionType.RELOAD);
        }
    }

}
