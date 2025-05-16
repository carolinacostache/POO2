package main.java.lab9;

public class Main {
    public static void main(String[] args) {
        System.out.println(Beings.BIRD);
        System.out.println(Beings.BIRD.getDescription());
        System.out.println(Beings.valueOf("CAT").getDescription());

    Integer anInt = 10;
    Bucket<Integer> anIntBucket = new Bucket<>(anInt);
    System.out.println(anIntBucket.getContent());


    Integer[] anIntArray = new Integer[3];
    anIntArray[0] = 1;
    anIntArray[1] = 2;
    anIntArray[2] = 3;
    Utils.printArray(anIntArray);


    NumberBox<Integer> numberBox = new NumberBox<>(Integer.parseInt("1"));
    System.out.println(numberBox.getNumber());
    System.out.println(numberBox.getDouble());

    }
}
