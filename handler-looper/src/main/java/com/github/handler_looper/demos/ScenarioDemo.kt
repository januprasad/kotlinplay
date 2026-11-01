package com.github.handler_looper.demos

import android.os.Handler
import android.os.HandlerThread
import android.os.Looper
import com.github.handler_looper.ui.DemoItem
import com.github.handler_looper.ui.DemoPhase
import com.github.handler_looper.util.LogBus
import java.util.concurrent.Executor

object ScenarioDemo {

    val demos = listOf(
        // Q24: Crashing snippet
        DemoItem(
            qNumber = 24,
            title = "Q24: Background Handler Crash & Fix",
            phase = DemoPhase.SCENARIO,
            questionText = "Why does new Handler() crash on a background thread and how do you fix it?",
            explanationText = """
                Snippet:
                Thread {
                    val handler = Handler(Looper.myLooper()!!)
                    handler.post { ... }
                }.start()
                
                Cause:
                Looper.myLooper() returns null on worker threads because Looper.prepare() was not called.
                
                Fix:
                Add Looper.prepare() before creating Handler and call Looper.loop() to start processing tasks!
            """.trimIndent(),
            runDemo = { runQ24Demo() }
        ),

        // Q25: Code after loop()
        DemoItem(
            qNumber = 25,
            title = "Q25: Code Execution After Looper.loop()",
            phase = DemoPhase.SCENARIO,
            questionText = "When does code placed after Looper.loop() inside thread run() execute?",
            explanationText = """
                Looper.loop() is a blocking infinite loop that continuously polls MessageQueue for messages.
                - Code placed after Looper.loop() will NEVER execute while the Looper is running!
                - It executes ONLY after looper.quit() or looper.quitSafely() is invoked, causing loop() to terminate and return.
            """.trimIndent(),
            runDemo = { runQ25Demo() }
        ),

        // Q26: Which thread runs it?
        DemoItem(
            qNumber = 26,
            title = "Q26: Thread Execution Context of Handler.post",
            phase = DemoPhase.SCENARIO,
            questionText = "If Thread A calls handler.post(runnable) on a Handler bound to Thread B, which thread executes the Runnable?",
            explanationText = """
                Thread B (the thread owning the Handler's Looper) ALWAYS executes the Runnable!
                - Thread A is merely the caller / producer thread that enqueues the Runnable into Thread B's MessageQueue.
                - Thread B's Looper dequeues and executes the Runnable.
            """.trimIndent(),
            runDemo = { runQ26Demo() }
        ),

        // Q27: Sequential executor design
        DemoItem(
            qNumber = 27,
            title = "Q27: SerialExecutor using HandlerThread",
            phase = DemoPhase.SCENARIO,
            questionText = "How do you build a thread-safe, single-threaded SerialExecutor using HandlerThread?",
            explanationText = """
                HandlerThread + Handler provides a built-in serial FIFO task runner.
                By implementing Executor interface:
                - submit(task) delegates to handler.post(task).
                - Guarantees strict sequential execution without complex mutex locks!
                - Clean shutdown using handlerThread.quitSafely().
            """.trimIndent(),
            runDemo = { runQ27Demo() }
        )
    )

    private fun runQ24Demo() {
        LogBus.log("--- Starting Q24: Background Handler Crash & Fix Demo ---")

        // Step 1: Demonstrate Crash
        Thread({
            LogBus.log("1. Running flawed code without Looper.prepare()...")
            try {
                val threadLooper = Looper.myLooper()
                    ?: error("Can't create handler inside thread ${Thread.currentThread().name} that has not called Looper.prepare()")
                val handler = Handler(threadLooper)
                handler.post { }
            } catch (e: Exception) {
                LogBus.log("CAUGHT EXPECTED CRASH: ${e.message}")
            }
        }, "FlawedWorker").start()

        // Step 2: Fixed Version
        Thread({
            LogBus.log("2. Running FIXED version with Looper.prepare() & Looper.loop()...")
            Looper.prepare()
            val workerHandler = Handler(Looper.myLooper()!!)
            workerHandler.post {
                LogBus.log("Fixed worker handler task successfully executed on thread: ${Thread.currentThread().name}")
                Looper.myLooper()?.quit()
            }
            Looper.loop()
            LogBus.log("Fixed worker thread exiting cleanly after quit().")
        }, "FixedWorker").start()
    }

    private fun runQ25Demo() {
        LogBus.log("--- Starting Q25: Code Execution After Looper.loop() Demo ---")

        val thread = Thread({
            LogBus.log("Worker thread: Calling Looper.prepare()...")
            Looper.prepare()

            val handler = Handler(Looper.myLooper()!!)
            handler.postDelayed({
                LogBus.log("Worker thread task: Calling looper.quit() after 100ms...")
                Looper.myLooper()?.quit()
            }, 100)

            LogBus.log("Worker thread: BEFORE Looper.loop() call.")
            Looper.loop()
            LogBus.log("Worker thread: AFTER Looper.loop() call! (Only executed because quit() returned from loop())")
        }, "Q25Worker")

        thread.start()
    }

    private fun runQ26Demo() {
        LogBus.log("--- Starting Q26: Thread Execution Context Demo ---")

        val mainHandler = Handler(Looper.getMainLooper())

        Thread({
            val callingThread = Thread.currentThread().name
            LogBus.log("Thread A ($callingThread) is calling mainHandler.post(runnable)...")

            mainHandler.post {
                val executionThread = Thread.currentThread().name
                LogBus.log("Runnable EXECUTED on thread: $executionThread (Caller was: $callingThread)")
            }
        }, "ProducerThreadA").start()
    }

    private fun runQ27Demo() {
        LogBus.log("--- Starting Q27: SerialExecutor using HandlerThread Demo ---")

        class SerialExecutor : Executor {
            private val handlerThread = HandlerThread("SerialExecutorHT").apply { start() }
            private val handler = Handler(handlerThread.looper)

            override fun execute(command: Runnable) {
                handler.post(command)
            }

            fun shutdown() {
                handlerThread.quitSafely()
                LogBus.log("SerialExecutor shut down safely.")
            }
        }

        val executor = SerialExecutor()

        LogBus.log("Submitting 5 tasks to SerialExecutor from 3 different threads...")

        for (i in 1..5) {
            val taskId = i
            Thread({
                executor.execute {
                    LogBus.log("Serial Task #$taskId executing on thread: ${Thread.currentThread().name}")
                    Thread.sleep(20)
                    if (taskId == 5) {
                        executor.shutdown()
                    }
                }
            }, "Producer-$i").start()
        }
    }
}
