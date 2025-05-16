package main.java.lab11_ex1;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Main {
        public static void main(String[] args) {

            List<String> books = List.of("red", "dorian gray", "istoria lumii", "Red", "Eu");
            List<String> booksResult = books.stream()
                    .filter(title -> title.length() > 2)
                    .map(title -> title.substring(0, 1).toUpperCase() + title.substring(1))
                    .distinct()
                    .limit(2)
                    .collect(Collectors.toList());

            booksResult.forEach(System.out::println);

            Predicate<String> aPredicate = title -> title.length() > 2;
            Consumer<String> aConsumer = title -> System.out.println("Titlu: " + title);
            Function<String, String> aFunction = title -> title.substring(0, 1).toUpperCase() + title.substring(1);
            BiFunction<String, String, String> aBiFunction = (title1, title2) -> title1 + title2;


            List<String> newResult = books.stream()
                    .filter(aPredicate::test)
                    .map(aFunction::apply)
                    .distinct()
                    .limit(2)
                    .collect(Collectors.toList());

            newResult.forEach(aConsumer::accept);

        }
}
