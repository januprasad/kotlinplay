package com.github.interview_prep.covariance

open class Tool

open class Ayudham : Tool()

open class Weapon : Ayudham()

open class Rifle : Weapon()

class SniperRifle : Rifle()

// class Case<out T> {
//    private val contents = mutableListOf<T>()
//
//    fun produce(): T = contents.last() // Producer: OK
// //    fun consume(item: T) = contents.add(item)  // Consumer: Error
// }
// fun useProducer(case: Case<Rifle>) {
//    // Produces Rifle and its subtypes
//    val rifle = case.produce()
//    println(rifle)
// }

class Case<in T> {
    private val contents = mutableListOf<T>()

//    fun produce(): T = contents.last() // Producer: Error

    fun consume(item: T) = contents.add(item) // Consumer: OK
}

fun useConsumer(case: Case<Rifle>) {
    // Consumes Rifle and its subtypes
    case.consume(SniperRifle())
}

fun main() {
//    useProducer(Case<SniperRifle>())
//    useProducer(Case<Weapon>())
//    useProducer(Case<Rifle>())

//    useConsumer(Case<SniperRifle>())               // Error
    useConsumer(Case<Rifle>()) // OK
    useConsumer(Case<Weapon>())
    useConsumer(Case<Ayudham>())
    useConsumer(Case<Tool>())
}
