package com.example.parcelize_test

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FeedsLikeData(
    val feedId: Int? = null,
    val totalLike: Int? = null,
    val like: Boolean? = null,
) : Parcelable

class Wrapper {
    @Parcelize
    data class CustomFeed(
        val feedId: Int? = null,
        val totalLike: Int? = null,
        val like: Boolean? = null,
    ) : Parcelable {
        @Parcelize
        data class FeedsItem(
            val feedId: Int? = null,
            val totalLike: Int? = null,
            val like: Boolean? = null,
        ) : Parcelable
    }
}
