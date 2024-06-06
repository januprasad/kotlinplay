package com.github.interview_prep

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
//private val coroutineDispatcher: DispatcherProvider
class MyViewModel() : ViewModel() {
    val mobula = Mobula()
    private val _uiState: MutableStateFlow<AppState> = MutableStateFlow(AppState.Init)
    val uiState = _uiState.asStateFlow()
    val cryptoState: MutableState<String> = mutableStateOf("")
    fun fetchData(symbol: String) {
        _uiState.value = AppState.Loading
        viewModelScope.launch(Dispatchers.IO) {
//            val response = mobula.getPrice(symbol).await()
            val result = mobula.test(symbol)
//            val crypto = mobula.parse(response).await()
            _uiState.value = AppState.Loaded(result.await())
        }
    }

    fun test(s: String) {
        _uiState.value = AppState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            val result = mobula.test(s)
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
