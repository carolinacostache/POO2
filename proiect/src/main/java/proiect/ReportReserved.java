package main.java.proiect;

import java.time.LocalDate;

public class ReportReserved extends Report {
    @Override
    public void generateReport(Library library) {
        System.out.println("Overdue reservations:");
        for (Book book : library.getBooks()) {
            if (book.getReservedUntil() != null && book.getReservedUntil().isBefore(LocalDate.now())) {
                System.out.println("- " + book.getTitle() + " (reservation expired)");
                book.clearReservation();
            }
        }
    }
}
