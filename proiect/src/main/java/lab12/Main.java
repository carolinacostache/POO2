package main.java.lab12;

public class Main {
    public static void main(String[] args) {
        System.out.printf("Started main()");

        ParallelThread task1 = new ParallelThread("ORDER-101");
        ParallelThread task2 = new ParallelThread("ORDER-102");

        task1.start();
        task2.start();

        System.out.printf("Finished main()");

    }
}
