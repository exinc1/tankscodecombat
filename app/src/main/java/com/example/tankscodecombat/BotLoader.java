package com.example.tankscodecombat;

import android.content.Context;
import android.util.Log;

import dalvik.system.DexClassLoader;

import java.io.File;

public class BotLoader {

    // loads a bot from a DEX file.
    public static Tank loadBot(Context context, String className, File dexFile) throws Exception {

        Log.d("debug", "loadBot() started, dex=" + dexFile.getAbsolutePath());

        // Optimization output folder
        File optimizedDir = context.getCodeCacheDir();

        DexClassLoader loader = new DexClassLoader(
                dexFile.getAbsolutePath(),       // DEX file
                optimizedDir.getAbsolutePath(),  // optimized output
                null,                            // no native libs
                context.getClassLoader()         // parent loader
        );

        // Load the class
        Class<?> botClass = loader.loadClass(className);

        // Check inheritance
        if (!Tank.class.isAssignableFrom(botClass)) {
            throw new IllegalArgumentException("Class '" + className + "' must extend Tank!");
        }

        Tank instance = (Tank) botClass.getDeclaredConstructor().newInstance();

        Log.d("debug", "loadBot() finished successfully!");

        return instance;
    }
}
