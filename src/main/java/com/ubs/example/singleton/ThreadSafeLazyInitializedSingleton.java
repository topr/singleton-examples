package com.ubs.example.singleton;

public class ThreadSafeLazyInitializedSingleton {

        private static volatile ThreadSafeLazyInitializedSingleton instance;

        private ThreadSafeLazyInitializedSingleton() { }

        public static ThreadSafeLazyInitializedSingleton getInstance() {
            if (instance == null ) {
                synchronized (ThreadSafeLazyInitializedSingleton.class) {
                    if (instance == null) {
                        instance = new ThreadSafeLazyInitializedSingleton();
                    }
                }
            }

            return instance;
        }

    public void performOperation(String argument) {
        // ...
    }
}
