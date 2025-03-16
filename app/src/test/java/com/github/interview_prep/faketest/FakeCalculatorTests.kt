package com.github.interview_prep.faketest

import com.github.kotlin_tryout.calculator.Calculator
import junit.framework.TestCase.assertEquals
import kotlin.test.Test


class CalculatorTest {

    @Test
    fun testAdd() {
        val calculator: Calculator = FakeCalculator()
        assertEquals(8.0, calculator.add(5.0, 3.0))
        assertEquals(0.0, calculator.add(0.0, 0.0))
        assertEquals(-2.0, calculator.add(1.0, -3.0))
    }

    @Test
    fun testSubtract() {
        val calculator: Calculator = FakeCalculator()
        assertEquals(2.0, calculator.subtract(5.0, 3.0))
        assertEquals(0.0, calculator.subtract(0.0, 0.0))
        assertEquals(4.0, calculator.subtract(1.0, -3.0))
    }

    @Test
    fun testMultiply() {
        val calculator: Calculator = FakeCalculator()
        assertEquals(15.0, calculator.multiply(5.0, 3.0))
        assertEquals(0.0, calculator.multiply(0.0, 5.0))
        assertEquals(-3.0, calculator.multiply(1.0, -3.0))
    }

    @Test
    fun testDivide() {
        val calculator: Calculator = FakeCalculator()
        assertEquals(2.5, calculator.divide(5.0, 2.0))
        assertEquals(-2.0, calculator.divide(4.0, -2.0))
    }

}