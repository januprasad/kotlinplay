package com.github.kotlin_tryout

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.random.Random
import kotlin.random.nextInt

// private val coroutineDispatcher: DispatcherProvider
class MyViewModel : ViewModel() {
    val mobula: Mobula = Mobula()
    private val _uiState: MutableStateFlow<AppState> = MutableStateFlow(AppState.Init)
    val uiState = _uiState.asStateFlow()

    private val _counter = MutableLiveData<Int>()
    val counter: LiveData<Int> get() = _counter

    init {
        _counter.value = Random(1000).nextInt(1000)
    }

    fun increment() {
        _counter.value = (_counter.value ?: 0) + 1
    }

    val cryptoState: MutableState<String> = mutableStateOf("")

    fun fetchData(symbol: String) {
        _uiState.value = AppState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            val response = mobula.getMethod(symbol).await()
//            _uiState.value = AppState.Loaded(result.await())
        }
    }

    fun test(s: String) {
        _uiState.value = AppState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            val result = mobula.test(s)
            _uiState.value = AppState.Loaded(result.await())
        }
    }

    fun testString(s: String) {
        _uiState.value = AppState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            val result = mobula.getString(s)
            _uiState.value = AppState.Loaded(result)
        }
    }

    fun handleAction(action: ViewModelAction) {
        when(action){
            ViewModelAction.Clear -> {
                Log.v("App","clear ${counter.value}")
            }
            ViewModelAction.Save -> {
                increment()
                Log.v("App","saved ${counter.value}")
            }
        }
    }
}

sealed interface ViewModelAction {
    object Save : ViewModelAction
    object Clear : ViewModelAction
}

sealed class AppState {
    object Init : AppState()

    object Loading : AppState()

    data class Done(
        val crypto: Crypto,
    ) : AppState()

    data class Loaded(
        val result: String,
    ) : AppState()
}
