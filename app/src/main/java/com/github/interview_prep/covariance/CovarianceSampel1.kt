package com.github.interview_prep.covariance

interface Source<out T> {
    fun nextT(): T
}

class IntSource : Source<Int> {
    override fun nextT(): Int = 42
}

class FloatSource : Source<Float> {
    override fun nextT(): Float = 1.0f
}

fun printValue(source: Source<Number>) {
    println(source.nextT())
}

fun main() {
    val intSource: Source<Int> = IntSource()
    val floatSource: Source<Float> = FloatSource()
    printValue(intSource) // This works because Int is a subtype of Number
    printValue(floatSource) // This works because Int is a subtype of Number
}

/**
 * Subtyping is preserved.
 * A subtype of a generic type can be used where the generic type is expected.
 * Keyword: out
 * Typically used for output positions (return types).
 *
 */
