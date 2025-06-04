package main.java.oop2_project.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionProvider {

    private ConnectionProvider() {}

    public static Connection getConnection() {
        String url = "jdbc:postgresql://localhost:5428/digital_library";
        String user = "carolina";
        String password = "carolina";

        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}