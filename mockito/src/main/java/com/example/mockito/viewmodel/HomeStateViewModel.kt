package com.example.mockito.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class HomeStateViewModel : ViewModel(){
    private var _appState by mutableStateOf(AppState())
    val appState get() = _appState

    fun loadItems(){
        _appState = appState.copy(
            loaded = true,
        )
    }

    suspend fun loadSomeItems(){
        viewModelScope.launch {
            delay(3000L)
            _appState = appState.copy(
                loaded = true,
            )
        }
    }

}
data class AppState(val loaded: Boolean = false)