package com.example.multithread_test;

public class TestClass {

    public void setSum(int sum) {
        this.sum = sum;
    }

    private int sum = 0;

    public void calculate() {
        setSum(getSum() + 1);
    }

    public int getSum() {
        return sum;
    }

    // standard setters and getters

    public synchronized void synchronisedCalculate() {
        setSum(getSum() + 1);
    }
    public static int staticSum = 0;
    public static synchronized void syncStaticCalculate() {
        staticSum = staticSum + 1;
    }
    private int count = 0;
    public void performSynchronisedTask() {
        synchronized (this) {
            setCount(getCount()+1);
        }
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
