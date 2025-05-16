package main.java.lab11;

@FunctionalInterface
public interface AFunctionalInterface {
    void print(String message);

    static void staticMethod() {
        //do something
    }

    default void defaultMethod() {
        //do something
    }
}
