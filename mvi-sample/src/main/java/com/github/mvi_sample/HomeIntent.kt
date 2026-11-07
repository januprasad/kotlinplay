package com.github.mvi_sample

sealed interface HomeIntent {
    object LoadItems : HomeIntent
    object Refresh : HomeIntent
    data class Search(val query: String) : HomeIntent
    data class ItemClicked(val id: String) : HomeIntent
    object ClearError : HomeIntent
}
