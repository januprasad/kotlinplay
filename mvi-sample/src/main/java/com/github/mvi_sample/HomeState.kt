package com.github.mvi_sample

import androidx.compose.runtime.Immutable

@Immutable
data class HomeState(
    val isLoading: Boolean = false,
    val items: List<Item> = emptyList(),
    val error: ErrorState? = null,
    val searchQuery: String = ""
) {
    sealed interface ErrorState {
        data class Network(val message: String) : ErrorState
        data object Unauthorized : ErrorState
    }
}

data class Item(
    val id: String,
    val title: String
)
