package com.ubs.example.singleton;

public class SingletonWithArgument {

    public enum Approach1 {

        INSTANCE;

        private String argument = null;

        public void init(String argument) {
            this.argument = argument;
        }

        public void performOperation() {
            System.out.println(argument);
        }
    }

    public enum Approach2 {

        INSTANCE;

        public void performOperation(String argument) {
            System.out.println(argument);
        }
    }
}
