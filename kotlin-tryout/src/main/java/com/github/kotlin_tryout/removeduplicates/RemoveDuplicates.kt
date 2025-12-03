package com.github.kotlin_tryout.removeduplicates

data class BowlersRanking(
    val id: Int,
    val name: String,
    val country: String,
)

fun main() {
    val items =
        listOf(
            com.github.kotlin_tryout.removeduplicates.BowlersRanking(1, "Jacob Thomas", "India"),
            com.github.kotlin_tryout.removeduplicates.BowlersRanking(1, "Jacob Thoma2s", "India"),
            com.github.kotlin_tryout.removeduplicates.BowlersRanking(2, "Chandran Mathew", "India"),
            com.github.kotlin_tryout.removeduplicates.BowlersRanking(3, "Nishanth P", "India"),
            com.github.kotlin_tryout.removeduplicates.BowlersRanking(4, "Manohar Joshi", "India"),
            com.github.kotlin_tryout.removeduplicates.BowlersRanking(5, "Manohar Joshi", "India"),
        )
    val removedDuplicates = items.distinctBy { it.id }
    println(removedDuplicates)
    println(removedDuplicates.size)
}
