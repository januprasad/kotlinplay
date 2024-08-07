package com.github.interview_prep.delgates

interface Mode {
    val color: String

    fun display()
}

class DarkMode(
    override val color: String,
) : Mode {
    override fun display() {
        println("Dark Mode..." + color)
    }
}

class LightMode(
    override val color: String,
) : Mode {
    override fun display() {
        println("Light Mode..." + color)
    }
}

class MyCustomMode(
    val mode: Mode,
) : Mode by mode

/*
class MyCustomMode(
    val mode: Mode,
) : Mode {
    override val color: String = mode.color

    override fun display() {
        mode.display()
    }
}
*/

fun main() {
    MyCustomModeService().doAction()
    MyCustomMode(LightMode("light")).display()
}

class MyCustomModeService {
    val mode: Mode = LightMode("light")

    fun doAction()  {
        mode.display()
    }
}
