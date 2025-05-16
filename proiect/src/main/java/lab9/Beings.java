package main.java.lab9;

public enum Beings {

    BIRD("A bird"),

    DOG("A dog"),

    CAT("A cat");

    private String description;

    Beings(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
