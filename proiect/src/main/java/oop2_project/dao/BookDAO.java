package main.java.oop2_project.dao;

import main.java.oop2_project.connection.ConnectionProvider;
import main.java.oop2_project.model.Author;
import main.java.oop2_project.model.Book;
import main.java.oop2_project.model.Genre;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookDAO implements GenericDAO<Book> {

    private final Connection connection;

    public BookDAO() {
        this.connection = ConnectionProvider.getConnection();
    }

    public void resetExpiredReservations() {
        String sql = "UPDATE book SET reserved_until = NULL, is_available = TRUE WHERE reserved_until < CURRENT_DATE";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Book> getBorrowedReservations() throws SQLException {
        String sql = "SELECT b.*, a.member_id AS author_id, m.name AS author_name, m.email AS author_email " +
                "FROM book b " +
                "JOIN author a ON b.author_id = a.member_id " +
                "JOIN member m ON a.member_id = m.id " +
                "WHERE b.reserved_until < CURRENT_DATE";

        List<Book> books = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                books.add(mapResultSetToBook(rs));
            }
        }
        return books;
    }

    public List<Book> findBorrowedBooksWithAuthors() {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT b.*, a.member_id AS author_id, m.name AS author_name, m.email AS author_email " +
                "FROM book b " +
                "JOIN author a ON b.author_id = a.member_id " +
                "JOIN member m ON a.member_id = m.id " +
                "WHERE b.borrower_id IS NOT NULL";

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                books.add(mapResultSetToBook(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    @Override
    public void save(Book book) throws SQLException {
        if (book.getId() == 0) {
            create(book);
        } else {
            update(book);
        }
    }

    private void create(Book book) throws SQLException {
        String sql = "INSERT INTO book (title, author_id, genre, is_available, borrower_id, reserved_until) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            fillPreparedStatement(stmt, book);
            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating book failed, no rows affected.");
            }
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    book.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating book failed, no ID obtained.");
                }
            }
        }
    }

    private void fillPreparedStatement(PreparedStatement stmt, Book book) throws SQLException {
        stmt.setString(1, book.getTitle());

        if (book.getAuthor() != null) {
            stmt.setInt(2, book.getAuthor().getId());
        } else {
            stmt.setInt(2, book.getAuthorId());
        }

        stmt.setString(3, book.getGenre().name());
        stmt.setBoolean(4, book.isAvailable());

        if (book.getBorrowerId() != null) {
            stmt.setInt(5, book.getBorrowerId());
        } else {
            stmt.setNull(5, Types.INTEGER);
        }

        if (book.getReservedUntil() != null) {
            stmt.setDate(6, Date.valueOf(book.getReservedUntil()));
        } else {
            stmt.setNull(6, Types.DATE);
        }
    }

    public Optional<Book> findByTitle(String title) throws SQLException {
        String sql = "SELECT b.*, a.member_id AS author_id, m.name AS author_name, m.email AS author_email " +
                "FROM book b " +
                "JOIN author a ON b.author_id = a.member_id " +
                "JOIN member m ON a.member_id = m.id " +
                "WHERE LOWER(b.title) = LOWER(?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, title);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapResultSetToBook(rs));
                }
            }
        }
        return Optional.empty();
    }

    public List<Book> findByAuthor(String authorName) throws SQLException {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT b.*, a.member_id AS author_id, m.name AS author_name, m.email AS author_email " +
                "FROM book b " +
                "JOIN author a ON b.author_id = a.member_id " +
                "JOIN member m ON a.member_id = m.id " +
                "WHERE LOWER(m.name) = LOWER(?) " +
                "ORDER BY b.title";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, authorName);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    books.add(mapResultSetToBook(rs));
                }
            }
        }
        return books;
    }

    @Override
    public List<Book> findAll() throws SQLException {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT b.*, a.member_id AS author_id, m.name AS author_name, m.email AS author_email " +
                "FROM book b " +
                "JOIN author a ON b.author_id = a.member_id " +
                "JOIN member m ON a.member_id = m.id " +
                "ORDER BY b.title";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                books.add(mapResultSetToBook(rs));
            }
        }
        return books;
    }

    @Override
    public Optional<Book> findByName(String name) {
        try {
            return findByTitle(name);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void update(Book book) throws SQLException {
        String sql = "UPDATE book SET title = ?, author_id = ?, genre = ?, is_available = ?, borrower_id = ?, reserved_until = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            fillPreparedStatement(stmt, book);
            stmt.setInt(7, book.getId());
            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated == 0) {
                throw new SQLException("Updating book failed, no rows affected.");
            }
        }
    }

    @Override
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM book WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted == 0) {
                throw new SQLException("Deleting book failed, no rows affected.");
            }
        }
    }

    private Book mapResultSetToBook(ResultSet rs) throws SQLException {
        Book book = new Book();
        book.setId(rs.getInt("id"));
        book.setTitle(rs.getString("title"));

        int authorId = rs.getInt("author_id");
        String authorName = rs.getString("author_name");
        String authorEmail = rs.getString("author_email");
        Author author = new Author(authorId, authorName, authorEmail);
        book.setAuthor(author);

        book.setGenre(Genre.valueOf(rs.getString("genre")));
        book.setAvailable(rs.getBoolean("is_available"));

        int borrowerId = rs.getInt("borrower_id");
        if (!rs.wasNull()) {
            book.setBorrowerId(borrowerId);
        } else {
            book.setBorrowerId(null);
        }

        Date reservedDate = rs.getDate("reserved_until");
        if (reservedDate != null) {
            book.setReservedUntil(reservedDate.toLocalDate());
        } else {
            book.setReservedUntil(null);
        }
        return book;
    }
}
