package com.example.tankscodecombat;

import java.net.URL;
import java.net.URLClassLoader;
import java.io.File;

public class BotLoader {

    public static Tank loadBot(String className, File botFile) throws Exception {
        // Convert file to URL
        URL[] urls = { botFile.toURI().toURL() };
        URLClassLoader loader = new URLClassLoader(urls, BotLoader.class.getClassLoader());

        // Load the class
        Class<?> botClass = loader.loadClass(className);

        // Validate it's a Tank subclass
        if (!Tank.class.isAssignableFrom(botClass)) {
            throw new IllegalArgumentException("Class '" + className + "' must extend Tank!");
        }

        // Create instance
        Object instance = botClass.getDeclaredConstructor().newInstance();
        return (Tank) instance;
    }
}
