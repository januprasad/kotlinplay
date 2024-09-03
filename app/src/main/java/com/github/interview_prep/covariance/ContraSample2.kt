package com.github.interview_prep.covariance

interface Consumer<in T> {
    fun consume(t: T)
}

/**
 * List<out T> in Kotlin is equivalent to List<? extends T> in Java.
 *
 * List<in T> in Kotlin is equivalent to List<? super T> in Java
 */

class AnimalConsumer : Consumer<Animal> {
    override fun consume(animal: Animal) {
        println("Consuming animal: $animal")
    }
}

// This works because Animal is a supertype of Cat
fun feed(consumer: Consumer<Cat>) {
    consumer.consume(Cat())
}

fun main() {
    // This works because Animal is a supertype of Cat
    val animalConsumer: Consumer<Animal> = AnimalConsumer()
    feed(animalConsumer) // open this feed you will know

    val catConsumer: Consumer<Cat> = AnimalConsumer()
    feed(catConsumer)
}
