package com.example.work_manager_example

import android.widget.Toast

class ToastManager(private val activityContext: WorkManagerActivity){
    fun showToast(msg: String) {
        Toast.makeText(activityContext, msg, Toast.LENGTH_SHORT).show()
    }
}