package com.github.interview_prep.covariance

interface Painter<in T> {
    fun onPaint(event: T)
}

interface Color {
    fun getColor(): String
}

class Red : Color {
    override fun getColor(): String = "Red"
}

class Blue : Color {
    override fun getColor(): String = "Blue"
}

class Green : Color {
    override fun getColor(): String = "Green"
}

fun paintWithColor(
    painter: Painter<Color>,
    color: Color,
) {
    painter.onPaint(color)
}

class WaterColor : Painter<Color> {
    override fun onPaint(event: Color) {
        println("paint ${event.getColor()} with water color")
    }
}

fun main() {
    val painter: Painter<Color> =
        object : Painter<Color> {
            override fun onPaint(event: Color) {
                println("Painting with color: ${event.getColor()}")
            }
        }

    painter.onPaint(Red())
    painter.onPaint(Blue())

    val waterColorPainter = WaterColor()
    paintWithColor(waterColorPainter, Red())
    paintWithColor(waterColorPainter, Blue())
    paintWithColor(waterColorPainter, Green())
}
