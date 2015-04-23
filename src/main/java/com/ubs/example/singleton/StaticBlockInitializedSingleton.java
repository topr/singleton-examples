package com.ubs.example.singleton;

public class StaticBlockInitializedSingleton {

    private static final StaticBlockInitializedSingleton INSTANCE;

    static {
        try {
            INSTANCE = new StaticBlockInitializedSingleton();
        } catch (Exception e) {
            throw new RuntimeException("OMG! Me haz a failure.", e);
        }
    }

    public static StaticBlockInitializedSingleton getInstance() {
        return INSTANCE;
    }

    private StaticBlockInitializedSingleton() { }

    public void performOperation(String argument) {
        // ...
    }
}
