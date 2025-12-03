package com.github.kotlin_tryout.observerpattern

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlin.random.Random
import kotlin.random.nextInt

// Define an interface for the observer
interface Observer {
    fun update(value: Int)
}

// Define a concrete observer that implements the Observer interface
class ValueObserver(
    private val name: String,
) : Observer {
    override fun update(value: Int) {
        println("$name received value: $value")
    }
}

// Define a subject that emits values and notifies observers
class ValueSubject {
    private val observers = mutableListOf<Observer>()

    fun addObserver(observer: Observer) {
        observers.add(observer)
    }

    fun removeObserver(observer: Observer) {
        observers.remove(observer)
    }

    private val observable: Flow<Int> =
        flow {
            while (true) {
                emit(Random.nextInt(0..1000))
                delay(100)
            }
        }

    fun startObserving() {
        val observerJob =
            CoroutineScope(Dispatchers.IO).launch {
                observable.collect { value ->
                    notifyObservers(value)
                }
            }
    }

    private fun notifyObservers(value: Int) {
        for (observer in observers) {
            observer.update(value)
        }
    }
}

fun main() {
    val valueSubject = ValueSubject()
    val observer1 = ValueObserver("Observer 1")
    val observer2 = ValueObserver("Observer 2")

    valueSubject.addObserver(observer1)
    valueSubject.addObserver(observer2)

    valueSubject.startObserving()

    // Uncomment this line to simulate a value update
    // valueSubject.notifyObservers(1234)
}
