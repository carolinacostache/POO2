package main.java.lab12;

public class ParallelThread extends Thread {
    private String orderId;

    public ParallelThread(String s) {
    }

    @Override
    public void run() {
        System.out.printf("Thread" + orderId + '\n');

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.printf("Ended Thread:" + orderId + '\n');

    }
}
