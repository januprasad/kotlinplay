package com.github.interview_prep

import android.content.Context


class MyHelper private constructor(
    context: Context,
) {
    companion object : SingletonHolder<MyHelper, Context>(::MyHelper)
}
