package com.github.interview_prep.designpatterns

open class Vehicle(
    private val acceleration: Double,
)

open class OS private constructor(
    var name: String,
    var version: Int,
)

// class AndroidOS(
//    name: String,
//    version: Int,
//    val isRooted: Boolean,
// ) : OS(name, version, isRooted) {
//    constructor(name: String, version: Int, isRooted: Boolean, isBatterySmarter: Boolean) : this(name, version, isRooted) {
//        // ...
//    }
// }

fun main() {
//    AndroidOS(
//        "Android",
//        11,
//        true,
//    )
}

class Car(
    acceleration: Double,
) : Vehicle(acceleration)
