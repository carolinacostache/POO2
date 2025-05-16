package main.java.lab9;

public class Utils {

    public static <T>  void printArray(T[] arrayToPrint){
        for(T element : arrayToPrint){
            System.out.println(element);
        }
    }
}
