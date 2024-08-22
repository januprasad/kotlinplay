package com.example.work_manager_example.timer

import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import androidx.work.CoroutineWorker
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkerParameters
import com.example.work_manager_example.R
import java.util.concurrent.TimeUnit

const val TAG = "TimerUpdate"

class TimerWorker(
    context: Context,
    workerParams: WorkerParameters,
) : CoroutineWorker(context, workerParams) {
    override suspend fun doWork(): Result {
        val currentTime = 0L
        // Simulate the timer increment (or decrement depending on your use case)
        val newTime = currentTime + 1 // Increment by 1 second (for example)
        return Result.success()
    }

    private fun showNotification(time: Long) {
        val notificationManager = applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val notification =
            NotificationCompat
                .Builder(applicationContext, "timer_channel")
                .setContentTitle("Timer")
                .setContentText("Time: $time seconds")
                .setSmallIcon(R.drawable.ic_timer)
                .build()

        notificationManager.notify(1, notification)
    }

    fun scheduler(context: Context) {
        val timerWorkRequest =
            PeriodicWorkRequestBuilder<TimerWorker>(1, TimeUnit.SECONDS)
                .setInitialDelay(1, TimeUnit.SECONDS)
                .addTag(TAG)
                .build()

        WorkManager.getInstance(context).enqueueUniquePeriodicWork(
            "TimerWork",
            ExistingPeriodicWorkPolicy.UPDATE,
            timerWorkRequest,
        )
    }
}
