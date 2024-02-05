package com.colak.gof.singleton;

/**
 * See <a href="https://medium.com/@amlan.117/create-thread-safe-singleton-object-in-java-f4169649fc38">...</a>
 */
public class StaticBlockSingleton {
    private static final StaticBlockSingleton INSTANCE;

    static {
        INSTANCE = new StaticBlockSingleton();
    }

    private StaticBlockSingleton() {
    }

    public static StaticBlockSingleton getInstance() {
        return INSTANCE;
    }
}
