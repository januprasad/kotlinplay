package com.github.januprasad.data_store

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences

class UserPreferencesRepository(
    private val dataStore: DataStore<Preferences>,
    context: Context,
)
