package com.example.mockito.sharedpref

import android.content.Context

class Repository(
    private val context: Context,
) {
    // save UserId to SharePreference
    fun saveUserId(id: String) {
        val sharedPreference =
            context.getSharedPreferences(
                "USER_DATA",
                Context.MODE_PRIVATE,
            )

        sharedPreference.edit().putString("USER_ID", id).commit()
    }
}
