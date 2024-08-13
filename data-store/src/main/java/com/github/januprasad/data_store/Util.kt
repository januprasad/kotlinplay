package com.github.januprasad.data_store

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore

data class UserPreferences(
    val showCompleted: Boolean,
)

private const val USER_PREFERENCES_NAME = "user_preferences"

private val Context.dataStore by preferencesDataStore(
    name = USER_PREFERENCES_NAME,
)
