package com.example.work_manager_example

import android.content.Context
import androidx.work.Data
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.Worker
import androidx.work.WorkerParameters
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

class EventUpdateWorker(context: Context, params: WorkerParameters) : Worker(context, params) {
    override fun doWork(): Result {
        val inputData = inputData // Retrieve input data
        val stringValue = inputData.getString(Events.Event)
        updateEvent(stringValue)
        return Result.success()
    }

    private fun updateEvent(stringValue: String?) {
        //probably some api call to updateEvent
        runBlocking {
            delay(1000L) //just for some delay
        }
    }
}

fun createEventWorkerRequest(event: String): OneTimeWorkRequest {
    val inputData = Data.Builder()
        .putString(Events.Event, event)
        .build()

    return OneTimeWorkRequestBuilder<EventUpdateWorker>()
        .setInputData(inputData)
        .build()
}

object Events {
    const val Event = "Event"
    const val Test = "Test"
}