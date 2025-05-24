package main.java.lab12_ex1;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {

        BankAccount account = new BankAccount(0);

        ExecutorService executor = Executors.newFixedThreadPool(3);

        Runnable task1 = () -> {
            account.deposit(500);
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            account.withdrawl(3000);
        };

        Runnable task2 = () -> {
            account.deposit(100);
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            account.withdrawl(10);
        };

        Runnable task3 = () -> {
            account.deposit(60);
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            account.withdrawl(300);
        };

        for (int i = 0; i < 3 ; i++){
            executor.submit(task1);
            executor.submit(task2);
            executor.submit(task3);
        }

        executor.shutdown();

    }
}
