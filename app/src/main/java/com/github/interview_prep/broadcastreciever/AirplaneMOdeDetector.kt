package com.github.interview_prep.broadcastreciever

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class AirplaneMOdeDetector : BroadcastReceiver() {
    override fun onReceive(
        context: Context,
        intent: Intent,
    ) {
        val airplaneMOdeEnabled =
            intent.getBooleanExtra(
                "state",
                false,
            )

        if (airplaneMOdeEnabled) {
            makeToast(context, "Airplane mode enabled!!")
        } else {
            makeToast(context, "Airplane mode disabled!!")
        }
    }

    private fun makeToast(
        context: Context,
        s: String,
    ) {
        Toast.makeText(context, s, Toast.LENGTH_LONG).show()
    }
}
