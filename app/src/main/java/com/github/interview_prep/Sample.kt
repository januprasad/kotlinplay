package com.github.interview_prep

interface AnimalCare<in T> {
    fun takeCareOf(animal: T)
}

class Vet : AnimalCare<Animal> {
    override fun takeCareOf(animal: Animal) {
        // do something
    }
}

open class Animal {}

class Dog : Animal() {}
class Cat : Animal() {}

fun main() {
    val animalCare: AnimalCare<Dog> = Vet()
    animalCare.takeCareOf(Dog())

    val animalCare1: AnimalCare<Cat> = Vet()
    animalCare1.takeCareOf(Cat())
}