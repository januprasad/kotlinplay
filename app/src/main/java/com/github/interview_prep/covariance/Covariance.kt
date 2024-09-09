package com.github.interview_prep.covariance

interface Producer<out T> {
    fun produce(): T
}

class StringProducer : Producer<String> {
    override fun produce(): String = "Hello"
}

class AnyProducer : Producer<Any> {
    override fun produce(): Any = 42
}

fun main() {
//    val aP = AnyProducer()
//    println(aP.produce())
//    val aP1 = StringProducer()
//    println(aP1.produce())

    val producer: Producer<Any> = StringProducer()
    val any: Any = producer.produce()

    val producer1: Producer<Any> = AnyProducer()
    val any1: Any = producer1.produce()
    val string: String = any1 as String // ClassCastException
}
