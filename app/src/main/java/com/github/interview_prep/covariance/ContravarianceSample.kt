package com.github.interview_prep.covariance

/**
Subtyping is reversed.
A supertype of a generic type can be used where the generic type is expected.
Keyword: in
Typically used for input positions (function parameters).
 */
interface AnimalCare<in T> {
    fun takeCareOf(animal: T)
}

class Vet : AnimalCare<Animal> {
    override fun takeCareOf(animal: Animal) {
        animal.name()
    }
}

class Vet1 : AnimalCare<Animal> {
    override fun takeCareOf(animal: Animal) {
        animal.name()
    }
}

abstract class Animal {
    abstract fun name()
}

class Dog : Animal() {
    override fun name() {
        println("Puppy")
    }
}

class Cat : Animal() {
    override fun name() {
        println("Veerappan")
    }
}

fun main() {
    val animalCare: AnimalCare<Dog> = Vet()
    animalCare.takeCareOf(Dog())

    val animalCare1: AnimalCare<Cat> = Vet1()
    animalCare1.takeCareOf(Cat())
}
