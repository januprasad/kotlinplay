package com.github.kotlin_tryout.superclass

fun main() {
    val m = Tape()
    m.test()
}

class Tape : com.github.kotlin_tryout.superclass.MusicPlayer {
    private fun play() {
        println("Playing music from tape")
    }

    fun pauseTap() {
        pause()
        play()
    }
}

fun com.github.kotlin_tryout.superclass.MusicPlayer.test() {
    println("testing a track")
}
