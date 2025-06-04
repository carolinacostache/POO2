package main.java.oop2_project.service;

import main.java.oop2_project.report.ReportBorrowed;
import main.java.oop2_project.report.ReportReserved;
import main.java.oop2_project.audit.AuditService;
import main.java.oop2_project.dao.AuthorDAO;
import main.java.oop2_project.dao.BookDAO;
import main.java.oop2_project.dao.ReaderDAO;
import main.java.oop2_project.model.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class LibraryService {
    private final BookDAO bookDAO;
    private final AuthorDAO authorDAO;
    //private final MemberDAO memberDAO;
    private final ReaderDAO readerDAO;
    private final AuditService auditService;
    private static final int RESERVATION_DAYS = 30;

    public LibraryService(Connection connection) {
        this.bookDAO = new BookDAO();
        this.authorDAO = new AuthorDAO();
        //this.memberDAO = new MemberDAO();
        this.readerDAO = new ReaderDAO();
        this.auditService = AuditService.getInstance();
    }


    public List<Book> getAllBooks() throws SQLException {
        List<Book> books = bookDAO.findAll();
        auditService.log("getAllBooks");
        return books;
    }

    public void addBook(String title, String authorName, String genre) throws SQLException {
        Optional<Author> authorOpt = authorDAO.findByName(authorName);
        if (authorOpt.isEmpty()) {
            throw new SQLException("This author doesn't exist in our library :(");
        }
        Book book = new Book();
        book.setTitle(title);
        book.setAuthor(authorOpt.get());
        book.setAuthorId(authorOpt.get().getId());
        book.setGenre(Genre.valueOf(genre.toUpperCase()));
        book.setAvailable(true);
        bookDAO.save(book);
        auditService.log("addBook: title=" + title + ", authorName=" + authorName + ", genre=" + genre);
    }

    public void removeBook(String bookTitle) throws SQLException {
        Optional<Book> bookOpt = bookDAO.findByTitle(bookTitle);
        if (bookOpt.isPresent()) {
            bookDAO.delete(bookOpt.get().getId());
            auditService.log("removeBook: title=" + bookTitle);
            System.out.println("Book removed successfully.");
        } else {
            System.out.println("No book found with the title: " + bookTitle);
        }
    }

    public List<Book> findBooksByAuthor(String authorName) throws SQLException {
        Optional<Author> authorOpt = authorDAO.findByName(authorName);
        if (authorOpt.isEmpty()) return List.of();
        List<Book> books = bookDAO.findByAuthor(authorOpt.get().getName());
        auditService.log("findBooksByAuthor: authorName=" + authorName);
        return books;
    }

    public void addMemberWithRole(String name, String email, String role) throws SQLException {
        switch (role.toLowerCase()) {
            case "author":
                Author author = new Author();
                author.setName(name);
                author.setEmail(email);
                authorDAO.save(author);
                break;
            case "reader":
                Reader reader = new Reader();
                reader.setName(name);
                reader.setEmail(email);
                readerDAO.save(reader);
                break;
            default:
                throw new SQLException("Unknown role: " + role);
        }
        auditService.log("addMemberWithRole: name=" + name + ", email=" + email + ", role=" + role);
    }

    public void borrowBook(String bookTitle, String readerName) throws SQLException {
        Optional<Book> bookOpt = bookDAO.findByTitle(bookTitle);
        if (bookOpt.isEmpty()) throw new SQLException("Book not found: " + bookTitle);

        Optional<Reader> readerOpt = readerDAO.findByName(readerName);
        if (readerOpt.isEmpty()) throw new SQLException("Reader not found: " + readerName);

        Book book = bookOpt.get();
        book.setAvailable(false);
        book.setBorrowerId(readerOpt.get().getId());
        book.setReservedUntil(null);
        bookDAO.update(book);
        auditService.log("borrowBook: bookName=" + bookTitle + ", readerName=" + readerName);
    }

    public void returnBook(String bookTitle) throws SQLException {
        Optional<Book> bookOpt = bookDAO.findByTitle(bookTitle);
        if (bookOpt.isEmpty()) throw new SQLException("Book not found: " + bookTitle);

        Book book = bookOpt.get();
        book.setAvailable(true);
        book.setBorrowerId(null);
        book.setReservedUntil(null);
        bookDAO.update(book);
        auditService.log("returnBook: bookTitle=" + bookTitle);
    }

    public void reserveBook(String bookTitle, String readerName) throws SQLException {
        Optional<Book> bookOpt = bookDAO.findByTitle(bookTitle);
        if (bookOpt.isEmpty()) throw new SQLException("Book not found: " + bookTitle);
        Book book = bookOpt.get();

        if (!book.isAvailable()) {
            throw new SQLException("Book is not available for reservation: " + bookTitle);
        }

        Optional<Reader> readerOpt = readerDAO.findByName(readerName);
        if (readerOpt.isEmpty()) throw new SQLException("Reader not found: " + readerName);

        book.setAvailable(false);
        book.setBorrowerId(readerOpt.get().getId());
        book.setReservedUntil(LocalDate.now().plusDays(RESERVATION_DAYS));
        bookDAO.update(book);
        auditService.log("reserveBook: title=" + bookTitle + ", reservedBy=" + readerName);
    }

    public void resetExpiredReservations() throws SQLException {
        bookDAO.resetExpiredReservations();
        auditService.log("resetExpiredReservations");
    }

    public List<Book> getBorrowedReservations() throws SQLException {
        List<Book> overdueBooks = bookDAO.getBorrowedReservations();
        auditService.log("getBorrowedReservations");
        return overdueBooks;
    }

    public void generateReservationsReport() {
        ReportReserved report = new ReportReserved();
        report.generateReport(this);
        auditService.log("generateReservationsReport");
    }

    public void generateBorrowedBooksReport() {
        ReportBorrowed report = new ReportBorrowed();
        report.generateReport(this);
        auditService.log("generateBorrowedBooksReport");
    }

    public List<Book> getActiveReservations() throws SQLException {
        return bookDAO.getActiveReservations();
    }

}
