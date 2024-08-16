package com.example.work_manager_example

import android.content.Context
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequest
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.Worker
import androidx.work.WorkerParameters
import java.util.concurrent.TimeUnit

class PeriodicDBUploadManager(
    private val context: Context,
) : WorkerContract {
    override fun exec() {
        val workReq = startPeriodicWork()
        WorkManager.getInstance(context).apply {
            enqueueUniquePeriodicWork(
                "jobTag",
                ExistingPeriodicWorkPolicy.KEEP,
                workReq,
            )
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
    val periodicWorkRequest1: PeriodicWorkRequest =
        PeriodicWorkRequest
            .Builder(
                PeriodicDBUploadWorker::class.java,
                15,
                TimeUnit.MINUTES,
            ).build()

    val constraints =
        Constraints
            .Builder()
//            .setRequiredNetworkType(NetworkType.CONNECTED) // Optional constraints
            .build()

    val periodicWorkRequest =
        PeriodicWorkRequestBuilder<PeriodicDBUploadWorker>(
            15,
            TimeUnit.MINUTES,
            8,
            TimeUnit.MINUTES,
        ).setConstraints(constraints)
            .build()
//    return periodicWorkRequest
    return periodicWorkRequest1
}
