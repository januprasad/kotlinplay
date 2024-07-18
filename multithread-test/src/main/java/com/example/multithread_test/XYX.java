package com.example.multithread_test;

public class XYX implements Runnable {
    private final int i;

    public XYX(int i) {
        this.i = i;
    }

    @Override
    public void run() {
        System.out.println("Executing task " + i + " by " + Thread.currentThread().getName());
    }
}
