package com.github.stateflow_sharedflow


fun main() {
    val source = Source.CD()
    playMusicPlayer(source = source)

    val source1 = Source.Tape()
    playMusicPlayer(source = source1)

    val source2 = Source.Spotify()
    playMusicPlayer(source = source2)
}

fun playMusicPlayer(source: Source) {
    val stream = when (source) {
        is Source.CD -> playMusic(source, ::playCD)
        is Source.Spotify -> playMusic(source, ::playSpotify)
        is Source.Tape -> playMusic(source, ::playTape)
    }
    println("Playing ${stream.trackInfo.random().title} from ${source.name}")

}

private fun playMusic(source: Source, play: (Source) -> Stream): Stream {
    return play(source)
}

private fun playTape(source: Source): Stream {
    return StreamService(source, StreamReader()).buildStreams()
}

private fun playCD(source: Source): Stream {
    return StreamService(source, StreamReader()).buildStreams()
}

private fun playSpotify(source: Source): Stream {
    return StreamService(source, StreamReader()).buildStreams()
}

interface IStreamReader {
    fun readCD(): MutableList<Track>
    fun fetchSpotify(): MutableList<Track>
    fun readTape(): MutableList<Track>
}

internal class StreamReader() : IStreamReader {

    override fun readCD(): MutableList<Track> {
        return mutableListOf(
            Track(
                id = 1,
                title = "Track 1"
            ),
            Track(
                id = 2,
                title = "Track 2"
            )
        )
    }

    override fun fetchSpotify(): MutableList<Track> {
        return mutableListOf(
            Track(
                id = 1,
                title = "Track 1"
            ),
            Track(
                id = 2,
                title = "Track 2"
            )
        )
    }

    override fun readTape(): MutableList<Track> {
        return mutableListOf(
            Track(
                id = 1,
                title = "Track 1"
            ),
            Track(
                id = 2,
                title = "Track 2"
            )
        )
    }
}

class StreamService(private val source: Source, private val streamReader: IStreamReader) {
    fun buildStreams(): Stream {
        val trackInfo = when (source) {
            is Source.CD -> streamReader.readCD()
            is Source.Spotify -> streamReader.fetchSpotify()
            is Source.Tape -> streamReader.readTape()
        }
        return Stream(trackInfo)
    }
}

data class Stream(val trackInfo: MutableList<Track>)

data class Track(val id: Int, val title: String)

sealed class Source(open val name: String) {
    data class CD(override val name: String = "CD") : Source(name)
    data class Tape(override val name: String = "Tape") : Source(name)
    data class Spotify(override val name: String = "Tape") : Source(name)
}
