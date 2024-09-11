package com.github.interview_prep.builderpatterns

class Bike {
    var color: String = "Red"
    var make: String = "Toyota"
    var model: String = "Camry"

    // ... other car properties
}

fun Bike.builder(): BikeBuilder = BikeBuilder(this)

class BikeBuilder(
    private val car: Bike,
) {
    fun setColor(color: String): BikeBuilder {
        car.color = color
        return this
    }

    fun setMake(make: String): BikeBuilder {
        car.make = make
        return this
    }

    fun setModel(model: String): BikeBuilder {
        car.model = model
        return this
    }

    fun build(): Bike = car
}

fun main() {
    val customCar =
        Bike()
            .builder()
            .setColor("Blue")
            .setMake("Ford")
            .setModel("Mustang")
            .build()

    println(customCar.color) // Output: Blue
    println(customCar.make) // Output: Ford
    println(customCar.model) // Output: Mustang
}
