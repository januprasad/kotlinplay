package com.example.job_scheduler_example

import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.content.Context
import android.os.Build
import android.util.Log

class JobSchedulerHelper(private val context: Context) {

    companion object {
        const val JOB_ID = 123
    }

    fun scheduleJob() {
        val componentName = ComponentName(context, MyJobService::class.java)
        val jobInfoBuilder = JobInfo.Builder(JOB_ID, componentName)
        
        // Set criteria for the job
        jobInfoBuilder.setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
        jobInfoBuilder.setPersisted(true) // Persist across device reboots

        // Schedule job to repeat every 15 minutes (minimum interval for periodic jobs)
        jobInfoBuilder.setMinimumLatency(15 * 60 * 1000) // 15 minutes

        val jobScheduler = context.getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler
        val result = jobScheduler.schedule(jobInfoBuilder.build())

        if (result == JobScheduler.RESULT_SUCCESS) {
            Log.v("JobSchedulerHelper", "Job scheduled successfully")
        } else {
            Log.v("JobSchedulerHelper", "Job scheduling failed")
        }
    }

    fun cancelJob() {
        val jobScheduler = context.getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler
        jobScheduler.cancel(JOB_ID)
        Log.d("JobSchedulerHelper", "Job canceled")
    }
}
