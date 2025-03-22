package main.java.lab3_ex1;

class Soarece implements Animal{
    private String name;

    public Soarece(String name){
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String makeSound() {
        return "Chit Chit Chit!";
    }

}
