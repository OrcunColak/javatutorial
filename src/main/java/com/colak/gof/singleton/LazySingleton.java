package com.colak.gof.singleton;

/**
 * See <a href="https://medium.com/@amlan.117/create-thread-safe-singleton-object-in-java-f4169649fc38">...</a>
 */
public class LazySingleton {

    private static LazySingleton instance;

    private LazySingleton() {}

    public synchronized static LazySingleton getInstance() {
        if (instance == null) {
            instance = new LazySingleton();
        }
        return instance;
    }
}
