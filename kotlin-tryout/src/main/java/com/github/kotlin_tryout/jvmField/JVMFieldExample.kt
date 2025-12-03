package com.github.kotlin_tryout.jvmField

import java.util.Date

data class Session(
    @JvmField val name: String,
    val date: Date = Date(),
)

fun main() {
    val session = Session("Session", Date())
    val name = session.name
}
