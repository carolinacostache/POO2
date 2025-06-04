package main.java.oop2_project.service;

import main.java.oop2_project.model.Book;
import main.java.oop2_project.model.Genre;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Service {
    private final LibraryService libraryService;
    private final Scanner scanner;

    public Service(LibraryService libraryService) {
        this.libraryService = libraryService;
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        while (true) {
            System.out.println("\n~~~ LIBRARY MENU ~~~");
            System.out.println("Welcome to my digital library!<3");
            System.out.println("0. Exit.");
            System.out.println("1. Add a new member to the library;");
            System.out.println("2. Display all the books available in the library;");
            System.out.println("3. Add a new book in the library;");
            System.out.println("4. Remove a book from the library;");
            System.out.println("5. Search books by author;");
            System.out.println("6. Borrow a book from the library;");
            System.out.println("7. Return a book to the library;");
            System.out.println("8. Reserve a book from the library;");
            System.out.println("9. Generate overdue borrowed book report;");
            System.out.println("10. Show reserved books;");
            System.out.println("11. Reset reservations;");
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.print("What would you like to do?");

            String input = scanner.nextLine();

            try {
                int choice = Integer.parseInt(input);
                switch (choice) {
                    case 1 -> addMember();
                    case 2 -> displayAllBooks();
                    case 3 -> addBook();
                    case 4 -> removeBook();
                    case 5 -> searchBooksByAuthor();
                    case 6 -> borrowBook();
                    case 7 -> returnBook();
                    case 8 -> reserveBook();
                    case 9 -> generateOverdueReport();
                    case 10 -> generateReservedReport();
                    case 11 -> resetReservations();
                    case 0 -> {
                        System.out.println("Goodbye!");
                        return;
                    }
                    default -> System.out.println("Please enter a number between 1 and 9");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number  between 1 and 9.");
            } catch (SQLException e) {
                System.out.println("Oopsi! Database error: " + e.getMessage());
            }
        }
    }

    private void displayAllBooks() throws SQLException {
        List<Book> books = libraryService.getAllBooks();
        if (books.isEmpty()) {
            System.out.println("Currently there are no books in the library :( ");
        } else {
            int i = 1;
            for (Book book : books) {
                System.out.println(i + ". " +book.getTitle() + " by " + book.getAuthor().getName() + ", Genre: " + book.getGenre() + ", Available: " + book.isAvailable());
                i++;
            }

        }
    }

    private void addBook() throws SQLException {
        System.out.print("What book do you want to add?\n");
        System.out.print("Title: ");
        String title = scanner.nextLine();
        System.out.print("Author: ");
        String authorName = scanner.nextLine();

        System.out.println("Please select the genre!");
        System.out.println("1. FICTION");
        System.out.println("2. NONFICTION");
        System.out.println("3. MYSTERY");
        System.out.println("4. SCIENCE");
        System.out.println("5. HISTORY");
        System.out.println("6. ROMANCE");
        System.out.println("7. KIDS");
        System.out.print("Genre: ");
        String genreChoice = scanner.nextLine();

        Genre bookGenre;
        switch (genreChoice) {
            case "1" -> bookGenre = Genre.FICTION;
            case "2" -> bookGenre = Genre.NONFICTION;
            case "3" -> bookGenre = Genre.MYSTERY;
            case "4" -> bookGenre = Genre.SCIENCE;
            case "5" -> bookGenre = Genre.HISTORY;
            case "6" -> bookGenre = Genre.ROMANCE;
            case "7" -> bookGenre = Genre.KIDS;
            default -> {
                System.out.println("Invalid genre choice. Defaulting to FICTION.");
                bookGenre = Genre.FICTION;
            }
        }

        try {
            libraryService.addBook(title, authorName, bookGenre.name());
            System.out.println("Book added successfully!");
        } catch (SQLException e) {
            System.out.println("Oopsi! Error adding book: " + e.getMessage());
        }
    }

    private void removeBook() throws SQLException {
        System.out.print("What book do you want to remove?\n");
        String bookTitle = scanner.nextLine();
        libraryService.removeBook(bookTitle);
        System.out.println("Book removed successfully.");
    }

    private void searchBooksByAuthor() throws SQLException {
        System.out.print("What author's work do you want to search?\n");
        String authorName = scanner.nextLine();
        List<Book> books = libraryService.findBooksByAuthor(authorName);
        if (books.isEmpty()) {
            System.out.println("No books found for this author :(");
        } else {
            System.out.println("Books by author " + authorName + ":");
            int i = 1;
            for (Book book : books) {
                System.out.println(i + ". " + book.getTitle());
                i++;
            }
        }
    }

    private void addMember() throws SQLException {
        System.out.print("Who is the new member you want to add?\n");
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.println("Select 1 or 2 for the role of the member.");
        System.out.println("1. Author");
        System.out.println("2. Reader");
        System.out.print("Choice: ");
        String roleChoice = scanner.nextLine();

        String role;
        switch (roleChoice) {
            case "1" -> role = "Author";
            case "2" -> role = "Reader";
            default -> {
                System.out.println("Invalid choice. Defaulting role to reader.");
                role = "Reader";
            }
        }

        try {
            libraryService.addMemberWithRole(name, email, role);
            System.out.println("New member added successfully!");
        } catch (SQLException e) {
            System.out.println("Oopsi. Failed to add member: " + e.getMessage());
        }
    }

    private void borrowBook() throws SQLException {
        System.out.print("What book do you want to borrow?\n");
        String bookName = scanner.nextLine();
        System.out.print("What is your name?");
        String readerName = scanner.nextLine();

        libraryService.borrowBook(bookName, readerName);
        System.out.println("Book borrowed successfully!");
    }

    private void returnBook() throws SQLException {
        System.out.print("What book do you want to return?\n");
        String bookName = scanner.nextLine();
        libraryService.returnBook(bookName);
        System.out.println("Book returned successfully!");
    }

    private void reserveBook() throws SQLException {
        System.out.print("What book do you want to reserve?\n");
        String bookName = scanner.nextLine();
        System.out.print("What is your name?");
        String readerName = scanner.nextLine();
        libraryService.reserveBook(bookName, readerName);
        System.out.println("Book reserved for 30 days successfully!");
    }

    private void generateOverdueReport() {
        libraryService.generateBorrowedBooksReport();
    }

    private void generateReservedReport() {
        libraryService.generateReservationsReport();

    }

    private void resetReservations() throws SQLException {
        libraryService.resetExpiredReservations();
        System.out.println("Expired reservations reset.");
    }
}
