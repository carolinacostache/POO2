# Digital Library 📚 – OOP Java Project 

## Overview 🔍

This is my Java project developed for the **Advanced Object-Oriented Programming** course.
The application simulates a digital library system where users can manage books, members, borrowed books and reservations. It also includes database persistence and an audit service for tracking user actions.
The project emphasizes clean OOP principles, proper architecture separation, and data persistence using JDBC with PostgreSQL.

## Stage 1️⃣ – Status
This stage focused on building the core logic.

✅ Core features implemented

✅ Simple classes with private/protected attributes and accessor methods (getters/setters)

✅ At least 2 different collections capable of managing the defined objects (List, Set, etc.), with at least one being sorted

✅ Use of inheritance to create additional classes and include them within the collections

✅ At least one service class that exposes the system's operations

✅ A Main class from which calls to the services are made

✅ Implements core OOP principles: inheritance, encapsulation, and exception handling

## Stage 2️⃣ - Status
This stage adds database connectivity via JDBC and a custom audit service.

✅ Database and JDBC
Integrated a PostgreSQL database with the following schema:
- member;
- author;
- reader;
- book;
- genre;
- audit_log;

✅ JDBC with DAO Pattern
To ensure maintainability and single-responsibility principles, the app uses the DAO (Data Access Object) design pattern. Each entity (Book, Reader, Author, etc.) has a corresponding DAO class that handles SQL logic. This isolates persistence logic from business logic, keeping LibraryService clean and focused on system behavior.

✅ CRUD Operations are implemented for the tables.

✅ Generic Singleton DAOs used for shared logic, ensuring only one instance of each service is created and used.

✅ Audit Service (CSV Logging)


## Actions 🎇
The following **actions** can be preformed in the application:

1. Add a new member (reader/author);
2. Display all the books available in the library;
3. Add a new book in the library;
4. Remove a book from the library;
5. Search books by author;
6. Borrow a book from the library;
7. Return a book to the library;
8. Reserve a book from the library;
9. Generate overdue borrowed book report;
10. Show reserved books;
11. Reset reservations.

Each of these actions is logged through the Audit Service.

##  Core Object Types 🧱

The system is built using the following classes:

- `Member` – Abstract base class for Reader and Author
- `Book` – Represents a book (title, author, genre, availability, borrowing/reservation info)
- `Author` – Inherits from `Member`; represents the book's author
- `Reader` – Inherits from `Member`; represents a library user
- `Genre` – Enum representing book genres (FICTION, NONFICTION, MYSTERY, etc.)
- `Report` – Abstract class for generating reports
- `ReportBorrow` – Generates overdue borrowed books report
- `ReportReserved` – Generates expired reservation report and resets availability
- `MissingBook` – Custom exception for handling missing books
- `Service` – Provides an interactive console menu
- `LibraryService` - Connects user commands to the underlying business logic and data storage
- `AuditService` - Handles CSV logging of user actions
- `ConnectionProvider` - Singleton class responsible for creating and managing the connection to the PostgreSQL database
- `BookDAO, ReaderDAO, AuthorDAO, MemberDAO` – DAO classes that encapsulate all SQL logic for their corresponding entities

##  Collections Used 🗂️

- `List<Book>` – Stores all books (unsorted)
- `Set<Reader>` – A `TreeSet` sorted by reader name






