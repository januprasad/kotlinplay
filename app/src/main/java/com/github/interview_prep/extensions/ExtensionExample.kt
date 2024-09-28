package com.github.interview_prep.extensions

import java.time.LocalDate

/**
 * destructuring example
 */
fun main() {
    val today = LocalDate.parse("2024-08-11")
    val (date, month, year) = today
    println("today is $date, month is $month, year is $year")
}

private operator fun LocalDate.component1(): Int = dayOfMonth

private operator fun LocalDate.component2(): Int = monthValue

private operator fun LocalDate.component3(): Int = year
