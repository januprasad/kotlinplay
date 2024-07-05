package com.github.stateflow_sharedflow

import java.util.LinkedList

fun main() {
    val linkedList = LinkedList<Int>()
    linkedList.add(1)
    linkedList.add(2)
    linkedList.add(3)
//    linkedList.map {
//        println(it)
//    }
    val list = listOf(44,5,6,7,7,8,7,4,3,4,2,2,9,22,32,43)
    println(list.maxOf { it })
    val hashmap = hashMapOf(
        1 to "one",
        2 to "two",
        3 to "three",
        4 to "four",
        5 to "five",
        6 to "six",
        6 to "six",
        6 to "six",
        6 to "six",
        6 to "six",
    )
    val hashmap2: HashMap<Int, String> = linkedMapOf(
        1 to "one",
        2 to "two",
        3 to "three",
        4 to "four",
        5 to "five",
        6 to "six",
        6 to "six",
        6 to "six",
        6 to "six",
        6 to "six",
    )
//    val hashSet = hashSetOf<>()
    println(hashmap.keys.first())
    println(hashmap.keys.last())
    println(hashmap.keys.size)
    println(hashmap2.keys.size)
}