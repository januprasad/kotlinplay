package com.github.mvi_sample

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getItemsUseCase: GetItemsUseCase = FakeGetItemsUseCase()
) : ViewModel() {

    private val _state = MutableStateFlow(HomeState())
    val state: StateFlow<HomeState> = _state.asStateFlow()

    private val _sideEffects = Channel<HomeSideEffect>(Channel.BUFFERED)
    val sideEffects: Flow<HomeSideEffect> = _sideEffects.receiveAsFlow()

    fun onIntent(intent: HomeIntent) {
        when (intent) {
            is HomeIntent.LoadItems -> loadItems()
            is HomeIntent.Refresh -> loadItems(refresh = true)
            is HomeIntent.Search -> search(intent.query)
            is HomeIntent.ItemClicked -> {
                viewModelScope.launch {
                    _sideEffects.send(HomeSideEffect.NavigateToDetail(intent.id))
                    _sideEffects.send(HomeSideEffect.ShowSnackbar("Clicked item: ${intent.id}"))
                }
            }
            is HomeIntent.ClearError -> _state.update { it.copy(error = null) }
        }
    }

    private fun loadItems(refresh: Boolean = false) {
        viewModelScope.launch {
            if (!refresh) _state.update { it.copy(isLoading = true) }
            
            getItemsUseCase()
                .onSuccess { items ->
                    _state.update { it.copy(isLoading = false, items = items, error = null) }
                }
                .onFailure { error ->
                    _state.update { it.copy(isLoading = false, error = HomeState.ErrorState.Network(error.message ?: "Unknown error")) }
                }
        }
    }

    private fun search(query: String) {
        _state.update { it.copy(searchQuery = query) }
    }
}
