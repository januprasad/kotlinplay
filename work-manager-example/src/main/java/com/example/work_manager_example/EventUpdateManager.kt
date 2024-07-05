package com.example.work_manager_example

import androidx.work.WorkInfo
import androidx.work.WorkManager

class EventUpdateManager(private val activityContext: WorkManagerActivity) {
    private var toastManager: ToastManager = ToastManager(activityContext)

    fun exec() {
        val workReq = createEventWorkerRequest(Events.Test)
        WorkManager.getInstance(activityContext).enqueue(workReq)
        WorkManager.getInstance(activityContext).getWorkInfoByIdLiveData(workReq.id)
            .observe(activityContext) { workInfo ->
                if (workInfo != null) {
                    when (workInfo.state) {
                        WorkInfo.State.SUCCEEDED -> {
                            toastManager.showToast(activityContext.getString(R.string.successfully_updated_event))
                        }

                        WorkInfo.State.FAILED -> {
                            toastManager.showToast(activityContext.getString(R.string.failed_to_upload_due_to_connectivity_issues))
                        }

                        else -> {}
                    }
                }
            }
    }
}