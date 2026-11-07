package com.github.mvi_sample

sealed interface AuthState {
    data object NotAuthenticated : AuthState
    data object Authenticating : AuthState
    data object Authenticated : AuthState
    data class Error(val message: String) : AuthState
}

sealed interface AuthIntent {
    data object StartAuthentication : AuthIntent
    data object AuthenticationSuccess : AuthIntent
    data class AuthenticationError(val message: String) : AuthIntent
}

sealed interface AuthSideEffect {
    data object TriggerBiometricPrompt : AuthSideEffect
    data object NavigateToHome : AuthSideEffect
}
