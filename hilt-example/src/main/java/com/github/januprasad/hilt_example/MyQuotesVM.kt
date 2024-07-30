package com.github.januprasad.hilt_example

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.januprasad.hilt_example.common.NetworkResult
import com.github.januprasad.hilt_example.model.Quote
import com.github.januprasad.hilt_example.repo.QuoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class MyQuotesVM
    @Inject
    constructor(
        val repository: QuoteRepository,
    ) : ViewModel() {
        private val _uiState = MutableStateFlow(AppState.UiState())
        val uiState: StateFlow<AppState.UiState> get() = _uiState.asStateFlow()

        fun events(event: Events) {
            when (event) {
                Events.RandomQuote -> {
                    repository
                        .randomQuote()
                        .onEach { result ->
                            when (result) {
                                is NetworkResult.Loading -> {
                                    _uiState.update { AppState.UiState(isLoading = true) }
                                }

                                is NetworkResult.Error -> {
                                    _uiState.update { AppState.UiState(error = result.message) }
                                }

                                is NetworkResult.Success -> {
                                    _uiState.update { AppState.UiState(data = result.data) }
                                }
                            }
                        }.launchIn(viewModelScope)
                }
            }
        }
    }

sealed class Events {
    object RandomQuote : Events()
}

object AppState {
    data class UiState(
        val isLoading: Boolean = false,
        val error: String? = null,
        val data: Quote? = null,
    )
}
