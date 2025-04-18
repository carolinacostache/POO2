package main.java.proiect;

import java.util.ArrayList;
import java.util.List;

public class Reader extends Member {
    private List<Book> borrowedBooks = new ArrayList<>();

    public Reader(String name, String email) {
        super(name, email);
    }

    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void borrowBook(Book book) {
        borrowedBooks.add(book);
    }

    public void returnBook(Book book) {
        borrowedBooks.remove(book);
    }
}

