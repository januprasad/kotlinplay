package com.example.work_manager_example

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.work_manager_example.databinding.ActivityWorkManagerBinding
import com.google.android.material.snackbar.Snackbar

class WorkManagerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWorkManagerBinding
    private lateinit var logUploadManager: LogUploadManager
    private lateinit var eventUpdateManager: EventUpdateManager
    private lateinit var periodicDBUploadManager: PeriodicDBUploadManager

    val REQUEST_EXTERNAL_STORAGE = 1000
    private val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission(),
        ) { isGranted: Boolean ->
            if (isGranted) {
                sendNotification(this)

                // Permission is granted. Continue the action or workflow in your
                // app.
            } else {
                // Explain to the user that the feature is unavailable because the
                // features requires a permission that the user has denied. At the
                // same time, respect the user's decision. Don't link to system
                // settings in an effort to convince the user to change their
                // decision.
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        binding = ActivityWorkManagerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        logUploadManager = LogUploadManager(this)
        eventUpdateManager = EventUpdateManager(this)
        periodicDBUploadManager = PeriodicDBUploadManager(this)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.buttonWorkerSampleToast.setOnClickListener {
            logUploadManager.exec()
        }
        binding.buttonWorkerSampleAfterTwoSeconds.setOnClickListener {
            logUploadManager.execAfter2Seconds()
        }
        binding.buttonWorkerSampleCancelAll.setOnClickListener {
            logUploadManager.execAfter2Seconds()
            logUploadManager.cancelAll()
        }
        binding.buttonWorkerSampleEventUpdate.setOnClickListener {
            eventUpdateManager.exec()
        }
        binding.buttonWorkerSampleEventCancelAndRestart.setOnClickListener {
            eventUpdateManager.exec()
            eventUpdateManager.cancelAndRestart()
        }
        binding.buttonWorkerPeriodic.setOnClickListener {
            periodicDBUploadManager.exec()
        }

        binding.buttonWorkerSampleImageDownloader.setOnClickListener {
            when {
                ContextCompat.checkSelfPermission(
                    this,
                    android.Manifest.permission.POST_NOTIFICATIONS,
                ) == PackageManager.PERMISSION_GRANTED -> {
                    // You can use the API that requires the permission.
                    Log.e(TAG, "onCreate: PERMISSION GRANTED")
                    sendNotification(this)
                }
                shouldShowRequestPermissionRationale(android.Manifest.permission.POST_NOTIFICATIONS) -> {
                    Snackbar
                        .make(
                            binding.root,
                            "Notification blocked",
                            Snackbar.LENGTH_LONG,
                        ).setAction("Settings") {
                            // Responds to click on the action
                        }.show()
                    //  Toast.makeText(this, "NOT ALLOWED", Toast.LENGTH_SHORT).show()
                }
                else -> {
                    Log.e(TAG, "onCreate: ask for permissions")
                    // You can directly ask for the permission.
                    // The registered ActivityResultCallback gets the result of this request.
                    //  if (Build.VERSION.SDK_INT >= 33) {
                    //   Log.e(TAG, "onCreate: 33" )
                    requestPermissionLauncher.launch(
                        android.Manifest.permission.POST_NOTIFICATIONS,
                    )
                    // }
                }
            }
        }
    }

    companion object {
        const val TAG = "MainActivity"
        const val NOTIFICATION_MESSAGE_TAG = "message from notification"

        fun newIntent(context: Context) =
            Intent(context, WorkManagerActivity::class.java).apply {
                putExtra(
                    NOTIFICATION_MESSAGE_TAG,
                    "Hi ☕\uD83C\uDF77\uD83C\uDF70",
                )
            }
    }
}
