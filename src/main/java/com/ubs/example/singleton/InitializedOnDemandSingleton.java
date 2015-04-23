package com.ubs.example.singleton;

public class InitializedOnDemandSingleton {

    private InitializedOnDemandSingleton() { }

    private static class SingletonHolder {
        private static final InitializedOnDemandSingleton INSTANCE = new InitializedOnDemandSingleton();
    }

    public static InitializedOnDemandSingleton getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public void performOperation(String argument) {
        // ...
    }
}
