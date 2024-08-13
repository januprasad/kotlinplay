package com.github.januprasad.broadcast_play

interface ConnectivityListener {
    fun onNetworkConnectionChanged(isConnected: Boolean)
}
