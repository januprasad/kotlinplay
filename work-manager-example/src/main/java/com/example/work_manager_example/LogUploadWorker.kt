package com.example.work_manager_example

import android.content.Context
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import androidx.work.Worker
import androidx.work.WorkerParameters
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import java.util.concurrent.TimeUnit

class LogUploadWorker(
    context: Context,
    params: WorkerParameters,
) : Worker(context, params) {
    override fun doWork(): Result {
        try {
            uploadLogData()
            return Result.success()
        } catch (e: Exception) {
            e.printStackTrace()
            return Result.failure()
        }
    }

    private fun uploadLogData() {
        runBlocking {
            val state = (1..10).random() % 2 == 0
            delay(100L) // just for some delay
            if (state) {
                throw Exception("")
            }
        }
    }
}

// data class Response(val msg: String)

fun createUploadLogWorkRequest() =
    OneTimeWorkRequest
        .Builder(LogUploadWorker::class.java)
        .build()

fun createUploadLogWorkRequest(delay: Long) =
    OneTimeWorkRequest
        .Builder(LogUploadWorker::class.java)
        .setInitialDelay(delay, TimeUnit.MILLISECONDS)
        .build()

fun createUploadLogWorkRequest1(
    context: Context,
    delay: Long,
) {
    val uniqueWork1 =
        OneTimeWorkRequest
            .Builder(LogUploadWorker::class.java)
            .setInitialDelay(delay, TimeUnit.MILLISECONDS)
            .build()

    val uniqueWork2 =
        OneTimeWorkRequest
            .Builder(LogUploadWorker::class.java)
            .setInitialDelay(delay, TimeUnit.MILLISECONDS)
            .build()

    val finishingWork =
        OneTimeWorkRequest
            .Builder(LogUploadWorker::class.java)
            .setInitialDelay(delay, TimeUnit.MILLISECONDS)
            .build()

    WorkManager
        .getInstance(context)
        .beginUniqueWork(
            "second_method_name",
            ExistingWorkPolicy.KEEP,
            listOf(uniqueWork1, uniqueWork2),
        ).then(finishingWork)
        .enqueue()
}
