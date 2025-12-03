package com.github.kotlin_tryout.scope

class Sample(
    private var s: String,
) {
    fun print() {
        println(s)
    }
    constructor(t: String, u: String) : this(t) {
        println("two")
        this.s += u
        // step 3:  s + u
    }

    init {
        println("one")
        // step 0:  s = t
        // step 1:  s + b
        s = s + "B"
    }
}

fun main() {
    Sample("T", "U").print()
}
