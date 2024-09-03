package com.github.interview_prep.privatefunctiontest;


import static org.junit.Assert.assertEquals;

import org.junit.Test;

import java.lang.reflect.Method;

public class MyClassTest {

    @Test
    public void testPrivateMethod() throws Exception {
        MyClass myClass = new MyClass();

        // Access the private method using reflection
        Method privateMethod = MyClass.class.getDeclaredMethod("privateMethod");
        privateMethod.setAccessible(true);

        // Invoke the private method
        Object result = privateMethod.invoke(myClass);

        // Assert the result
        assertEquals("Hello World", result);
    }
}