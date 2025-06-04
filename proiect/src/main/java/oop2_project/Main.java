package main.java.oop2_project;

import main.java.oop2_project.connection.ConnectionProvider;
import main.java.oop2_project.service.LibraryService;
import main.java.oop2_project.service.Service;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        try (Connection connection = ConnectionProvider.getConnection()) {
            LibraryService libraryService = new LibraryService(connection);
            Service menu = new Service(libraryService);
            menu.run();
        } catch (Exception e) {
            System.err.println("Error starting application: " + e.getMessage());
        }
    }
}
