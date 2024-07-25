package com.github.solid.longrunning;

public class LongRunningTaskBasic implements Runnable {
    @Override
    public void run() {
        // Long-running task
        try {
            Thread.sleep(5000); // Simulate long-running operation
            System.out.println("Task completed.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Thread thread = new Thread(new LongRunningTaskBasic());
        thread.start();
        
        System.out.println("Main thread running...");
    }
}