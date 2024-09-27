package com.github.januprasad.turbine_tests

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ExampleViewModel(
    private val cryptographicCalculation: CryptographicCalculation,
) : ViewModel() {
    private val _vmState: MutableStateFlow<VmState> = MutableStateFlow(VmState.Waiting)
    val vmState = _vmState.asStateFlow()

    fun requestEncryption() {
        val result = cryptographicCalculation.encryptedString("Hello World")
        _vmState.update {
            VmState.Finished(result)
        }
    }
}
