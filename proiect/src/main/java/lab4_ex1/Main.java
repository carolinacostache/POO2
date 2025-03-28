package main.java.lab4_ex1;

import main.java.lab4_ex1.Classroom;

public class Main {
    public static void main(String[] args) {
        Student[] students = {
                new Student("Alice", 14, 9),
                new Student("Bob", 15, 10),
                new Student("Charlie", 14, 1)
        };

        Classroom[] classrooms = {
                new Classroom(1, 30),
                new Classroom(2, 25),
                new Classroom(3, 20)
        };

        String[] subjects = {"Math", "Science", "History", "English", "Physical Education"};
        Schedule schedule = new Schedule(subjects);

        School school = new School(students);

        StudentReport studentReport = new StudentReport(students);
        studentReport.generate(students);
        studentReport.print();

        SchoolSummaryReport schoolSummaryReport = new SchoolSummaryReport();
        schoolSummaryReport.generate(classrooms);
        schoolSummaryReport.print();
    }
}