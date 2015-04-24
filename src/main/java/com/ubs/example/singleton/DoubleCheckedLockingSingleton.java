package com.ubs.example.singleton;

public class DoubleCheckedLockingSingleton {

        private static volatile DoubleCheckedLockingSingleton instance;

        private DoubleCheckedLockingSingleton() { }

        public static DoubleCheckedLockingSingleton getInstance() {
            if (instance == null ) {
                synchronized (DoubleCheckedLockingSingleton.class) {
                    if (instance == null) {
                        instance = new DoubleCheckedLockingSingleton();
                    }
                }
            }

            return instance;
        }

    public void performOperation(String argument) {
        // ...
    }
}
