package com.github.interview_prep.builderpatterns

class Car {
    var color: String = "Red"
    var make: String = "Toyota"
    var model: String = "Camry"

    // ... other car properties
}

class CarBuilder {
    private val car = Car()

    fun setColor(color: String): CarBuilder {
        car.color = color
        return this
    }

    fun setMake(make: String): CarBuilder {
        car.make = make
        return this
    }

    fun setModel(model: String): CarBuilder {
        car.model = model
        return this
    }

    fun build(): Car = car
}

fun main() {
    val customCar =
        CarBuilder()
            .setColor("Blue")
            .setMake("Ford")
            .setModel("Mustang")
            .build()

    println(customCar.color) // Output: Blue
    println(customCar.make) // Output: Ford
    println(customCar.model) // Output: Mustang
}
