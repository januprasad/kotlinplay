package com.github.interview_prep

import com.github.interview_prep.Source.*

//sealed class Shape {
//    abstract fun area(): Float
//    class Circle(val radius: Float) : Shape() {
//        override fun area(): Float {
//            return radius * radius * 3.14f
//        }
//    }
//
//    class Rectangle(private val width: Float, private val height: Float) : Shape() {
//        override fun area(): Float {
//            return width * height
//        }
//
//        override fun toString(): String {
//            return area().toString()
//        }
//    }
//
//    class Triangle(val base: Float, val height: Float) : Shape() {
//        override fun area(): Float {
//            return base * height
//        }
//    }
//}
//
//fun main() {
//    val area = Shape.Rectangle(4f, 5f)
//    println(area)
//}

class MusicPlayer(private val source: Source) {
    fun play() {
        source.play()
    }
    fun getMetadata() = source.metadata()
}

sealed interface SourceSpec {
    fun play()
}
fun getFileHDDByteArray(source: String): ByteArray {
    return source.encodeToByteArray().plus(1)
}
fun getFileSDCardByteArray(source: String): ByteArray {
    return source.encodeToByteArray().plus(2)
}
fun getFileCDByteArray(source: String): ByteArray {
    return source.encodeToByteArray().plus(3)
}

sealed class Source(private val encodedFile: ByteArray) : SourceSpec {

     class Laptop(private val source: String) : Source(getFileHDDByteArray(source)) {
        override fun play() {
            println("Playing from Laptop $source")
        }
    }


     class CD(private val index: String) : Source(getFileCDByteArray(index)) {
        override fun play() {
            println("Playing from CD $index")
        }
    }

    class Ipod(private val path: String) : Source(getFileSDCardByteArray(path)) {
        override fun play() {
            println("Playing from Ipod $path")
        }
    }

    fun metadata(): String {
        return encodedFile.decodeToString()
    }


}



class Stage(private val player: MusicPlayer) {

    fun whatisPlayingNow() {
        player.play()
    }
}

fun main() {
//    val source = Ipod("/Volumes/CKiPod/iPod_Control/Music/Track1.mp3")
//    val stage = Stage(
//        player = MusicPlayer(source)
//    )
//    stage.whatisPlayingNow()
//    source.metadata()


    val source2= Source.CD("3")
    val stage1 = Stage(
        player = MusicPlayer(source2))
//    stage1.whatisPlayingNow()
    source2.play()

//    val stage2 = Stage(
//        player = MusicPlayer(Laptop("Users/Hackerbooth/music/rock/Track3.mp3"))
//    )
//    stage2.whatisPlayingNow()

}