package main.java.oop2_project.report;

import main.java.oop2_project.service.LibraryService;
import java.sql.SQLException;

public abstract class Report {
    public abstract void generateReport(LibraryService libraryService) throws SQLException;
}
