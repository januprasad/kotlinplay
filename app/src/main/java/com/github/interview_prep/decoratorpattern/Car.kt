package com.github.interview_prep.decoratorpattern

// Component interface
fun interface Car {
    fun drive()
}

// Concrete component
class BasicCar : Car {
    override fun drive() {
        println("Move from A to B")
    }
}

// Extension function for Car interface
fun Car.decorate(initialize: () -> Unit): Car =
    object : Car {
        override fun drive() {
            initialize()
            this@decorate.drive()
        }
    }

fun Car.decorate1(initialize: () -> Unit): Car =
    Car {
        initialize()
        this@decorate1.drive()
    }

fun main() {
    // Create a basic car
    val myBasicCar: Car = BasicCar()

    // Decorate it to make it an offroad car
    val offroadCar: Car =
        myBasicCar.decorate {
            println("Configure offroad driving mode")
        }

    val sandRaceCar: Car =
        myBasicCar.decorate1 {
            println("Configure sandrace driving mode")
        }

    // Drive the offroad car
    offroadCar.drive()
    sandRaceCar.drive()
}
