package com.example.multithread_test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FixedThreadPoolExample {
    public static void main(String[] args) {
        // Create a fixed thread pool with 3 threads
        ExecutorService service = Executors.newFixedThreadPool(2);

        // Submit runnable tasks to the executor service
        for (int i = 0; i < 10; i++) {
            service.submit(new XYX(i));
        }

        // Shutdown the executor service
        service.shutdown();
    }
}

class Task implements Runnable {
    private final int taskId;

    public Task(int taskId) {
        this.taskId = taskId;
    }

    @Override
    public void run() {
        System.out.println("Executing task " + taskId + " by " + Thread.currentThread().getName());
        try {
            // Simulate some work with sleep
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.err.println("Completed task " + taskId + " by " + Thread.currentThread().getName());
    }
}