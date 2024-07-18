package com.example.problem_solving_questions

fun main() {
    println(runningSum(intArrayOf(1, 2, 3, 4, 5)).sum())
    println(intArrayOf(1, 2, 3, 4, 5).sum())
}

fun runningSum(nums: IntArray): IntArray = nums.runningReduce { sum, element -> sum + element }.toIntArray()
