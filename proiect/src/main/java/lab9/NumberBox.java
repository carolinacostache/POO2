package main.java.lab9;

public class NumberBox<T extends Number>{
    private T number;

    NumberBox(T number) {
        this.number = number;
    }

    public T getNumber() {
        return this.number;
    }

    public double getDouble() {
        return this.number.doubleValue();
    }
}
