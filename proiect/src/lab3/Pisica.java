package lab3;

class Pisica implements Animal{
    private String name;
    public Pisica(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String makeSound() {
        return "Miau Miua Miauuu!";
    }



}
