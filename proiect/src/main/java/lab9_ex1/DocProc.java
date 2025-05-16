package main.java.lab9_ex1;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class DocProc {
        public void printList(List<? extends Documnet> docs){
            for(Documnet doc : docs){
                doc.getContent();
            }

        }
}
