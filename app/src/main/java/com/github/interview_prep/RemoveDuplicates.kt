package com.github.interview_prep

data class BowlersRanking(
    val id: Int,
    val name: String,
    val country: String,
)

fun main() {
    val items =
        arrayOf(
            BowlersRanking(1, "Jacob Thomas", "India"),
            BowlersRanking(1, "Jacob Thomas", "India"),
            BowlersRanking(2, "Chandran Mathew", "India"),
            BowlersRanking(3, "Nishanth P", "India"),
            BowlersRanking(4, "Manohar Joshi", "India"),
            BowlersRanking(5, "Manohar Joshi", "India"),
        )
    val removedDuplicates = items.distinct()
    println(removedDuplicates.size)
}
