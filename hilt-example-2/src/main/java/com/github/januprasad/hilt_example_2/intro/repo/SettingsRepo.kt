package com.github.januprasad.hilt_example_2.intro.repo

interface SettingsRepo {

    fun isOnboarded(): Boolean
    fun saveOnboardingState(isOnboarded: Boolean)

}