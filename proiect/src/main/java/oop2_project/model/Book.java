package main.java.oop2_project.model;

import java.time.LocalDate;

public class Book {
    private int id;
    private String title;
    private Author author;
    private int authorId;
    private Genre genre;
    private boolean isAvailable = true;
    private Reader borrower;
    private Integer borrowerId;
    private LocalDate reservedUntil;

    public Book(int id, String title, int authorId, String genreName, boolean isAvailable, Integer borrowerId, LocalDate reservedUntil) {
        this.id = id;
        this.title = title;
        this.authorId = authorId;
        this.genre = Genre.fromString(genreName);
        this.isAvailable = isAvailable;
        this.borrowerId = borrowerId;
        this.reservedUntil = reservedUntil;
        this.author = null;
        this.borrower = null;
    }

    public Book(String title, Author author, Genre genre) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        if (author != null) {
            this.authorId = author.getId();
        }
    }

    public Book() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public Integer getBorrowerId() {
        return borrowerId;
    }

    public void setBorrowerId(Integer borrowerId) {
        this.borrowerId = borrowerId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
        if (author != null) {
            this.authorId = author.getId();
        }
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public boolean isAvailable() {
        return isAvailable && (reservedUntil == null || LocalDate.now().isAfter(reservedUntil));
    }

    public void setAvailable(boolean available) {
        this.isAvailable = available;
    }

    public Reader getBorrower() {
        return borrower;
    }

    public void setBorrower(Reader borrower) {
        this.borrower = borrower;
        if (borrower != null) {
            this.borrowerId = borrower.getId();
        } else {
            this.borrowerId = null;
        }
    }

    public LocalDate getReservedUntil() {
        return reservedUntil;
    }

    public void setReservedUntil(LocalDate reservedUntil) {
        this.reservedUntil = reservedUntil;
    }

    public void borrow(Reader reader) {
        this.isAvailable = false;
        setBorrower(reader);
    }

    public void returnBook() {
        this.isAvailable = true;
        setBorrower(null);
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
