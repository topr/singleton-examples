package com.ubs.example.singleton;

public class PublicFieldSingleton {

    public static final PublicFieldSingleton INSTANCE = new PublicFieldSingleton();

    private PublicFieldSingleton() { }

    public void performOperation(String argument) {
        // ...
    }
}
