package com.github.interview_prep.scope

import com.github.interview_prep.partition.User
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    val list =
        arrayListOf(
            User(
                id = 3,
                name = "Amit",
                isMentor = true,
            ),
        )

    val userHolder = UserHolder(list)
    runBlocking {
        launch {
            delay(1000)
            list.clear()
        }
    }

    println(userHolder.list.size)
}

data class UserHolder(
    val list: MutableList<User>,
)
