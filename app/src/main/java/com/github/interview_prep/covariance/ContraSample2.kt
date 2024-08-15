package com.github.interview_prep.covariance

interface Consumer<in T> {
    fun consume(t: T)
}

class AnimalConsumer : Consumer<Animal> {
    override fun consume(animal: Animal) {
        println("Consuming animal: $animal")
    }
}

fun feed(consumer: Consumer<Cat>) {
    consumer.consume(Cat())
}

fun main() {
    val animalConsumer: Consumer<Animal> = AnimalConsumer()
    feed(animalConsumer) // This works because Animal is a supertype of Cat
}
