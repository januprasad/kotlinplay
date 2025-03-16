package com.github.kotlin_tryout.calculator

interface Calculator {
    fun add(a: Double, b: Double): Double
    fun subtract(a: Double, b: Double): Double
    fun multiply(a: Double, b: Double): Double
    fun divide(a: Double, b: Double): Double
}

class SimpleCalculator : Calculator {
    override fun add(a: Double, b: Double): Double {
        return a + b
    }

    override fun subtract(a: Double, b: Double): Double {
        return a - b
    }

    override fun multiply(a: Double, b: Double): Double {
        return a * b
    }

    override fun divide(a: Double, b: Double): Double {
        if (b == 0.0) {
            throw IllegalArgumentException("Cannot divide by zero")
        }
        return a / b
    }
}

fun main() {
    val calculator: Calculator = SimpleCalculator()

    println("Addition: ${calculator.add(10.0, 5.0)}")
    println("Subtraction: ${calculator.subtract(10.0, 5.0)}")
    println("Multiplication: ${calculator.multiply(10.0, 5.0)}")
    try {
        println("Division: ${calculator.divide(10.0, 2.0)}")
        println("Division by zero: ${calculator.divide(10.0, 0.0)}")
    } catch (e: IllegalArgumentException) {
        println("Error: ${e.message}")
    }
}