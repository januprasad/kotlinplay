package com.github.januprasad.hilt_example_2.app.ui.components.appdrawer

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class AppDrawerItemInfo<T>(
    val drawerOption: T,
    @StringRes val title: Int,
    @DrawableRes val drawableId: Int,
    @StringRes val descriptionId: Int
)