package main.java.lab9_ex1;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class Collections {
    public static void main(String[] args) {
        HashMap<String, Integer> aMap = new HashMap<>();
        HashMap<String, Double> aLinkedMap = new LinkedHashMap<>();

        aMap.put("first", 1);
        System.out.println(aMap);
        aMap.put("first", 2);
        System.out.println(aMap);
        System.out.println(aMap.get("first"));

    }
}
