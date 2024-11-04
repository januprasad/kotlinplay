package com.github.solid.longrunning;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class LongRunningTaskCallable implements Callable<String> {
    @Override
    public String call() throws InterruptedException {
        // Long-running task
        Thread.sleep(5000); // Simulate long-running operation
        return "Task completed.";
    }

    public static void main(String[] args) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        
        Future<String> future = executor.submit(new LongRunningTaskCallable());
        
        try {
            System.out.println(future.get()); // Blocking call to get the result
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        
        executor.shutdown(); // Initiates an orderly shutdown
    }
}
