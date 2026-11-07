package com.github.mvi_sample

sealed interface HomeSideEffect {
    data class NavigateToDetail(val itemId: String) : HomeSideEffect
    data class ShowSnackbar(val message: String) : HomeSideEffect
    object NavigateToLogin : HomeSideEffect
}
