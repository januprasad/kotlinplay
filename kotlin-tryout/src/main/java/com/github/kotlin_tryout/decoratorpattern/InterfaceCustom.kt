package com.github.kotlin_tryout.decoratorpattern

import java.text.SimpleDateFormat
import java.util.Date
import java.util.UUID

fun main() {
    val logger = consoleLogger.uniqueId().withTimeStamp()
    logger.log("App started")
}

private fun Logger.withTimeStamp() = Logger { log("$formattedDate $it") }

private fun Logger.uniqueId() = Logger { log(UUID.randomUUID().toString() + " " + it) }

val consoleLogger =
    Logger {
        println(it)
    }

fun interface Logger {
    fun log(message: String)
}

val currentDate = Date()
val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
val formattedDate = dateFormat.format(currentDate)
