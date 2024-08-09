package com.github.interview_prep.mutablelistproblem

import androidx.collection.MutableIntSet
import androidx.collection.mutableIntSetOf
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
        val items22 = arrayOf(1, 2, 3)
        val items = mutableListOf(1, 2, 3)
        val items1 = mutableIntSetOf(1, 2, 3)
        val items2 = mutableSetOf(1, 2, 3)
        val screen1 = Screen2(items2)
        val screen2 = Screen(items)
        val screen3 = Screen3(items1)
        val screen4 = Screen4(items22)
        operate(items)
        operate(items1)
        operate2(items2)
        println(screen1.numbers.size)
        println(screen2.numbers.size)
        println(screen3.numbers.size)
        println(screen4.numbers.size)
    }
}

fun operate(items1: MutableIntSet) {
    items1.clear()
}

fun operate(items: MutableList<Int>) {
    items.clear()
}

fun operate2(items: MutableSet<Int>) {
    items.clear()
}

class Screen4(
    val numbers: Array<Int>,
)

class Screen(
    val numbers: List<Int>,
)

class Screen2(
    val numbers: Set<Int>,
)

class Screen3(
    val numbers: MutableIntSet,
)
