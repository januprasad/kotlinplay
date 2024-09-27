package com.github.solid

data class People(
    val name: String,
    val age: Int,
) {
    companion object {
        fun isAdultBoy(age: Int) = age > MIN_AGE
    }
}

fun main() {
    val jk = People("JK", 34)
    jk.isAdult()
    People.isAdultBoy(34)
}

const val MIN_AGE = 18

fun People.isAdult() = age > MIN_AGE
