package com.github.kotlin_tryout.covariance

/**
Subtyping is reversed.
A supertype of a generic type can be used where the generic type is expected.
Keyword: in
Typically used for input positions (function parameters).
 */
interface AnimalCare<in T> {
    fun takeCareOf(animal: T): String
}

class Vet : AnimalCare<Animal> {
    override fun takeCareOf(animal: Animal): String = "Vet Must take care ${animal.name()}"
}

class Vet1 : AnimalCare<Animal> {
    override fun takeCareOf(animal: Animal): String = "Vet1 Must take care ${animal.name()}"
}

abstract class Animal {
    abstract fun name(): String
}

class Dog : Animal() {
    override fun name(): String = "Puppy"
}

class Cat : Animal() {
    override fun name() = "Veerappan"
}

fun main() {
    val animalCare: AnimalCare<Dog> = Vet()
    println(animalCare.takeCareOf(Dog()))

    val animalCare1: AnimalCare<Cat> = Vet1()
    println(animalCare1.takeCareOf(Cat()))
    val animals =
        mutableListOf(
            Cat(),
            Cat(),
        )
    val animalWrapper = AnimalWrapper<Animal>()
    animalWrapper.addAnimal(animals)
}

class AnimalWrapper<out T> {
    fun addAnimal(animal: Collection<Animal>) {
        animal.plus(Dog())
    }
}
