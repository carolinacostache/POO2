package main.java.lab9_ex1;

public class Documnet<T> {

    String name;
    private T content;
    public Documnet(T content) {
        this.content = content;
    }
    public T getContent() {
        return this.content;
    }

}

