package com.github.kotlin_tryout.dataclassoperations

fun main() {
    val iphone = Iphone("15.5")
    println(iphone.version)
    val ipadVersion = iphone.copy(version = "16.7")
    println(ipadVersion)
}

data class Iphone(
    override val version: String,
) : OS

interface OS {
    val version: String
}

// abstract class OS {
//    abstract val version: String
// }
