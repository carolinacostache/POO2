package main.java.lab12_ex2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        ChatRoom chatRoom = new ChatRoom();
        ExecutorService executor = Executors.newFixedThreadPool(5);

        String[] users = { "caro", "felix", "delia", "antonia", "eriq" };

        for (String user : users) {
            executor.submit(() -> {
                for (int i = 1; i <= 3; i++) {
                    chatRoom.postMessage(user, "msj " + i + " from " + user);
                    try {
                        Thread.sleep((long) (Math.random() * 100));
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            });
        }

        executor.shutdown();
        executor.awaitTermination(5, TimeUnit.SECONDS);

        System.out.println("\n--- All Messages ---");
        for (Message message : chatRoom.getMessages()) {
            System.out.println(message);
        }
    }
}