package main.java.oop2_project.dao;

import main.java.oop2_project.connection.ConnectionProvider;
import main.java.oop2_project.model.Author;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AuthorDAO implements GenericDAO<Author> {

    private final Connection connection;
    private final MemberDAO memberDAO;

    public AuthorDAO() {
        this.connection = ConnectionProvider.getConnection();
        this.memberDAO = new MemberDAO();
    }

    @Override
    public void save(Author author) {
        if (author.getId() == 0) {
            create(author);
        } else {
            update(author);
        }
    }

    @Override
    public Optional<Author> findByName(String name) {
        String sql = """
            SELECT m.id, m.name, m.email
            FROM author a
            JOIN member m ON a.member_id = m.id
            WHERE LOWER(m.name) = LOWER(?)
        """;

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, name);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Author author = new Author(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("email")
                    );
                    return Optional.of(author);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to find Author by name: " + name, e);
        }

        return Optional.empty();
    }

    @Override
    public List<Author> findAll() {
        List<Author> authors = new ArrayList<>();
        String sql = """
            SELECT m.id, m.name, m.email
            FROM author a
            JOIN member m ON a.member_id = m.id
        """;

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                authors.add(new Author(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to retrieve all Authors", e);
        }

        return authors;
    }

    @Override
    public void update(Author author) {
        memberDAO.update(author);
    }

    @Override
    public void delete(int id) {
        memberDAO.delete(id);
    }

    public boolean exists(int memberId) {
        String sql = "SELECT 1 FROM author WHERE member_id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, memberId);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to check existence of Author with member_id: " + memberId, e);
        }
    }

    private void create(Author author) {
        try {
            connection.setAutoCommit(false);
            memberDAO.save(author);

            String insertSql = "INSERT INTO author (member_id) VALUES (?)";
            try (PreparedStatement stmt = connection.prepareStatement(insertSql)) {
                stmt.setInt(1, author.getId());
                stmt.executeUpdate();
            }

            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
            throw new RuntimeException("Failed to create Author", e);
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}
