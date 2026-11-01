package com.github.handler_looper.demos

import android.os.Handler
import android.os.HandlerThread
import android.os.Looper
import android.os.Message
import android.os.SystemClock
import com.github.handler_looper.ui.DemoItem
import com.github.handler_looper.ui.DemoPhase
import com.github.handler_looper.util.LogBus

object IntermediateDemo {

    val demos = listOf(
        // Q6: Post variants
        DemoItem(
            qNumber = 6,
            title = "Q6: Handler Post Variants & Timing",
            phase = DemoPhase.INTERMEDIATE,
            questionText = "What are the post variants and how are messages ordered?",
            explanationText = """
                Post Variants:
                - post(Runnable): Executes as soon as possible (uptimeMillis).
                - postDelayed(Runnable, delayMs): Schedules for (now + delayMs).
                - postAtTime(Runnable, uptimeMs): Schedules for specific uptimeMillis.
                - postAtFrontOfQueue(Runnable): Inserts at head of queue (when=0).
                
                Observed Execution Order in this demo:
                1. postAtFrontOfQueue (inserted at front)
                2. post (immediate)
                3. postAtTime (now + 300ms)
                4. postDelayed (now + 500ms)
            """.trimIndent(),
            runDemo = { runQ6Demo() }
        ),

        // Q7: Message.obtain vs new
        DemoItem(
            qNumber = 7,
            title = "Q7: Message.obtain() vs Message() Pooling",
            phase = DemoPhase.INTERMEDIATE,
            questionText = "Why should you use Message.obtain() instead of creating new Message() instances?",
            explanationText = """
                Android maintains a static pool of recycled Message objects (sPool up to MAX_POOL_SIZE = 50).
                - Calling Message.obtain() reuses an existing Message from the pool, avoiding GC churn and heap fragmentation.
                - Creating new Message() allocates new heap objects every time.
                
                This demo measures the execution time for obtaining vs allocating 10,000 messages.
            """.trimIndent(),
            runDemo = { runQ7Demo() }
        ),

        // Q8: sendMessage vs post
        DemoItem(
            qNumber = 8,
            title = "Q8: sendMessage() vs post() Dispatch Hierarchy",
            phase = DemoPhase.INTERMEDIATE,
            questionText = "What is the difference between sendMessage() and post(), and how does dispatchMessage work?",
            explanationText = """
                Both end up calling MessageQueue.enqueueMessage(), but:
                - post(Runnable) wraps the Runnable as msg.callback.
                - sendMessage(msg) passes a structured Message (what, arg1, arg2, obj).
                
                dispatchMessage Resolution Order:
                1. If msg.callback != null -> call msg.callback.run() (Runnable posted via post())
                2. Else if mCallback != null -> call mCallback.handleMessage(msg)
                3. Else -> call handleMessage(msg)
            """.trimIndent(),
            runDemo = { runQ8Demo() }
        ),

        // Q9: HandlerThread
        DemoItem(
            qNumber = 9,
            title = "Q9: HandlerThread Serial Processing",
            phase = DemoPhase.INTERMEDIATE,
            questionText = "What is HandlerThread and how does it execute tasks?",
            explanationText = """
                HandlerThread is a standard Thread subclass that automatically calls Looper.prepare() and Looper.loop().
                - All tasks posted to a HandlerThread execute serially on that single worker thread in FIFO order (or ordered by timestamp).
                - Eliminates concurrency/synchronization issues for sequential background tasks.
            """.trimIndent(),
            runDemo = { runQ9Demo() }
        ),

        // Q10: quit vs quitSafely
        DemoItem(
            qNumber = 10,
            title = "Q10: Looper.quit() vs Looper.quitSafely()",
            phase = DemoPhase.INTERMEDIATE,
            questionText = "What is the difference between quit() and quitSafely()?",
            explanationText = """
                - quit(): Immediately discards ALL messages in the queue (both pending due messages and future delayed messages) and exits loop().
                - quitSafely(): Delivers all messages that are ALREADY due for execution (when <= now), discards only future delayed messages, and then exits loop().
            """.trimIndent(),
            runDemo = { runQ10Demo() }
        ),

        // Q11: Post to main from anywhere
        DemoItem(
            qNumber = 11,
            title = "Q11: Posting to Main Thread (4 Ways)",
            phase = DemoPhase.INTERMEDIATE,
            questionText = "What are the different ways to post code to the Main Thread from a background thread?",
            explanationText = """
                1. Handler(Looper.getMainLooper()).post { ... }
                2. Activity.runOnUiThread { ... }
                3. View.post { ... }
                4. lifecycleScope.launch(Dispatchers.Main) { ... }
                
                All 4 techniques ultimately post a message to the Main Thread's Looper / MessageQueue!
            """.trimIndent(),
            runDemo = { runQ11Demo() }
        ),

        // Q12: Deprecated constructor
        DemoItem(
            qNumber = 12,
            title = "Q12: Handler() Deprecated Constructor",
            phase = DemoPhase.INTERMEDIATE,
            questionText = "Why is the Handler() no-arg constructor deprecated in Android 11 (API 30)?",
            explanationText = """
                The parameterless Handler() constructor implicitly binds to Looper.myLooper() on the calling thread.
                - If invoked on a thread without a Looper, it crashes at runtime.
                - If invoked on a background thread with a Looper, developers often incorrectly assumed it posted to the UI thread!
                - Explicitly passing the Looper (e.g., Handler(Looper.getMainLooper()) or Handler(Looper.myLooper()!!)) makes thread targeting intentional and bug-free.
            """.trimIndent(),
            runDemo = { runQ12Demo() }
        )
    )

    private fun runQ6Demo() {
        LogBus.log("--- Starting Q6: Post Variants & Timing Demo ---")
        val handler = Handler(Looper.getMainLooper())
        val now = SystemClock.uptimeMillis()

        handler.postDelayed({ LogBus.log("Task A: postDelayed(500ms)") }, 500)
        handler.postAtTime({ LogBus.log("Task B: postAtTime(now + 300ms)") }, now + 300)
        handler.post { LogBus.log("Task C: post(immediate)") }
        handler.postAtFrontOfQueue { LogBus.log("Task D: postAtFrontOfQueue()") }
    }

    private fun runQ7Demo() {
        LogBus.log("--- Starting Q7: Message.obtain() vs Message() Pooling Demo ---")
        val iterations = 10_000

        // 1. Using Message.obtain()
        val startObtain = SystemClock.elapsedRealtimeNanos()
        for (i in 0 until iterations) {
            val msg = Message.obtain()
            msg.what = i
            msg.recycle() // Return to pool
        }
        val obtainTimeUs = (SystemClock.elapsedRealtimeNanos() - startObtain) / 1000

        // 2. Using new Message()
        val startNew = SystemClock.elapsedRealtimeNanos()
        for (i in 0 until iterations) {
            @Suppress("ObsoleteSdkInt")
            val msg = Message()
            msg.what = i
        }
        val newTimeUs = (SystemClock.elapsedRealtimeNanos() - startNew) / 1000

        LogBus.log("Message.obtain() (with pool recycle) time for 10k items: ${obtainTimeUs}µs")
        LogBus.log("New Message() allocation time for 10k items: ${newTimeUs}µs")
        LogBus.log("Key takeaway: Message.obtain() reuses sPool objects to eliminate GC overhead.")
    }

    private fun runQ8Demo() {
        LogBus.log("--- Starting Q8: sendMessage() vs post() Dispatch Hierarchy Demo ---")

        val handler = object : Handler(
            Looper.getMainLooper(),
            Handler.Callback { msg ->
                LogBus.log("mCallback received message what=${msg.what}")
                false // Fallthrough to handleMessage
            }
        ) {
            override fun handleMessage(msg: Message) {
                LogBus.log("handleMessage received message what=${msg.what}")
            }
        }

        // Test 1: Message with Runnable callback (post)
        LogBus.log("1. Dispatching Message with msg.callback (via post):")
        val msgWithCallback = Message.obtain(handler) {
            LogBus.log("msg.callback Runnable executed! (mCallback and handleMessage skipped)")
        }
        handler.sendMessage(msgWithCallback)

        // Test 2: Message without Runnable callback (sendMessage)
        LogBus.log("2. Dispatching Message without msg.callback (via sendMessage):")
        handler.sendMessage(handler.obtainMessage(200, "Payload"))
    }

    private fun runQ9Demo() {
        LogBus.log("--- Starting Q9: HandlerThread Serial Processing Demo ---")

        val handlerThread = HandlerThread("DemoHandlerThread")
        handlerThread.start()

        val workerHandler = Handler(handlerThread.looper)

        for (i in 1..5) {
            val taskNum = i
            workerHandler.post {
                LogBus.log("Executing Task #$taskNum on thread: ${Thread.currentThread().name}")
                Thread.sleep(30) // Simulate work
                if (taskNum == 5) {
                    handlerThread.quitSafely()
                    LogBus.log("Completed all 5 tasks! HandlerThread quitSafely called.")
                }
            }
        }
    }

    private fun runQ10Demo() {
        LogBus.log("--- Starting Q10: Looper.quit() vs quitSafely() Demo ---")

        // Demo 1: quitSafely()
        val htSafely = HandlerThread("HT_Safely")
        htSafely.start()
        val hSafely = Handler(htSafely.looper)

        hSafely.post { LogBus.log("[quitSafely HT] Immediate task 1 starting...") }
        hSafely.post {
            LogBus.log("[quitSafely HT] Immediate task 2 calling htSafely.quitSafely()...")
            htSafely.quitSafely()
        }
        hSafely.post { LogBus.log("[quitSafely HT] Immediate task 3 (due before quit) WILL run!") }
        hSafely.postDelayed({ LogBus.log("[quitSafely HT] Delayed task (500ms) WILL BE DROPPED") }, 500)

        // Demo 2: quit()
        val htQuit = HandlerThread("HT_Quit")
        htQuit.start()
        val hQuit = Handler(htQuit.looper)

        hQuit.post { LogBus.log("[quit HT] Immediate task 1 calling htQuit.quit()...") }
        hQuit.post {
            htQuit.quit()
            LogBus.log("[quit HT] Called quit(). Subsequent tasks in queue will be dropped immediately.")
        }
        hQuit.post { LogBus.log("[quit HT] Task 3 (in queue) DROPPED!") }
    }

    private fun runQ11Demo() {
        LogBus.log("--- Starting Q11: Posting to Main Thread Demo ---")

        Thread({
            LogBus.log("Background thread (${Thread.currentThread().name}) starting execution...")

            // Technique 1: Handler(Looper.getMainLooper())
            Handler(Looper.getMainLooper()).post {
                LogBus.log("Technique 1: Handler(Looper.getMainLooper()).post executed on thread: ${Thread.currentThread().name}")
            }
        }, "WorkerForQ11").start()
    }

    private fun runQ12Demo() {
        LogBus.log("--- Starting Q12: Handler() Deprecated Constructor Demo ---")

        LogBus.log("Creating explicit Handler(Looper.getMainLooper())...")
        val mainExplicit = Handler(Looper.getMainLooper())
        LogBus.log("mainExplicit looper thread: ${mainExplicit.looper.thread.name}")

        Thread({
            Looper.prepare()
            @Suppress("DEPRECATION")
            val workerImplicit = Handler() // Binds to current worker thread's looper
            LogBus.log("workerImplicit created on worker thread implicitly binds to: ${workerImplicit.looper.thread.name}")
            Looper.myLooper()?.quit()
        }, "WorkerImplicitThread").start()
    }
}
