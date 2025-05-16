package main.java.lab9;

import java.util.List;

public class Wildcards {
    public void printList(List<?> aList){
        for(Object item : aList){
            System.out.println(item);
        }
    }
}
