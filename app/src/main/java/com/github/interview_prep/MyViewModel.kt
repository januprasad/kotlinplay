package com.github.interview_prep

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MyViewModel : ViewModel() {
    val mobula = Mobula()
    private val _uiState: MutableStateFlow<AppState> = MutableStateFlow(AppState.Init)
    val uiState = _uiState.asStateFlow()
    val cryptoState: MutableState<String> = mutableStateOf("")
    fun fetchData() {
        viewModelScope.launch {
            _uiState.value = AppState.Loading
            val response = mobula.getPrice()
            val crypto = mobula.parse(response)
            _uiState.value = AppState.Done(crypto)
        }
    }
}

sealed class AppState {
    object Init : AppState()
    object Loading : AppState()
    data class Done(val crypto: Crypto) : AppState()
}
