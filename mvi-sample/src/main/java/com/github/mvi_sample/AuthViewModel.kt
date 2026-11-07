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

class AuthViewModel : ViewModel() {

    private val _state = MutableStateFlow<AuthState>(AuthState.NotAuthenticated)
    val state: StateFlow<AuthState> = _state.asStateFlow()

    private val _sideEffects = Channel<AuthSideEffect>(Channel.BUFFERED)
    val sideEffects: Flow<AuthSideEffect> = _sideEffects.receiveAsFlow()

    fun onIntent(intent: AuthIntent) {
        when (intent) {
            is AuthIntent.StartAuthentication -> {
                _state.update { AuthState.Authenticating }
                viewModelScope.launch {
                    _sideEffects.send(AuthSideEffect.TriggerBiometricPrompt)
                }
            }
            is AuthIntent.AuthenticationSuccess -> {
                _state.update { AuthState.Authenticated }
                viewModelScope.launch {
                    _sideEffects.send(AuthSideEffect.NavigateToHome)
                }
            }
            is AuthIntent.AuthenticationError -> {
                _state.update { AuthState.Error(intent.message) }
            }
        }
    }
}
