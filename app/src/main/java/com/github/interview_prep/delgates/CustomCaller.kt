package com.github.interview_prep.delgates

import kotlin.reflect.KProperty

class Resource

class Owner {
    val valResource: Resource by ResourceDelegate()
}

fun main() {
    println(Owner().valResource)
}

class ResourceDelegate {
    operator fun getValue(
        thisRef: Owner,
        property: KProperty<*>,
    ): Resource = Resource()
}
