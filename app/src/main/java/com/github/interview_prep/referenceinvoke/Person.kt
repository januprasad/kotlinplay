package com.github.interview_prep.referenceinvoke

class Person(
    val name: String,
)

fun main() {
    val person = Person("Bob")
    val name = Person::name.invoke(person) // Property reference
    val nameGetter = Person::name // Property reference

    println(name) // Output: Bob
    println(nameGetter(person)) // Output: Bob

    val personConstructor = ::Person // Constructor reference
    val person1 = personConstructor("Alice")
    println(person1.name) // Output: Alice
    println(::Person.invoke("Alice").name) // Output: Alice
}
