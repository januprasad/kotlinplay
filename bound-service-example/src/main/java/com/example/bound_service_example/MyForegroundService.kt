package com.example.bound_service_example

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder

class MyForegroundService : Service() {
    private val notificationChannelId = "my_foreground_notification_channel"
    private val notificationId = 1

    // ... other service logic

    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
    }

    private fun createNotificationChannel() {
        val channel =
            NotificationChannel(
                notificationChannelId,
                "Foreground Service Channel",
                NotificationManager.IMPORTANCE_DEFAULT,
            )
        channel.description = "Channel for foreground service notifications"
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }

    override fun onStartCommand(
        intent: Intent?,
        flags: Int,
        startId: Int,
    ): Int {
        // Handle starting the foreground service here (e.g., start background tasks)
        showForegroundNotification()
        return START_STICKY // Adjust restart behavior as needed (e.g., START_NOT_STICKY)
    }

    private fun showForegroundNotification() {
        val notificationIntent = Intent(this, MainActivity::class.java) // Replace with your activity
        val pendingIntent =
            PendingIntent.getActivity(
                this,
                0,
                notificationIntent,
                PendingIntent.FLAG_IMMUTABLE or
                    PendingIntent.FLAG_UPDATE_CURRENT,
            )

        val notification =
            Notification
                .Builder(this, notificationChannelId)
                .setContentTitle("Foreground Service")
                .setContentText("Service is running...")
                .setSmallIcon(R.drawable.ic_notification) // Replace with your icon resource
                .setContentIntent(pendingIntent)
                .setOngoing(true) // Indicate ongoing notification
                .build()

        startForeground(notificationId, notification)
    }

    override fun onDestroy() {
        super.onDestroy()
        // Cleanup tasks for the service
        stopForeground(true) // Remove notification when service stops
    }

    override fun onBind(intent: Intent?): IBinder? = null
}
