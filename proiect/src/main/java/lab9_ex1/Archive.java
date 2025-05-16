package main.java.lab9_ex1;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Archive <T extends Documnet>{
    private Set<T> documents = new HashSet<>();
    public void addDocumnet(T doc){
        documents.add(doc);
    }

    public void getAll(){
        for(T doc : documents){
            doc.getContent();
        }
    }

    public static<T extends Documnet> void printList(List<T> docs){
        for(T doc : docs){
            System.out.println(doc);
        }
    }
}
