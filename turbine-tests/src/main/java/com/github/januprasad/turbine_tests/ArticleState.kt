package com.github.januprasad.turbine_tests

sealed interface ArticleState {
    object Waiting : ArticleState

    object Running : ArticleState

    data class Loaded(
        val data: String,
    ) : ArticleState
}
