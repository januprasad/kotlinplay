package com.example.job_scheduler_example

import android.app.job.JobParameters
import android.app.job.JobService
import android.util.Log

class MyJobService : JobService() {
    override fun onStartJob(params: JobParameters?): Boolean {
        Log.d("MyJobService", "Job started")
        
        // Simulate some work here (e.g., fetching data from a server)
        Thread {
            try {
                Thread.sleep(3000) // Simulate a long-running task
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }

            Log.d("MyJobService", "Job finished")
            jobFinished(params, false)
        }.start()

        // Return true if the job is running asynchronously
        return true
    }

    override fun onStopJob(params: JobParameters?): Boolean {
        Log.d("MyJobService", "Job stopped before completion")
        // Return true if the job should be rescheduled
        return true
    }
}
