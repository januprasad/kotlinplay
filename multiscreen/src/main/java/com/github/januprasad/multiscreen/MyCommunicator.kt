package com.github.januprasad.multiscreen

interface MyCommunicator { // Meant for inter-fragment communication

    fun displayDetails(
        title: String,
        description: String,
    )
}
