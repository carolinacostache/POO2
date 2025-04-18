package main.java.proiect;

import java.time.LocalDate;

public class ReportBorrowed extends Report {
    @Override
    public void generateReport(Library library) {
        System.out.println("Overdue borrowed books:");
        for (Book book : library.getBooks()) {
            if (book.getBorrower() != null && book.getReservedUntil() != null && book.getReservedUntil().isBefore(LocalDate.now())) {
                System.out.println("- " + book.getTitle() + " (borrowed by " + book.getBorrower().getName() + ")");
            }
        }
    }
}
