package com.github.januprasad.hilt_example_2.app

sealed class AppState {

    object NotOnboarded: AppState()
    object Onboarded: AppState()

}