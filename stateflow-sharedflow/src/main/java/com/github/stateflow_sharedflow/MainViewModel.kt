package com.github.stateflow_sharedflow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class UIState(
    var name: String = "",
    var id: Int = 0,
)

class MainViewModel : ViewModel() {
    val testChannel = Channel<UIEvent>()
    val testEvent = MutableSharedFlow<UIEvent>()
    private val _testState = MutableStateFlow(UIState())
    var testState = _testState.asStateFlow()

    init {
        startFlow()
//        startChannel()
    }

    @OptIn(ExperimentalCoroutinesApi::class, DelicateCoroutinesApi::class)
    private fun startFlow() {
        viewModelScope.launch(Dispatchers.Unconfined) {
            delay(1000)
            testEvent.emit(UIEvent.Red)
            delay(2000)
            testEvent.emit(UIEvent.Green)
            delay(5000)
            testEvent.emit(UIEvent.Red)
        }
    }

    private fun startChannel() {
        viewModelScope.launch {
            delay(1000)
            testChannel.send(UIEvent.Red)
            delay(2000)
            testChannel.send(UIEvent.Green)
            delay(5000)
            testChannel.send(UIEvent.Red)
        }
    }

    fun updateState(
        name: String,
        id: String,
    ) {
        viewModelScope.launch(Dispatchers.Default) {
            _testState.update { state ->
                state.copy(name = name, id = id.toInt())
            }
        }
    }
}

sealed class UIEvent {
    object Red : UIEvent()

    object Green : UIEvent()
}
