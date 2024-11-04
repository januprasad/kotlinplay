package com.github.kotlin_tryout

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class SampleStateViewModel : ViewModel() {
    private val _nameState = MutableStateFlow<String?>(null)
    val name = _nameState.asStateFlow()

    init {
        viewModelScope.launch {
            _nameState.value = API.getName()
        }
    }

    val email =
        API.getEmail()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = null,
            )
}

object API {
    suspend fun getName(): String {
        // Simulate an API call
        delay(1000L)
        return "Januprasad K"
    }

    fun getEmail(): Flow<String> {
        // Simulate an API call
        return flow {
            delay(1000L)
            emit("itsjenu@gmail.com")
        }
    }
}
