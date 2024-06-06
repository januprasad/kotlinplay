package com.github.interview_prep

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MyViewModel(private val coroutineDispatcher: DispatcherProvider) : ViewModel() {
    val mobula = Mobula()
    private val _uiState: MutableStateFlow<AppState> = MutableStateFlow(AppState.Init)
    val uiState = _uiState.asStateFlow()
    val cryptoState: MutableState<String> = mutableStateOf("")
    fun fetchData() {
        _uiState.value = AppState.Loading
        viewModelScope.launch(coroutineDispatcher.io) {
            val response = mobula.getPrice("BTC", coroutineDispatcher).await()
            val crypto = mobula.parse(response).await()
            _uiState.value = AppState.Done(crypto.data)
        }
    }

    fun test() {
        _uiState.value = AppState.Loading
        viewModelScope.launch(coroutineDispatcher.io) {
            val result = mobula.test("")
            _uiState.value = AppState.Loaded(result.await())
        }
    }
}

sealed class AppState {
    object Init : AppState()
    object Loading : AppState()
    data class Done(val crypto: Crypto) : AppState()
    data class Loaded(val result: String) : AppState()
}
