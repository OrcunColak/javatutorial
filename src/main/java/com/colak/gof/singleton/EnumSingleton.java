package com.colak.gof.singleton;

/**
 * See <a href="https://medium.com/@amlan.117/create-thread-safe-singleton-object-in-java-f4169649fc38">...</a>
 */
public enum EnumSingleton {

    INSTANCE;

    public void doSomething() {
        // ... Singleton logic ...
    }
}
