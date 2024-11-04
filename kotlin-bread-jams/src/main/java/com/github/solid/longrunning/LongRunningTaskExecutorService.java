package com.github.solid.longrunning;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LongRunningTaskExecutorService implements Runnable {
    private final Integer id;

    LongRunningTaskExecutorService(Integer id){

        this.id = id;
    }
    @Override
    public void run() {
        // Long-running task
        try {
            Thread.sleep(5000); // Simulate long-running operation
            System.out.println("Task "+ id +" completed.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        
        executor.submit(new LongRunningTaskExecutorService(101));
        executor.submit(new LongRunningTaskExecutorService(102));
        
        executor.shutdown(); // Initiates an orderly shutdown
    }
}
