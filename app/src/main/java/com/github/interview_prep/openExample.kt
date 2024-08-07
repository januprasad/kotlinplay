package com.github.interview_prep

class openExample

open class TestOpen {
    open fun test() {
    }
}

class ChildClass : TestOpen() {
    override fun test() {
        super.test()
    }
}
