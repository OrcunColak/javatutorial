package com.colak.gof.singleton;

// See https://medium.com/@amlan.117/create-thread-safe-singleton-object-in-java-f4169649fc38
public class DoubleCheckedSingleton {
    private static volatile DoubleCheckedSingleton INSTANCE;

    private DoubleCheckedSingleton() {
    }

    public static DoubleCheckedSingleton getInstance() {
        if (INSTANCE == null) {
            synchronized (DoubleCheckedSingleton.class) {
                if (INSTANCE == null) {
                    INSTANCE = new DoubleCheckedSingleton();
                }
            }
        }
        return INSTANCE;
    }
}
