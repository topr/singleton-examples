package com.ubs.example.singleton;

public class EagerlyInitializedSingleton {

    private static final EagerlyInitializedSingleton INSTANCE = new EagerlyInitializedSingleton();

    private EagerlyInitializedSingleton() {}

    public static EagerlyInitializedSingleton getInstance() {
        return INSTANCE;
    }

    public void performOperation(String argument) {
        // ...
    }
}
