package com.github.kotlin_tryout

import kotlinx.coroutines.flow.flow

class MyFakeRepository : MyRepository() {
    fun observeCount() =
        flow {
            emit(ITEM_1)
        }
}

open class MyRepository {
    val counter = listOf("A")
}

val ITEM_1 = "Item1"
