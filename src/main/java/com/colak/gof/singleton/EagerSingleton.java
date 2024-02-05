package com.colak.gof.singleton;

/**
 * See <a href="https://medium.com/@amlan.117/create-thread-safe-singleton-object-in-java-f4169649fc38">...</a>
 */
public class EagerSingleton {
    private static final EagerSingleton INSTANCE = new EagerSingleton();

    private EagerSingleton() {}

    public static EagerSingleton getInstance() {
        return INSTANCE;
    }
}
