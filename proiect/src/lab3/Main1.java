package lab3;

public class Main1 {
    public static void main(String[] args) {
        Animal[] animals = {
                new Pisica("Felix"),
                new Vaca("Mioara"),
                new Soarece("Jerry")
        };

        for (int i = 0; i < animals.length; i++) {
            Animal animal = animals[i];
            System.out.println((i+1) + "." + animal.getClass().getSimpleName() + "(" + animal.getName() + "):" + animal.makeSound());
        }
    }
}
