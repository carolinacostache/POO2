package main.java.oop2_project.report;

import main.java.oop2_project.model.Book;
import main.java.oop2_project.service.LibraryService;

import java.time.LocalDate;
import java.util.List;

public class ReportBorrowed extends Report {
    @Override
    public void generateReport(LibraryService libraryService) {
        try {
            List<Book> books = libraryService.getAllBooks();
            System.out.println("Overdue borrowed books:");
            for (Book book : books) {
                if (book.getBorrower() != null &&
                        book.getReservedUntil() != null &&
                        book.getReservedUntil().isBefore(LocalDate.now())) {
                    System.out.printf("- %s (borrowed by %s), Due: %s\n",
                            book.getTitle(),
                            book.getBorrower().getName(),
                            book.getReservedUntil());
                }
            }
        } catch (Exception e) {
            System.out.println("Error generating borrowed report: " + e.getMessage());
        }
    }
}
