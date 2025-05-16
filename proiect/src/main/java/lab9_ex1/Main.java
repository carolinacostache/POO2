package main.java.lab9_ex1;

import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        HashMap<String, Documnet> aMap = new HashMap<>();

        aMap.put("first", new Report("Sales Report"));
        System.out.println(aMap);
        aMap.put("second", new Report("Buying Report"));
        System.out.println(aMap);

    }

}
