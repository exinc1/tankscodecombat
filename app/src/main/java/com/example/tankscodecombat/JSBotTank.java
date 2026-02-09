package com.example.tankscodecombat;

import org.mozilla.javascript.*;

public class JSBotTank extends Tank {

    private final Scriptable scope;
    private final Function runFunction;

    public JSBotTank(String jsCode) {
        Context cx = Context.enter();
        cx.setOptimizationLevel(-1); // REQUIRED on Android

        scope = cx.initStandardObjects();
        cx.evaluateString(scope, jsCode, "bot", 1, null);

        runFunction = (Function) scope.get("run", scope);
        Context.exit();
    }

    @Override
    public Action run(Direction direction) {
        Context cx = Context.enter();
        cx.setOptimizationLevel(-1);

        NativeObject jsDir = new NativeObject();
        jsDir.put("degrees", jsDir, direction.getDegrees());

        Object result = runFunction.call(cx, scope, scope, new Object[]{ jsDir });
        Context.exit();

        return parseAction(result);
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
