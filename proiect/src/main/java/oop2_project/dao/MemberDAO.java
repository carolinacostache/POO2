package main.java.oop2_project.dao;

import main.java.oop2_project.connection.ConnectionProvider;
import main.java.oop2_project.model.Member;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MemberDAO implements GenericDAO<Member> {

    private final Connection connection;

    public MemberDAO() {
        this.connection = ConnectionProvider.getConnection();
    }


    @Override
    public void save(Member member) {
        if (member.getId() == 0) {
            create(member);
        } else {
            update(member);
        }
    }

    private void create(Member member) {
        String sql = "INSERT INTO member (name, email) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, member.getName());
            stmt.setString(2, member.getEmail());
            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating member failed, no rows affected.");
            }
            try (ResultSet keys = stmt.getGeneratedKeys()) {
                if (keys.next()) {
                    member.setId(keys.getInt(1));
                } else {
                    throw new SQLException("Creating member failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error creating member", e);
        }
    }


    @Override
    public Optional<Member> findByName(String name) {
        String sql = "SELECT id, name, email FROM member WHERE LOWER(name) = LOWER(?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, name);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Member member = new Member(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("email")
                    );
                    return Optional.of(member);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }


    @Override
    public List<Member> findAll() {
        List<Member> members = new ArrayList<>();
        String sql = "SELECT * FROM member";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                members.add(new Member(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error finding all members", e);
        }
        return members;
    }

    @Override
    public void update(Member member) {
        String sql = "UPDATE member SET name = ?, email = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, member.getName());
            stmt.setString(2, member.getEmail());
            stmt.setInt(3, member.getId());
            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Updating member failed, no rows affected.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error updating member", e);
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM member WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Deleting member failed, no rows affected.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting member", e);
        }
    }
}
