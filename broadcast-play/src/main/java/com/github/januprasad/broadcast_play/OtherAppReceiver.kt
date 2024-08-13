package com.github.januprasad.broadcast_play

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class OtherAppReceiver : BroadcastReceiver() {
    override fun onReceive(
        context: Context?,
        intent: Intent?,
    ) {
        // Other app initialization tasks
        Log.v("OtherAppReceiver", "Other app initialization tasks completed.")
    }
}
