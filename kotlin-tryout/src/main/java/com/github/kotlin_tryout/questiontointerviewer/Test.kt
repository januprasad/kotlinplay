package com.github.kotlin_tryout.questiontointerviewer

fun main() {
//    MutbaleListIssue.kt

    val items = mutableListOf(1, 2, 3, 4)
//    items.forEach { item ->
//        if (item < 2) {
//            println(item)
//        } else {
//            items.remove(item) // when this is executed the exception will be thrown
//        }
//    }

//    val items = mutableListOf(1, 2, 3, 4)
    val iterator = items.iterator()
    while (iterator.hasNext()) {
        val item = iterator.next()
        if (item < 1) {
            println(item)
        } else {
            iterator.remove()
        }
    }
}
