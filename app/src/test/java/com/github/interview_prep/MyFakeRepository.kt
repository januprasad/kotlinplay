package com.github.interview_prep

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
