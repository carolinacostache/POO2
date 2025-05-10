package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionProvider {

    private ConnectionProvider() {}

    public static Connection getConnection() {
        String url = "jdbc:postgresql://localhost:5430/bank_db";
        String user = "manager";
        String password = "manager";

        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}