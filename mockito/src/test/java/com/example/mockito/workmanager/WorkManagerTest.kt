package com.example.mockito.workmanager

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.work.ListenableWorker
import androidx.work.WorkerParameters
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito

@RunWith(RobolectricTestRunner::class)
class ClassTest{
    private lateinit classToTest: ClassToTest
    private lateinit context: Context

    @Before
    fun setUp(){
        context = ApplicationProvider.getApplicationContext()
        val config = Configuration.Builder()
            .setWorkerFactory((WorkerFactory.getDefaultWorkerFactory()))
            .setExecutor(SynchronousExecutor())
            .setMinimumLoggingLevel(Log.DEBUG)
            .build()
        WorkManagerTestInitHelper.initializeTestWorkManager(context, config)
    }
}