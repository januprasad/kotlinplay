package com.github.solid.covariance

class UnsafeBox<out T>(
    private var item: T,
) {
    fun get(): T = item

    fun set(newItem: @UnsafeVariance T) {
        item = newItem
    }
}

open class Animal

open class Dog : Animal()

open class Schnauzer : Dog()

// Because T is "out", when you call setIt(), you can pass
// either UnsafeBox<Dog> or UnsafeBox<Schnauzer>:
fun setIt(
    box: UnsafeBox<Dog>,
    dog: Dog,
) = box.set(dog)

fun main() {
    val box: UnsafeBox<Schnauzer> = UnsafeBox(Schnauzer())
//    setIt(box, Dog()) // Oops! Passing a Dog to UnsafeBox<Schnauzer>
    setIt(box, Schnauzer()) // Oops! Passing a Dog to UnsafeBox<Schnauzer>
//    val schnauzer: Schnauzer = box.get() // ClassCastException!
}
