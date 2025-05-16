package main.java.lab11;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class Main {
    public static void main(String[] args) {
    Animal animal = new Animal() {
        @Override
        public void print() {
            System.out.println("BARK!");
        }
    };

    animal.print();
    System.out.println(animal.getClass().getName());
/*
    AFunctionalInterface aFunctionalInterface2 = new AFunctionalInterface() {

        @Override
        public void print(String message) {
            System.out.println(message);
        }
    };

 */
    AFunctionalInterface aFunctionalInterface2 = message -> System.out.println(message);
    aFunctionalInterface2.print("a message");
    Predicate<String> aPredicate;


    List<String> aList = List.of("A", "B", "C");

    Optional<Integer> result = aList.stream().filter(value -> value.equals("A"))
            .map(element -> {
                int a = 10;
                return a/2;
            })
            .distinct()
            .filter(value -> value.equals("A"))
            .findFirst();
            ///.toList();
    ///result.forEach(value -> System.out.println(value));

        aList.stream().forEach(value -> aFunctionalInterface2.print(value));

        aList.forEach(System.out::println);




    }
}
