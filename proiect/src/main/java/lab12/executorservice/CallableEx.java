package main.java.lab12.executorservice;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class CallableEx {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        List<Callable<Integer>> list =  new ArrayList<>();
        list.add(() -> "apple".length());
        list.add(() -> "orange".length());
        list.add(() -> "peach".length());

        List<Future<Integer>> results = executor.invokeAll(list);

        for(Future<Integer> future: results){
            System.out.println(future.get());
        }

        Runnable task = () -> System.out.println("done");

        executor.invokeAll(list);
    }
}
