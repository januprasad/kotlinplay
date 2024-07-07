package com.example.rx_android_jav

// Node class to represent a song in the playlist
data class SongNode(
    val title: String,
    val artist: String,
) {
    var next: SongNode? = null // Reference to the next song in the playlist
}

class MusicPlayerLinkedList {
    private var head: SongNode? = null // Head of the linked list (first song)

    // Add a song to the end of the playlist
    fun addSong(
        title: String,
        artist: String,
    ) {
        val newSong = SongNode(title, artist)
        if (head == null) {
            head = newSong
        } else {
            var current = head
            while (current?.next != null) {
                current = current.next
            }
            current?.next = newSong
        }
    }

    // Print the playlist (song titles)
    fun printPlaylist() {
        var current = head
        while (current != null) {
            println("${current.title} by ${current.artist}")
            current = current?.next
        }
    }
}

// Example usage
fun main() {
    val musicPlayer = MusicPlayerLinkedList()
    musicPlayer.addSong("Imagine", "John Lennon")
    musicPlayer.addSong("Bohemian Rhapsody", "Queen")
    musicPlayer.addSong("Hey Jude", "The Beatles")
    musicPlayer.printPlaylist()
}
