package main.java.oop2_project.model;

public enum Genre {
    FICTION,
    NONFICTION,
    MYSTERY,
    SCIENCE,
    HISTORY,
    ROMANCE,
    KIDS;

    public static Genre fromString(String genreName) {
        if (genreName == null) return null;
        try {
            return Genre.valueOf(genreName.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
