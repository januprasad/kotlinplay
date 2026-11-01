package com.github.handler_looper.demos

import android.content.Intent
import android.os.Handler
import android.os.HandlerThread
import android.os.Looper
import android.os.Message
import android.os.MessageQueue
import android.os.SystemClock
import com.github.handler_looper.ui.DemoItem
import com.github.handler_looper.ui.DemoPhase
import com.github.handler_looper.util.LogBus
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

object AdvancedDemo {

    val demos = listOf(
        // Q13: Sleep without CPU burn
        DemoItem(
            qNumber = 13,
            title = "Q13: Looper Idle State & epoll_wait",
            phase = DemoPhase.ADVANCED,
            questionText = "How does Looper wait for messages without consuming 100% CPU in an infinite loop?",
            explanationText = """
                Inside MessageQueue.next(), when mMessages is null or no message is due, the thread enters nativePollOnce(ptr, timeout).
                - Uses Linux epoll_wait() system call under the hood.
                - Puts the thread into a Kernel SLEEP state (0% CPU consumption).
                - When a new message is posted from any thread, enqueueMessage() calls nativeWake(ptr) via Linux eventfd to awaken the thread.
            """.trimIndent(),
            runDemo = { runQ13Demo() }
        ),

        // Q14: Loop != ANR
        DemoItem(
            qNumber = 14,
            title = "Q14: Why Looper.loop() Does Not Cause ANR",
            phase = DemoPhase.ADVANCED,
            questionText = "Why doesn't the infinite loop in Looper.loop() cause an Application Not Responding (ANR)?",
            explanationText = """
                ANR occurs when the main thread fails to process input/touch/draw events within the timeout window (e.g. 5s for input, 10-20s for BroadcastReceiver/Service).
                - Looper.loop() itself does NOT block event processing—it IS the event processing mechanism!
                - An ANR happens ONLY when a specific Message handler callback blocks the thread (e.g. Thread.sleep() or slow DB/Network IO), preventing Looper from picking up subsequent input messages.
            """.trimIndent(),
            runDemo = { runQ14Demo() }
        ),

        // Q15: Sync barrier
        DemoItem(
            qNumber = 15,
            title = "Q15: Synchronous Barrier & Asynchronous Messages",
            phase = DemoPhase.ADVANCED,
            questionText = "What is a Synchronous Barrier in MessageQueue and how do Asynchronous Messages work?",
            explanationText = """
                A Synchronous Barrier is a special Message with target = null placed in MessageQueue.
                - When Looper encounters a barrier, it bypasses all standard synchronous messages in the queue.
                - It only processes messages marked as Asynchronous (msg.setAsynchronous(true)).
                - Used internally by ViewRootImpl to prioritize UI layout/draw passes over background tasks!
                - Once removed (removeSyncBarrier(token)), normal message dispatch resumes.
            """.trimIndent(),
            runDemo = { runQ15Demo() }
        ),

        // Q16: IdleHandler
        DemoItem(
            qNumber = 16,
            title = "Q16: MessageQueue.IdleHandler",
            phase = DemoPhase.ADVANCED,
            questionText = "What is IdleHandler and when does it run?",
            explanationText = """
                MessageQueue.IdleHandler is an interface with queueIdle(): Boolean.
                - Invoked when the MessageQueue has exhausted all due messages and is about to block in nativePollOnce().
                - Return value controls persistence:
                  * false -> Automatically removed after running once.
                  * true -> Remains registered and fires every time the queue becomes idle.
                - Ideal for deferring non-urgent work (e.g., pre-fetching data, analytics) until UI is completely idle.
            """.trimIndent(),
            runDemo = { runQ16Demo() }
        ),

        // Q17: Memory leak
        DemoItem(
            qNumber = 17,
            title = "Q17: Handler Memory Leaks & WeakReference Fix",
            phase = DemoPhase.ADVANCED,
            questionText = "How can a Handler cause an Activity memory leak, and how do you prevent it?",
            explanationText = """
                Leak Mechanism:
                Non-static inner Handler / anonymous Runnable holds an implicit reference to outer Activity.
                When a delayed message is posted, MessageQueue -> Message -> Target Handler -> Outer Activity.
                If Activity is finished/rotated, Garbage Collector cannot reclaim it until the delayed message executes!
                
                Fix:
                1. Use static inner class / top-level Handler with WeakReference<Activity>.
                2. Call handler.removeCallbacksAndMessages(null) in Activity.onDestroy().
                
                Tapping this launches LeakyActivity to demonstrate the leak & fix live!
            """.trimIndent(),
            runDemo = { ctx ->
                ctx.startActivity(Intent(ctx, LeakyActivity::class.java))
            }
        ),

        // Q18: removeCallbacks variants
        DemoItem(
            qNumber = 18,
            title = "Q18: Clearing Handlers with removeCallbacks",
            phase = DemoPhase.ADVANCED,
            questionText = "How do removeCallbacks(Runnable) and removeCallbacksAndMessages(null) work?",
            explanationText = """
                - removeCallbacks(runnable): Removes all instances of that specific Runnable matching object identity from MessageQueue.
                - removeCallbacksAndMessages(token):
                  * Passing null removes ALL pending messages and runnables posted by that Handler!
                  * Passing a token object removes messages posted with that specific token.
            """.trimIndent(),
            runDemo = { runQ18Demo() }
        ),

        // Q19: Handler.Callback
        DemoItem(
            qNumber = 19,
            title = "Q19: Handler.Callback Interception",
            phase = DemoPhase.ADVANCED,
            questionText = "How does Handler.Callback allow message interception without subclassing Handler?",
            explanationText = """
                Handler constructor accepts Handler.Callback interface.
                In dispatchMessage(msg):
                If mCallback != null -> call mCallback.handleMessage(msg).
                - If Callback returns true: Message is consumed! Handler's override handleMessage(msg) is skipped.
                - If Callback returns false: Execution falls through to Handler's override handleMessage(msg).
            """.trimIndent(),
            runDemo = { runQ19Demo() }
        ),

        // Q20: Everything posts to main Looper
        DemoItem(
            qNumber = 20,
            title = "Q20: View.post vs runOnUiThread vs Handler",
            phase = DemoPhase.ADVANCED,
            questionText = "How do View.post, Activity.runOnUiThread, and Handler compare?",
            explanationText = """
                - runOnUiThread: Checks if calling thread == UI thread. If yes, runs Runnable inline immediately. If no, posts to Main Looper.
                - View.post: If View is attached to window (AttachInfo != null), posts to Main Looper. If detached, queues Runnable in HandlerActionQueue and flushes when attached!
                - Handler(Looper.getMainLooper()).post: Always posts to Main Looper queue (never inline).
                
                Tapping this launches ViewPostActivity to demonstrate detached vs attached View.post!
            """.trimIndent(),
            runDemo = { ctx ->
                ctx.startActivity(Intent(ctx, ViewPostActivity::class.java))
            }
        ),

        // Q21: Handler <-> Coroutines
        DemoItem(
            qNumber = 21,
            title = "Q21: Handler vs Dispatchers.Main vs Dispatchers.Main.immediate",
            phase = DemoPhase.ADVANCED,
            questionText = "What is the difference between Dispatchers.Main, Dispatchers.Main.immediate, and Handler.post?",
            explanationText = """
                - Dispatchers.Main: Posts the coroutine task to the Main Looper queue (always deferred).
                - Dispatchers.Main.immediate: If invoked ALREADY on the Main thread, executes inline synchronously! If on background thread, posts to Main Looper queue.
                - Handler(Looper.getMainLooper()).post: Always posts to Main Looper queue.
            """.trimIndent(),
            runDemo = { runQ21Demo() }
        ),

        // Q22: Message ordering
        DemoItem(
            qNumber = 22,
            title = "Q22: Message Ordering & SystemClock.uptimeMillis",
            phase = DemoPhase.ADVANCED,
            questionText = "How are messages sorted inside MessageQueue?",
            explanationText = """
                MessageQueue is a singly-linked list sorted strictly by msg.when timestamp (based on SystemClock.uptimeMillis()).
                - A message with when = now + 100ms will sit behind a message with when = now + 50ms, regardless of call order.
                - MessageQueue.enqueueMessage() performs linear insertion sorting by msg.when.
            """.trimIndent(),
            runDemo = { runQ22Demo() }
        ),

        // Q23: uptimeMillis vs wall clock
        DemoItem(
            qNumber = 23,
            title = "Q23: SystemClock.uptimeMillis vs currentTimeMillis",
            phase = DemoPhase.ADVANCED,
            questionText = "Why does Handler timing rely on uptimeMillis instead of currentTimeMillis?",
            explanationText = """
                - System.currentTimeMillis(): Wall clock time. Subject to user changes, timezone changes, and NTP updates.
                - SystemClock.uptimeMillis(): Monotonic clock since system boot. Does NOT jump backward/forward. PAUSES during deep CPU sleep (suspend).
                - Handlers use uptimeMillis so postDelayed timers are reliable and unaffected by wall-clock changes, but they pause when the device sleeps! (Use AlarmManager/WorkManager for deep sleep wakeups).
            """.trimIndent(),
            runDemo = { runQ23Demo() }
        )
    )

    private fun runQ13Demo() {
        LogBus.log("--- Starting Q13: Looper Idle State & epoll_wait Demo ---")

        val ht = HandlerThread("EpollDemoThread")
        ht.start()

        LogBus.log("HandlerThread state immediately after start (queue empty): ${ht.state}")
        Thread.sleep(50)
        LogBus.log("HandlerThread state while idle in nativePollOnce: ${ht.state}")

        val handler = Handler(ht.looper)
        handler.post {
            LogBus.log("Worker thread awakened via eventfd! Executing task on: ${Thread.currentThread().name}")
            LogBus.log("HandlerThread state while executing: ${Thread.currentThread().state}")
            ht.quitSafely()
        }
    }

    private fun runQ14Demo() {
        LogBus.log("--- Starting Q14: Why Looper.loop() Does Not Cause ANR Demo ---")
        LogBus.log("Looper.loop() handles messages one by one in an endless loop.")
        LogBus.log("When queue is empty, epoll_wait puts the thread into low-power sleep state (0% CPU).")
        LogBus.log("ANR only happens if a single Handler callback blocks for > 5 seconds!")
        LogBus.log("Simulating a small safe delay (200ms) on UI thread to demonstrate UI thread block...")

        val start = SystemClock.uptimeMillis()
        Thread.sleep(200)
        val end = SystemClock.uptimeMillis()
        LogBus.log("UI thread unblocked after ${end - start}ms. (ANR threshold is 5,000ms for input events).")
    }

    private fun runQ15Demo() {
        LogBus.log("--- Starting Q15: Synchronous Barrier Demo ---")
        val handler = Handler(Looper.getMainLooper())
        val queue = Looper.getMainLooper().queue

        try {
            val postBarrierMethod = MessageQueue::class.java.getMethod("postSyncBarrier")
            val removeBarrierMethod = MessageQueue::class.java.getMethod("removeSyncBarrier", Int::class.javaPrimitiveType)

            // Post Barrier
            val token = postBarrierMethod.invoke(queue) as Int
            LogBus.log("Posted Sync Barrier token=$token to MessageQueue!")

            // 1. Post Synchronous Message (Standard)
            handler.post {
                LogBus.log("Synchronous Message executed! (Should only run AFTER barrier removal)")
            }

            // 2. Post Asynchronous Message
            val asyncMsg = Message.obtain(handler) {
                LogBus.log("Asynchronous Message executed FIRST! (Bypassed Sync Barrier)")

                // Remove barrier after async message runs
                removeBarrierMethod.invoke(queue, token)
                LogBus.log("Removed Sync Barrier token=$token. Synchronous messages now unblocked!")
            }
            asyncMsg.isAsynchronous = true
            handler.sendMessage(asyncMsg)

        } catch (e: Exception) {
            LogBus.log("Sync Barrier API restricted via reflection on this Android version: ${e.message}")
            LogBus.log("Explanation: Sync Barrier hides normal messages and allows only setAsynchronous(true) messages (used by ViewRootImpl for 60/120fps UI rendering).")
        }
    }

    private fun runQ16Demo() {
        LogBus.log("--- Starting Q16: MessageQueue.IdleHandler Demo ---")
        val queue = Looper.getMainLooper().queue

        var idleCount = 0
        val idleHandler = MessageQueue.IdleHandler {
            idleCount++
            LogBus.log("IdleHandler.queueIdle() triggered! (Count = $idleCount)")
            false // Return false to run once and unregister
        }

        queue.addIdleHandler(idleHandler)
        LogBus.log("Registered one-shot IdleHandler. Will fire as soon as main Looper queue is drained.")
    }

    private fun runQ18Demo() {
        LogBus.log("--- Starting Q18: Clearing Handlers with removeCallbacks Demo ---")
        val handler = Handler(Looper.getMainLooper())

        val r1 = Runnable { LogBus.log("Runnable 1 executed") }
        val r2 = Runnable { LogBus.log("Runnable 2 executed (SHOULD BE REMOVED)") }
        val r3 = Runnable { LogBus.log("Runnable 3 executed") }

        handler.postDelayed(r1, 100)
        handler.postDelayed(r2, 100)
        handler.postDelayed(r3, 100)

        LogBus.log("Posted r1, r2, r3 with 100ms delay. Now calling handler.removeCallbacks(r2)...")
        handler.removeCallbacks(r2)
    }

    private fun runQ19Demo() {
        LogBus.log("--- Starting Q19: Handler.Callback Interception Demo ---")

        val handler = object : Handler(
            Looper.getMainLooper(),
            Handler.Callback { msg ->
                if (msg.what == 1) {
                    LogBus.log("Callback intercepted msg.what=1 and returned true! (Consuming message)")
                    true // Consumed! handleMessage will NOT be called
                } else {
                    LogBus.log("Callback received msg.what=${msg.what} and returned false. (Falling through to handleMessage)")
                    false // Fallthrough
                }
            }
        ) {
            override fun handleMessage(msg: Message) {
                LogBus.log("handleMessage executed for msg.what=${msg.what}")
            }
        }

        LogBus.log("Sending msg.what=1 (intercepted & consumed by Callback)...")
        handler.sendMessage(handler.obtainMessage(1))

        LogBus.log("Sending msg.what=2 (passed through to handleMessage)...")
        handler.sendMessage(handler.obtainMessage(2))
    }

    private fun runQ21Demo() {
        LogBus.log("--- Starting Q21: Handler vs Dispatchers.Main vs Dispatchers.Main.immediate Demo ---")

        LogBus.log("Starting from Main Thread...")

        // 1. Dispatchers.Main.immediate
        CoroutineScope(Dispatchers.Main.immediate).launch {
            LogBus.log("Dispatchers.Main.immediate executed! (Runs inline if already on Main thread)")
        }

        // 2. Dispatchers.Main
        CoroutineScope(Dispatchers.Main).launch {
            LogBus.log("Dispatchers.Main executed! (Posted to Main Looper queue)")
        }

        // 3. Handler.post
        Handler(Looper.getMainLooper()).post {
            LogBus.log("Handler.post executed! (Posted to Main Looper queue)")
        }

        LogBus.log("Main thread caller finished inline execution.")
    }

    private fun runQ22Demo() {
        LogBus.log("--- Starting Q22: Message Ordering & SystemClock.uptimeMillis Demo ---")
        val handler = Handler(Looper.getMainLooper())
        val now = SystemClock.uptimeMillis()

        LogBus.log("Posting Task 1 with delay +100ms")
        handler.postAtTime({ LogBus.log("Task 1 executed (+100ms)") }, now + 100)

        LogBus.log("Posting Task 2 with delay +10ms")
        handler.postAtTime({ LogBus.log("Task 2 executed (+10ms)") }, now + 10)

        LogBus.log("Posting Task 3 with delay +50ms")
        handler.postAtTime({ LogBus.log("Task 3 executed (+50ms)") }, now + 50)

        LogBus.log("Messages enqueued. MessageQueue sorts them as: Task 2 (+10ms) -> Task 3 (+50ms) -> Task 1 (+100ms).")
    }

    private fun runQ23Demo() {
        LogBus.log("--- Starting Q23: SystemClock.uptimeMillis vs currentTimeMillis Demo ---")

        val uptime = SystemClock.uptimeMillis()
        val currentTime = System.currentTimeMillis()

        LogBus.log("SystemClock.uptimeMillis(): ${uptime}ms (Monotonic clock since boot, pauses during CPU deep sleep)")
        LogBus.log("System.currentTimeMillis(): ${currentTime}ms (Wall clock epoch time, subject to user/NTP changes)")
        LogBus.log("Conclusion: Handlers use uptimeMillis to calculate message target execution times (msg.when).")
    }
}
