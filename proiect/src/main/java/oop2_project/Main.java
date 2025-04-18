package main.java.oop2_project;

public class Main {
    public static void main(String[] args) {
        Library library = new Library();
        Service menu = new Service(library);
        menu.run();
    }
}
