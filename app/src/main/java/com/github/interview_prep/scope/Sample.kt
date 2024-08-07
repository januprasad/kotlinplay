package com.github.interview_prep.scope

class Sample(
    private var s: String,
) {
    init {

        // step 0:  s = t
        // step 1:  s + b
        s = s + "B"
    }

    fun print() {
        println(s)
    }
    constructor(t: String, u: String) : this(t) {
        this.s += u
        // step 3:  s + u
    }
}

fun main() {
    Sample("T", "U").print()
}
