package com.github.handler_looper.demos

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.ComponentActivity
import com.github.handler_looper.util.LogBus

class ViewPostActivity : ComponentActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LogBus.log("[ViewPostActivity] onCreate()")

        val layout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(48, 48, 48, 48)
        }

        val titleView = TextView(this).apply {
            text = "Q20: View.post Attached vs Detached"
            textSize = 20f
        }

        val dummyButton = Button(this).apply {
            text = "Test View.post on Detached vs Attached View"
        }

        val backButton = Button(this).apply {
            text = "Back to Main"
            setOnClickListener { finish() }
        }

        layout.addView(titleView)
        layout.addView(dummyButton)
        layout.addView(backButton)

        // 1. Unattached View Test
        val unattachedView = Button(this)
        LogBus.log("Calling View.post on UNATTACHED View...")
        unattachedView.post {
            LogBus.log("[Unattached View] Runnable executed!")
        }
        LogBus.log("Is unattachedView attached to window? ${unattachedView.isAttachedToWindow}")

        dummyButton.setOnClickListener {
            LogBus.log("Calling View.post on ATTACHED dummyButton...")
            dummyButton.post {
                LogBus.log("[Attached View] dummyButton.post Runnable executed on UI thread: ${Thread.currentThread().name}")
            }
        }

        setContentView(layout)
    }
}
