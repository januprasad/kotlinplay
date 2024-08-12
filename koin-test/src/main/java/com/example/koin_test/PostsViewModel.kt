package com.example.koin_test

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.koin_test.data.Post
import com.example.koin_test.repo.PostsRepository
import com.example.koin_test.util.NetworkResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update

class PostsViewModel(
    private val postsRepository: PostsRepository,
) : ViewModel() {
    private val _uiState = MutableStateFlow(AppState.UiState())
    val uiState: StateFlow<AppState.UiState> get() = _uiState.asStateFlow()

    init {
        getPosts()
    }

    fun getPosts() {
        postsRepository
            .getPosts()
            .onEach { result ->
                when (result) {
                    is NetworkResult.Loading -> {
                        _uiState.update {
                            AppState.UiState(loading = true)
                        }
                    }
                    is NetworkResult.Error -> {
                        _uiState.update { AppState.UiState(error = result.message) }
                    }
                    is NetworkResult.Success -> {
                        _uiState.update {
                            AppState.UiState(
                                data = result.data?.posts,
                                loading = false,
                            )
                        }
                    }
                }
            }.launchIn(viewModelScope)
    }
}

object AppState {
    data class UiState(
        val loading: Boolean = true,
        val error: String? = null,
        val data: List<Post>? = null,
    ) {
        val loaded: Boolean = data != null
    }
}
