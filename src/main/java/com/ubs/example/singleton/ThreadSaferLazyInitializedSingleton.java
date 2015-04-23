package com.ubs.example.singleton;

public class ThreadSaferLazyInitializedSingleton {

    private static ThreadSaferLazyInitializedSingleton instance = null;

    private ThreadSaferLazyInitializedSingleton() { }

    public static synchronized ThreadSaferLazyInitializedSingleton getInstance() {
        if (instance == null) {
            instance = new ThreadSaferLazyInitializedSingleton();
        }

        return instance;
    }

    public void performOperation(String argument) {
        // ...
    }
}
