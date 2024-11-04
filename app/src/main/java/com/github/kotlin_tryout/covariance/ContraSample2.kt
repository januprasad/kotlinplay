package com.github.kotlin_tryout.covariance

interface IConsumer<in T> {
    fun consume(t: T)
}

/**
 * List<out T> in Kotlin is equivalent to List<? extends T> in Java.
 *
 * List<in T> in Kotlin is equivalent to List<? super T> in Java
 */

class AnimalConsumer : IConsumer<Animal> {
    override fun consume(animal: Animal) {
        println("Consuming animal: $animal")
    }
}

// This works because Animal is a supertype of Cat
fun feed(consumer: IConsumer<Cat>) {
    consumer.consume(Cat())
}

fun main() {
    // This works because Animal is a supertype of Cat
    val animalConsumer: IConsumer<Animal> = AnimalConsumer()
    feed(animalConsumer) // open this feed you will know

    val catConsumer: IConsumer<Cat> = AnimalConsumer()
    feed(catConsumer)
}
