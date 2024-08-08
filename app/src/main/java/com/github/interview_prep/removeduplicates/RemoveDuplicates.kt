package com.github.interview_prep.removeduplicates

data class BowlersRanking(
    val id: Int,
    val name: String,
    val country: String,
)

fun main() {
    val items =
        listOf(
            BowlersRanking(1, "Jacob Thomas", "India"),
            BowlersRanking(1, "Jacob Thoma2s", "India"),
            BowlersRanking(2, "Chandran Mathew", "India"),
            BowlersRanking(3, "Nishanth P", "India"),
            BowlersRanking(4, "Manohar Joshi", "India"),
            BowlersRanking(5, "Manohar Joshi", "India"),
        )
    val removedDuplicates = items.distinctBy { it.id }
    println(removedDuplicates)
    println(removedDuplicates.size)
}
