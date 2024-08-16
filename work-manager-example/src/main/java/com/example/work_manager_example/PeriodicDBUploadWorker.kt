package com.example.work_manager_example

import android.content.Context
import androidx.work.Constraints
import androidx.work.PeriodicWorkRequest
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkRequest
import androidx.work.Worker
import androidx.work.WorkerParameters
import java.util.concurrent.TimeUnit

class PeriodicDBUploadManager(
    private val context: Context,
) : WorkerContract {
    private lateinit var workReq: WorkRequest

    override fun exec() {
        workReq = startPeriodicWork()
        WorkManager.getInstance(context).apply {
            enqueue(workReq)
        }
    }
}

//
class PeriodicDBUploadWorker(
    appContext: Context,
    workerParams: WorkerParameters,
) : Worker(appContext, workerParams) {
    override fun doWork(): Result {
        // Your work logic here
        println("Uploading......")
        return Result.success()
    }
}

fun startPeriodicWork(): PeriodicWorkRequest {
    val constraints =
        Constraints
            .Builder()
//            .setRequiredNetworkType(NetworkType.CONNECTED) // Optional constraints
            .build()

    val periodicWorkRequest =
        PeriodicWorkRequestBuilder<PeriodicDBUploadWorker>(
            20,
            TimeUnit.SECONDS,
        ).setConstraints(constraints)
            .build()
    return periodicWorkRequest
}
