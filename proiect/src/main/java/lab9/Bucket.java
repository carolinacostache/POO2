package main.java.lab9;

public class Bucket<T> {

    private T content;
    public Bucket(T content) {
        this.content = content;
    }
    public T getContent() {
        return this.content;
    }
}
