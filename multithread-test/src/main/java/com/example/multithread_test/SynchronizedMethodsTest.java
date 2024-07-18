package com.example.multithread_test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class SynchronizedMethodsTest {

    @Test
    public void givenMultiThread_whenNonSyncMethod() {
        ExecutorService service = Executors.newFixedThreadPool(3);
        TestClass summation = new TestClass();

        IntStream.range(0, 1000)
                .forEach(count -> service.submit(summation::synchronisedCalculate));

//        IntStream.range(0, 1000)
//                .forEach(count -> service.submit(summation::calculate)); //fails
        try {
            service.awaitTermination(1000, TimeUnit.MILLISECONDS);
            assertEquals(1000, summation.getSum());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    public void givenMultiThread_whenStaticSyncMethod() {
        ExecutorService service = Executors.newCachedThreadPool();

        IntStream.range(0, 1000)
                .forEach(count ->
                        service.submit(TestClass::syncStaticCalculate));
        try {
            service.awaitTermination(100, TimeUnit.MILLISECONDS);
            assertEquals(1000, TestClass.staticSum);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    public void givenMultiThread_whenBlockSync() {
        ExecutorService service = Executors.newFixedThreadPool(3);
        TestClass synchronizedBlocks = new TestClass();

        IntStream.range(0, 1000)
                .forEach(count ->
                        service.submit(synchronizedBlocks::performSynchronisedTask));
        try {
            service.awaitTermination(100, TimeUnit.MILLISECONDS);
            assertEquals(1000, synchronizedBlocks.getCount());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


    }
}