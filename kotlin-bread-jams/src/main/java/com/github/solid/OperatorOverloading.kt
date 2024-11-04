package com.github.solid

data class Point(
    val x: Int,
    val y: Int,
) {
//    operator fun plus(p: Point): Point = Point(x + p.x, y + p.y)

    operator fun minus(a: Int): Int = x - a

    operator fun plus(p: Point): Point = Point(x + p.x, y + p.y)
}

fun main() {
    val point1 = Point(10, 20)
    val point2 = Point(30, 40)
    val point3 = point1 + point2
    val point4 = point1 - 10
    println(point4) // prints Point(x=40, y=60)
}

// data class Point(val x: Int, val y: Int)

// operator fun Point.minus(other: Point) = Point(x - other.x, y - other.y)
