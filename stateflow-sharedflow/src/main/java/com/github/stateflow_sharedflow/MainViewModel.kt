package com.github.stateflow_sharedflow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch


class MainViewModel : ViewModel() {
    val testEvent = MutableSharedFlow<UIEvent>()
    val testChannel = Channel<UIEvent>()

    init {
//        startFlow()
        startChannel()
    }

    private fun startFlow() {
        viewModelScope.launch {
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
}

sealed class UIEvent {
    object Red : UIEvent()
    object Green : UIEvent()

}