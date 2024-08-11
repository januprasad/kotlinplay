package com.example.koin_test

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class SettingsViewModel(
    private val store: SettingsRepository,
) : ViewModel() {
    val nickname = mutableStateOf(store.nickname)
    val turboMode = mutableStateOf(store.turboMode)
    val throttle = mutableStateOf(store.throttle)

    fun save() =
        viewModelScope.launch {
            store.nickname = nickname.value
            store.turboMode = turboMode.value
            store.throttle = throttle.value
        }
}
