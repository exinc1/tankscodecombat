package com.example.tankscodecombat;

import android.content.Context;
import android.util.Log;

import dalvik.system.DexClassLoader;

import java.io.File;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class BotLoader {

    // Loads the first public class from the dex file
    public static Tank loadBot(Context context, File dexFile, File optimizedDir) throws Exception {
        Log.d("debug", "Loading bot from dex=" + dexFile.getAbsolutePath());

        // DexClassLoader requires a writable optimized directory
        DexClassLoader loader = new DexClassLoader(
                dexFile.getAbsolutePath(),
                optimizedDir.getAbsolutePath(),
                null,
                context.getClassLoader()
        );

        // Auto-detect class name from dex/jar (simplest: assume only one class)
        String className = findFirstClassName(dexFile);
        if (className == null) throw new Exception("No class found in dex file");

        Class<?> botClass = loader.loadClass(className);
        Object botInstance = botClass.getDeclaredConstructor().newInstance();

        return new BotTankWrapper(botInstance);
    }

    // Extract first class name from jar/dex
    private static String findFirstClassName(File file) throws Exception {
        if (file.getName().endsWith(".jar")) {
            try (JarFile jar = new JarFile(file)) {
                Enumeration<JarEntry> entries = jar.entries();
                while (entries.hasMoreElements()) {
                    String name = entries.nextElement().getName();
                    if (name.endsWith(".class")) {
                        return name.replace('/', '.').replace(".class", "");
                    }
                }
            }
        }
        // if already dex, user must know the class name — return null to force manual entry
        return null;
    }
}
