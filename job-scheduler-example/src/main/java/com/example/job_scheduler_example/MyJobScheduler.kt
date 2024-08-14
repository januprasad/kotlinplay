package com.example.job_scheduler_example

import android.app.job.JobParameters
import android.app.job.JobService
import android.util.Log

class MyJobScheduler : JobService() {
    override fun onStartJob(p0: JobParameters?): Boolean {
        Log.v("Job", "Starting")
        return true
    }

    override fun onStopJob(p0: JobParameters?): Boolean {
        Log.v("Job", "Ending")
        return false
    }
}
