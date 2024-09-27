package com.github.interview_prep.mutablelistproblem

fun main() {
    val mutableList = mutableListOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12)
    val holder = MutableListHolder(mutableList)
    holder.clear()
    println(mutableList)
}

data class MutableListHolder(
    private val mutableList: MutableList<Int>,
) {
    fun clear() {
        mutableList.removeLast()
    }
}
