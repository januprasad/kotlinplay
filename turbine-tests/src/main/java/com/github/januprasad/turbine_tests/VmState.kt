package com.github.januprasad.turbine_tests

sealed class VmState {
    object Waiting : VmState()

    object Running : VmState()

    data class Finished(
        val data: String,
    ) : VmState()
}
