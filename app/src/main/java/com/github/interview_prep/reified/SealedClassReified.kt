package com.github.interview_prep.reified

sealed class VideoType {
    data class Programming(
        val name: String,
    ) : VideoType()

    data class Cooking(
        val name: String,
    ) : VideoType()

    data class Travel(
        val name: String,
    ) : VideoType()
}

fun main() {
    val programmingVideoKt = VideoType.Programming("Kotlin Programming")
    val programmingVideoJav = VideoType.Programming("Java Programming")
    val cookingVideo = VideoType.Cooking("How to make Pasta")
    val travelVideo = VideoType.Travel("The Unforgettable Italy")

    val videos =
        listOf(
            programmingVideoKt,
            programmingVideoJav,
            cookingVideo,
            travelVideo,
        )

    val filteredVideos = filter<VideoType.Programming>(videos)
    println(filteredVideos)
    applyTransform(
        list = videos,
        transformation = { video ->
            /**
             * big logic dont line
             */
            when (video) {
                is VideoType.Cooking -> video.copy(video.name.plus(" ~1"))
                is VideoType.Programming -> video.copy(video.name.plus(" ~1"))
                is VideoType.Travel -> video.copy(video.name.plus(" ~1"))
            }
        },
        onComplete = { videos ->
            videos.forEach(::println)
        },
    )

    val playVideo = PlayVideo<VideoType>()
    playVideo.play(videos)
}

inline fun <reified T> filter(list: List<VideoType>): List<T> = list.filterIsInstance<T>()

inline fun applyTransform(
    list: List<VideoType>,
    noinline transformation: (VideoType) -> VideoType,
    crossinline onComplete: (List<VideoType>) -> Unit,
) {
    val transformedList = list.map(transformation)
    onComplete(transformedList)
}

class PlayVideo<T> where T : VideoType {
    fun play(videoList: List<T>) {
        videoList.forEach { video ->
            when (video) {
                is VideoType.Cooking -> playCookingVideo(video)
                is VideoType.Programming -> playProgrammingVideo(video)
                is VideoType.Travel -> playTravelVideo(video)
            }
        }
    }

    private fun playTravelVideo(video: VideoType.Travel) {
        println("Programming video in media plyer")
    }

    private fun playProgrammingVideo(video: VideoType.Programming) {
        println("Programming video in vlc")
    }

    private fun playCookingVideo(video: VideoType.Cooking) {
        println("playing playCookingVideo in expo player")
    }
}
