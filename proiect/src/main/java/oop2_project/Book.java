package main.java.oop2_project;
import java.time.LocalDate;

public class Book {
    private String title;
    private Author author;
    private Genre genre;
    private boolean isAvailable = true;
    private Reader borrower;
    private LocalDate reservedUntil;

    public Book(String title, Author author, Genre genre) {
        this.title = title;
        this.author = author;
        this.genre = genre;
    }

    public String getTitle() {
        return title;
    }

    public Author getAuthor() {
        return author;
    }

    public Genre getGenre() {
        return genre;
    }

    public boolean isAvailable() {
        return isAvailable && (reservedUntil == null || LocalDate.now().isAfter(reservedUntil));
    }

    public Reader getBorrower() {
        return borrower;
    }

    public LocalDate getReservedUntil() {
        return reservedUntil;
    }

    public void borrow(Reader reader) {
        this.isAvailable = false;
        this.borrower = reader;
    }

    public void returnBook() {
        this.isAvailable = true;
        this.borrower = null;
    }

    public void reserveFor30Days() {
        this.isAvailable = false;
        this.reservedUntil = LocalDate.now().plusDays(30);
    }

    public void clearReservation() {
        this.isAvailable = true;
        this.reservedUntil = null;
    }
}
