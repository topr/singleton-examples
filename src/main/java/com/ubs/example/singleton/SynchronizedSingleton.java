package com.ubs.example.singleton;

public class SynchronizedSingleton {

    private static SynchronizedSingleton instance = null;

    private SynchronizedSingleton() { }

    public static synchronized SynchronizedSingleton getInstance() {
        if (instance == null) {
            instance = new SynchronizedSingleton();
        }

        return instance;
    }

    public void performOperation(String argument) {
        // ...
    }
}
