package main.java.lab3_ex1;

public class Vaca implements Animal{
    private String name;

    public Vaca(String name){
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String makeSound() {
        return "Moo MOO moooooo!";
    }

}
