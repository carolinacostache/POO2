package main.java.oop2_project.dao;

import main.java.oop2_project.connection.ConnectionProvider;
import main.java.oop2_project.model.Reader;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ReaderDAO implements GenericDAO<Reader> {

    private final Connection connection;
    private final MemberDAO memberDAO;

    public ReaderDAO() {
        this.connection = ConnectionProvider.getConnection();
        this.memberDAO = new MemberDAO();
    }

    @Override
    public void save(Reader reader) {
        if (reader.getId() == 0) {
            create(reader);
        } else {
            update(reader);
        }
    }

    private void create(Reader reader) {
        try {
            connection.setAutoCommit(false);
            memberDAO.save(reader);

            String insertSql = "INSERT INTO reader (member_id) VALUES (?)";
            try (PreparedStatement stmt = connection.prepareStatement(insertSql)) {
                stmt.setInt(1, reader.getId());
                stmt.executeUpdate();
            }

            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
            throw new RuntimeException("Failed to create Reader", e);
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public List<Reader> findAll() {
        List<Reader> readers = new ArrayList<>();
        String sql = """
            SELECT m.id, m.name, m.email
            FROM reader r
            JOIN member m ON r.member_id = m.id
        """;

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                readers.add(new Reader(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to retrieve all Readers", e);
        }

        return readers;
    }

    @Override
    public void update(Reader reader) {
        memberDAO.update(reader);
    }

    @Override
    public void delete(int id) {
        memberDAO.delete(id);
    }

    public Optional<Reader> findByName(String name) {
        String sql = """
            SELECT m.id, m.name, m.email
            FROM reader r
            JOIN member m ON r.member_id = m.id
            WHERE LOWER(m.name) = LOWER(?)
        """;

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, name);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Reader reader = new Reader(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("email")
                    );
                    return Optional.of(reader);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }
}
