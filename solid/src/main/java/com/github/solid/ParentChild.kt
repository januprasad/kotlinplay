package com.github.solid

import java.lang.ref.WeakReference

class Parent {
    private val childRef: WeakReference<Child> by lazy { WeakReference(Child()) }

    fun getChild(): Child? = childRef.get()

    fun print() {
        println("inner class: ${childRef.get()}")
    }
}

class Child {
    private val parentRef: WeakReference<Parent> by lazy { WeakReference(Parent()) }

    fun getParent(): Parent? = parentRef.get()

    fun print() {
        println("inner class: ${parentRef.get()}")
    }
}

fun main() {
    val parent: Parent? = Parent()
    val child: Child? = Child()

    parent?.print()
    child?.print()
    println("main fun: ${parent?.getChild()}")
    println("main fun: ${child?.getParent()}\n")

    // Force garbage collection to see the effect of weak references
    System.gc()

    println("After garbage collection:")
    parent?.print()
    child?.print()
    println("main fun: ${parent?.getChild()}")
    println("main fun: ${child?.getParent()}")
}
