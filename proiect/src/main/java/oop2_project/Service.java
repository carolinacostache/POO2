package main.java.oop2_project;
import java.util.Scanner;

public class Service {
    private Library library;
    private Scanner scanner;

    public Service(Library library) {
        this.library = library;
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        while (true) {
            System.out.println("\n~~~ LIBRARY MENU ~~~");
            System.out.println("Welcome to my digital library!<3");
            System.out.println("0. Exit.");
            System.out.println("1. Display all the books available in the library;");
            System.out.println("2. Add a new book in the library;");
            System.out.println("3. Remove a book from the library;");
            System.out.println("4. Search books by author;");
            System.out.println("5. Add a new reader;");
            System.out.println("6. Borrow a book from the library;");
            System.out.println("7. Return a book to the library;");
            System.out.println("8. Reserve a book from the library;");
            System.out.println("9. Generate overdue borrowed book report;");
            System.out.println("10. Reset reservations;");
            System.out.print("Choose: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            try {
                switch (choice) {
                    case 1 -> library.getBooks().forEach(book -> System.out.println(book.getTitle()+ " by "+ book.getAuthor().getName()));
                    case 2 -> {
                        System.out.print("What book do you want to add?");
                        System.out.print("\nTitle: ");
                        String title = scanner.nextLine();
                        System.out.print("Author: ");
                        String authorName = scanner.nextLine();
                        System.out.print("Please select the genre!");

                        Genre bookGenre = null;
                        System.out.println("1. FICTION");
                        System.out.println("2. NONFICTION");
                        System.out.println("3. MYSTERY");
                        System.out.println("4. SCIENCE");
                        System.out.println("5. HISTORY");
                        System.out.println("6. ROMANCE");
                        System.out.println("7. KIDS");

                        System.out.println("Genre:");
                        String genreChoice = scanner.next();
                        scanner.nextLine();
                        switch (genreChoice) {
                            case "1":
                                bookGenre = Genre.FICTION;
                                break;
                            case "2":
                                bookGenre = Genre.NONFICTION;
                                break;
                            case "3":
                                bookGenre = Genre.MYSTERY;
                                break;
                            case "4":
                                bookGenre = Genre.SCIENCE;
                                break;
                            case "5":
                                bookGenre = Genre.HISTORY;
                                break;
                            case "6":
                                bookGenre = Genre.ROMANCE;
                                break;
                            case "7":
                                bookGenre = Genre.KIDS;
                                break;
                            default:
                                System.out.println("Invalid genre choice. Defaulting to FICTION.");
                                bookGenre = Genre.FICTION;
                        }
                        Author author = new Author(authorName, "test@author.com");
                        library.addBook(new Book(title, author, bookGenre));
                    }
                    case 3 -> {
                        System.out.print("What book do you want to remove?");
                        System.out.print("Title: ");
                        library.removeBook(scanner.nextLine());
                    }
                    case 4 -> {
                        System.out.print("What book do you want to find?(Searching by author name)\n");
                        System.out.print("Author: ");
                        library.searchByAuthor(scanner.nextLine())
                                .forEach(book -> System.out.println(book.getTitle()));
                    }
                    case 5 -> {
                        System.out.print("Please provide the new reader's information.\n");
                        System.out.print("Name: ");
                        String name = scanner.nextLine();
                        System.out.print("Email: ");
                        String email = scanner.nextLine();
                        library.addReader(new Reader(name, email));
                        System.out.print("New reader added successfully!:)");

                    }
                    case 6 -> {
                        System.out.print("What book do you want to borrow?\n");
                        System.out.print("Title: ");
                        String title = scanner.nextLine();
                        System.out.print("Who is borrowing this book?\n");
                        System.out.print("Reader name: ");
                        String name = scanner.nextLine();
                        Reader reader = library.getReaders().stream()
                                .filter(r -> r.getName().equalsIgnoreCase(name))
                                .findFirst().orElse(null);
                        if (reader == null) {
                            System.out.println("Reader not found. Please register first!");
                        } else {
                            try {
                                library.borrowBook(title, reader);
                            } catch (MissingBook e) {
                                System.out.println("Error: " + e.getMessage());
                            }
                        }
                    }
                    case 7 -> {
                        System.out.print("What book do you want to return?\n");
                        System.out.print("Title: ");
                        String title = scanner.nextLine();
                        System.out.print("Who is returning this book?\n");
                        System.out.print("Reader name: ");
                        String name = scanner.nextLine();
                        Reader reader = library.getReaders().stream()
                                .filter(r -> r.getName().equalsIgnoreCase(name))
                                .findFirst().orElse(null);
                        if (reader == null) {
                            System.out.println("Reader not found. Please register first!");
                        } else {
                            try {
                                library.returnBook(title, reader);
                            } catch (MissingBook e) {
                                System.out.println("Error: " + e.getMessage());
                            }
                        }
                    }
                    case 8 -> {
                        System.out.print("What book would you like to reserve? ");

                        System.out.print("Title: ");
                        String title = scanner.nextLine();

                        try {
                            library.reserveBook(title);
                            System.out.println("Book reserved successfully for 30 days.");
                        } catch (MissingBook e) {
                            System.out.println("Error: " + e.getMessage());
                        }

                    }
                    case 9 -> new ReportBorrowed().generateReport(library);
                    case 10 -> new ReportReserved().generateReport(library);
                    case 0 -> {
                        System.out.println("Goodbye!");
                        return;
                    }
                    default -> System.out.println("Invalid choice");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}
