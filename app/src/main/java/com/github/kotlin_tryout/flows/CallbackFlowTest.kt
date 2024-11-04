package com.github.kotlin_tryout.flows

import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.runBlocking

fun main() = runBlocking{
    waitForRandomNumber().collect {
        println(it)
    }
}

fun waitForRandomNumber(): Flow<Location> {
    return callbackFlow {
        val locationListener = object : LocationListener {

            override fun onLocationUpdate(location: Location) {
                trySend(location)
            }

            override fun done() {
                println("Done")
            }

        }
        locationListener.onLocationUpdate(Location("Kerala"))
        awaitClose {
            locationListener.onLocationUpdate(Location("Kerala1"))
            locationListener.done()
        }
    }
}

interface LocationListener {
    fun onLocationUpdate(location: Location)
    fun done()
}
data class Location(val location: String)
