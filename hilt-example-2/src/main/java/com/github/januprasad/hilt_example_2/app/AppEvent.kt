package com.github.januprasad.hilt_example_2.app

sealed class AppEvent {

    object FinishOnboarding: AppEvent()
    object SendMail: AppEvent()

}