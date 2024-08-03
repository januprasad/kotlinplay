package com.example.mockito.utils

import android.content.Context
import com.example.mockito.R

data class ClassUnderTest(
    val c: Context,
) {
    fun getName(): String = c.getString(R.string.name_label)
}
