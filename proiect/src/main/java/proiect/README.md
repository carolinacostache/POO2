# Digital Library 📚 – OOP Java Project 

## Overview 🔍

This is the first stage of my Java project developed for the **Advanced Object-Oriented Programming** course.

The application simulates a digital library system where users can manage books, readers, borrowings, and reservations through a console-based menu.

## Stage 1️⃣ – Status

✅ Core features implemented
✅ Simple classes with private/protected attributes and accessor methods (getters/setters)
✅ At least 2 different collections capable of managing the defined objects (e.g., List, Set, Map, etc.), with at least one being sorted. If collections haven't been covered by the deadline, one-dimensional or two-dimensional arrays may be used instead
✅ Use of inheritance to create additional classes and include them within the collections
✅ At least one service class that exposes the system's operations
✅ A Main class from which calls to the services are made
✅ Uses inheritance, encapsulation, and exception handling


## Actions 🎇
The following **actions** can be preformed in the application:

1. Display all the books available in the library;
2. Add a new book in the library;
3. Remove a book from the library;
4. Search books by author;
5. Add a new reader;
6. Borrow a book from the library;
7. Return a book to the library;
8. Reserve a book from the library;
9. Generate overdue borrowed book report;
10. Reset reservations.

##  Core Object Types 🧱

The system is built using the following main classes:

- `Book` – Represents a book (title, author, genre, availability, borrowing/reservation info)
- `Author` – Inherits from `Member`; represents the book's author
- `Reader` – Inherits from `Member`; represents a library user
- `Genre` – Enum representing book genres (FICTION, NONFICTION, MYSTERY, etc.)
- `Library` – Manages collections of books and readers
- `Report` – Abstract class for generating reports
- `ReportBorrow` – Generates overdue borrowed books report
- `ReportReserved` – Generates expired reservation report and resets availability
- `MissingBook` – Custom exception for handling missing books
- `ServiceMenu` – Provides an interactive console menu

##  Collections Used 🗂️

- `List<Book>` – Stores all books (unsorted)
- `Set<Reader>` – A `TreeSet` sorted by reader name






