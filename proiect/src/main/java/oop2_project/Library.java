package main.java.oop2_project;
import java.util.*;
import java.time.LocalDate;


public class Library {
    private List<Book> books = new ArrayList<>();
    private Set<Reader> readers = new TreeSet<>(Comparator.comparing(Reader::getName));

    public List<Book> getBooks() { return books; }
    public Set<Reader> getReaders() { return readers; }

    public void addBook(Book book) {
        books.add(book);
    }

    public void removeBook(String title) throws MissingBook {
        Book toRemove = findBookByTitle(title);
        books.remove(toRemove);
    }

    public Book findBookByTitle(String title) throws MissingBook {
        return books.stream()
                .filter(book -> book.getTitle().equalsIgnoreCase(title))
                .findFirst()
                .orElseThrow(() -> new  MissingBook("Book not found: " + title));
    }

    public List<Book> searchByAuthor(String authorName) {
        return books.stream()
                .filter(book -> book.getAuthor().getName().equalsIgnoreCase(authorName))
                .toList();
    }

    public List<Book> searchByGenre(String genre) {
        try {
            Genre g = Genre.valueOf(genre.toUpperCase());
            return books.stream()
                    .filter(book -> book.getGenre() == g)
                    .toList();
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid genre: " + genre);
            return List.of();
        }
    }


    public void addReader(Reader reader) {
        readers.add(reader);
    }

    public void borrowBook(String title, Reader reader) throws MissingBook {
        Book book = findBookByTitle(title);
        if (book == null) {
            throw new MissingBook("The book is not available in the library.");
        }
        if (book.isAvailable()) {
            book.borrow(reader);
            reader.borrowBook(book);
            System.out.println("Book borrowed successfully!");
        } else {
            System.out.println("The book is currently unavailable.");
        }
    }

    public void returnBook(String title, Reader reader) throws MissingBook {
        Book book = findBookByTitle(title);

        if (book == null) {
            throw new MissingBook("The book was not found in the library.");
        }

        if (reader.getBorrowedBooks().contains(book)) {
            book.returnBook();
            reader.returnBook(book);
            System.out.println("Book returned successfully!");
        } else {
            System.out.println("Error: The reader did not borrow this book.");
        }
    }


    public void reserveBook(String title) throws MissingBook {
        Book book = findBookByTitle(title);
        if (book.isAvailable()) {
            book.reserveFor30Days();
        }
    }

    public void resetReservations() {
        for (Book book : books) {
            if (book.getReservedUntil() != null && LocalDate.now().isAfter(book.getReservedUntil())) {
                book.clearReservation();
                System.out.println("Reservation has been reset.");
            }
        }
    }

}
