package com.example.mockito

import android.content.Context

data class ClassUnderTest(val c: Context) {
    fun getName(): String =
        c.getString(R.string.name_label)
}