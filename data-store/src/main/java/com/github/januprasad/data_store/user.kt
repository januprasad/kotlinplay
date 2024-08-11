package com.github.januprasad.data_store

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore

val Context.user: DataStore<Preferences> by preferencesDataStore(name = "userInfo")

object DataStoreKeys {
    val USER_NAME = stringPreferencesKey("user_name")
    val EMAIL = stringPreferencesKey("email")
}

private suspend fun saveUserData(
    name: String,
    email: String,
) {
    user.edit { usrData ->
        usrData[DataStoreKeys.USER_NAME] = name
        usrData[DataStoreKeys.EMAIL] = email
    }

    // Display Data after save:
    getUserData()
}
