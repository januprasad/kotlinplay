package com.github.kotlin_tryout.extensions

import java.util.TreeMap

fun main() {
    val data = TreeMap<String, String>()

    operator fun <K, V> TreeMap<K, V>.get(index: Int): V = values.elementAt(index)
    data["a"] = "helloa"
    data["b"] = "hellob"
    data["c"] = "helloc"
    data["d"] = "helld"
    data["e"] = "helloe"
    println(data["a"])
    println(data[1])
}
