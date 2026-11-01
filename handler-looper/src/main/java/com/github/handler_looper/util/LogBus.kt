package com.github.handler_looper.util

import android.os.Handler
import android.os.Looper
import android.os.SystemClock
import android.util.Log
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * LogBus captures logs with timestamps relative to app start and the name of the current thread.
 * Logs are output to Logcat (tag: HandlerLab) and emitted via StateFlow to the UI log panel.
 */
object LogBus {
    private val startTime = SystemClock.uptimeMillis()
    private val mainHandler = Handler(Looper.getMainLooper())

    private val _logs = MutableStateFlow<List<String>>(emptyList())
    val logs: StateFlow<List<String>> = _logs.asStateFlow()

    fun log(msg: String) {
        val elapsed = SystemClock.uptimeMillis() - startTime
        val threadName = Thread.currentThread().name
        val line = "[$threadName] +${elapsed}ms  $msg"

        Log.d("HandlerLab", line)

        if (Looper.myLooper() == Looper.getMainLooper()) {
            _logs.value = _logs.value + line
        } else {
            mainHandler.post {
                _logs.value = _logs.value + line
            }
        }
    }

    fun clear() {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            _logs.value = emptyList()
        } else {
            mainHandler.post {
                _logs.value = emptyList()
            }
        }
    }
}
