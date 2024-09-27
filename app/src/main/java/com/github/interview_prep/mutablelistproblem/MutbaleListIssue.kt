package com.github.interview_prep.mutablelistproblem

import androidx.collection.MutableIntSet
import androidx.collection.mutableIntSetOf
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
        val anArray = arrayOf(1, 2, 3)
        val aList = arrayListOf(1, 2, 3)
        val aMutableList = mutableListOf(1, 2, 3)
        val aMutableIntSet = mutableIntSetOf(1, 2, 3)
        val aMutableSetOf = mutableSetOf(1, 2, 3)
        val setHolder = SetHolder(aMutableSetOf)
        val mutableListHolder = ListHolder(aMutableList)
        val intSetHolder = MutableIntSetHolder(aMutableIntSet)
        val arrayHolder = ArrayHolder(anArray)
        val listHolder = ListHolder(aList)
        operate(aMutableList)
        operate(aMutableIntSet)
        operate2(aMutableSetOf)
        operate4(aList)
        // operate3(anArray)
        println("aMutableSetOf " + setHolder.numbers.size)
        println("aMutableList " + mutableListHolder.numbers.size)
        println("aMutableIntSet " + intSetHolder.numbers.size)
        println("anArray " + arrayHolder.numbers.size)
        println("aList " + listHolder.numbers.size)
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

fun operate4(items: ArrayList<Int>) {
    items.clear()
}

class ArrayHolder(
    val numbers: Array<Int>,
)

class ListHolder(
    val numbers: List<Int>,
)

class SetHolder(
    val numbers: Set<Int>,
)

class MutableIntSetHolder(
    val numbers: MutableIntSet,
)
