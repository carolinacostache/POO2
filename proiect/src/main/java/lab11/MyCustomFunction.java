package main.java.lab11;

@FunctionalInterface
public interface MyCustomFunction<T, R> {
    R mapToInteger(T aValue);
}
