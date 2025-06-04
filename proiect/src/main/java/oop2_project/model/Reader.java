package main.java.oop2_project.model;

import java.util.ArrayList;
import java.util.List;

public class Reader extends Member {
    private List<Book> borrowedBooks = new ArrayList<>();

    public Reader(int id, String name, String email) {
        super(id, name, email);
    }

    public Reader() {
        super();
    }

    public List<Book> getBorrowedBooks() {
        return new ArrayList<>(borrowedBooks);
    }

    public void borrowBook(Book book) {
        if (!borrowedBooks.contains(book)) {
            borrowedBooks.add(book);
        }
    }

    public void returnBook(Book book) {
        borrowedBooks.remove(book);
    }

    @Override
    public String toString() {
        return "Reader{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", borrowedBooksCount=" + borrowedBooks.size() +
                '}';
    }
}
