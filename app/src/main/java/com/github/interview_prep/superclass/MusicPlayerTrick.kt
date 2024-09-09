package com.github.interview_prep.superclass

fun main() {
    val m = Tape()
    m.test()
}

class Tape : MusicPlayer {
    private fun play() {
        println("Playing music from tape")
    }

    fun pauseTap() {
        pause()
        play()
    }
}

fun MusicPlayer.test() {
    println("testing a track")
}
