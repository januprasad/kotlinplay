package com.github.solid

interface Shape {

    fun getArea(): Double
}

class Rectangle(val width: Double, val height: Double) : Shape {
    override fun getArea(): Double {
        require(width > 0.0) { "Rectangle width must be positive" }
        require(height > 0.0) { "Rectangle height must be positive" }
        return width * height
    }
}

class Square(val side: Double) : Shape {
    override fun getArea(): Double {
        require(side > 0.0) { "Square side must be positive" }
        return side * side
    }
}

fun main() {
    val r = Rectangle(0.0,12.0)
    println(r.getArea())
}