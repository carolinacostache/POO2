package main.java.oop2_project.report;

import main.java.oop2_project.model.Book;
import main.java.oop2_project.service.LibraryService;

import java.util.List;

public class ReportReserved extends Report {
    @Override
    public void generateReport(LibraryService libraryService) {
        try {
            List<Book> reservedBooks = libraryService.getActiveReservations(); // new method
            if (reservedBooks.isEmpty()) {
                System.out.println("No books are currently reserved.");
            } else {
                System.out.println("Currently Reserved Books:");
                for (Book book : reservedBooks) {
                    System.out.printf("%s, Reserved Until: %s\n",
                            book.getTitle(), book.getReservedUntil());
                }
            }
        } catch (Exception e) {
            System.out.println("Error generating reserved report: " + e.getMessage());
        }
    }
}
