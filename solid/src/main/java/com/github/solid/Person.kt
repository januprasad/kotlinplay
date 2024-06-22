package com.github.solid

data class Person(val id: Int, val name: String) {

    init {
        println("$id  $name")
    }
    constructor(name: String) : this(-1, name) {
        println("Secondary")
    }

}

fun main() {
    val p = Person(id = 10, "JK")
    val p1 = Person("Admin")
}