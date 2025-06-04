package main.java.oop2_project.model;

public class Member {
    protected int id;
    protected String name;
    protected String email;

    public Member(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {

        return name;
    }
    public String getEmail() {

        return email;
    }
    public void setName(String name) {

        this.name = name;
    }
    public void setEmail(String email) {

        this.email = email;
    }

    public Member() {
    }

}
