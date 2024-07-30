package com.github.januprasad.hilt_example

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.januprasad.hilt_example.model.Quote
import com.github.januprasad.hilt_example.repo.QuoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
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
                Events.RandomQuote ->
                    viewModelScope.launch(Dispatchers.IO) {
                        repository.randomQuote().collectLatest {
                            Log.v("Quote", it.data?.quote.orEmpty())
                        }
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
        val data: Quote? = null,
    )
}
