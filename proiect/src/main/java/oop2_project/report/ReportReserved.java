package main.java.oop2_project.report;

import main.java.oop2_project.model.Book;
import main.java.oop2_project.service.LibraryService;

import java.util.List;

public class ReportReserved extends Report {
    @Override
    public void generateReport(LibraryService libraryService) {
        try {
            List<Book> overdueBooks = libraryService.getBorrowedReservations();
            if (overdueBooks.isEmpty()) {
                System.out.println("No overdue reservations.");
            } else {
                System.out.println("Overdue Reservations:");
                for (Book book : overdueBooks) {
                    System.out.printf("%s (Book ID: %d), Reserved Until: %s\n",
                            book.getTitle(), book.getId(), book.getReservedUntil());
                }
            }
        } catch (Exception e) {
            System.out.println("Error generating reserved report: " + e.getMessage());
        }
    }
}
