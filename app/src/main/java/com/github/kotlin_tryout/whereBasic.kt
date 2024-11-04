package com.github.kotlin_tryout

fun main() {
}

sealed class VideoType {
    data class ShortsVideo(
        val url: String,
        val length: Int,
    ) : VideoType()

    data class Video(
        val url: String,
        val length: Int,
    ) : VideoType()

    data class KidsVideo(
        val url: String,
        val length: Int,
    ) : VideoType()
}
