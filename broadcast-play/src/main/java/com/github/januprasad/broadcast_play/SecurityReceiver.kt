package com.github.januprasad.broadcast_play
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class SecurityReceiver : BroadcastReceiver() {
    override fun onReceive(
        context: Context?,
        intent: Intent?,
    ) {
        // Security initialization tasks
        Log.v("SecurityReceiver", "Security initialization tasks completed.")

        // Propagate the broadcast to the next receiver
        resultCode = android.app.Activity.RESULT_OK
    }
}
