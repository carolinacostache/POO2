package main.java.oop2_project.exception;

public class MissingBook extends Exception{
    public MissingBook(String message) {
        super(message);
    }
}