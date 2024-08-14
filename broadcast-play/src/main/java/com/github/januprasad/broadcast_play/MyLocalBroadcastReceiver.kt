package com.github.januprasad.broadcast_play

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class MyLocalBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(
        context: Context,
        intent: Intent,
    ) {
        when (intent.action) {
            Filters.PlayGame.name -> {
                val result = intent.getStringExtra("data")
                Toast.makeText(context, result.toString(), Toast.LENGTH_SHORT).show()
            }
            else -> {
                Toast.makeText(context, "Action not found!!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
