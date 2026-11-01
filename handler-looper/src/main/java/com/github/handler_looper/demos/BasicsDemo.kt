package com.github.handler_looper.demos

import android.os.Handler
import android.os.Looper
import android.os.Message
import com.github.handler_looper.ui.DemoItem
import com.github.handler_looper.ui.DemoPhase
import com.github.handler_looper.util.LogBus

object BasicsDemo {

    val demos = listOf(
        // Q1: Handler -> Queue -> Looper flow
        DemoItem(
            qNumber = 1,
            title = "Q1: Handler -> Queue -> Looper Flow",
            phase = DemoPhase.BASICS,
            questionText = "How do Handler, MessageQueue, and Looper interact?",
            explanationText = """
                Flow summary:
                1. Producer thread calls handler.post(runnable) or handler.sendMessage(msg).
                2. Handler sets msg.target = this and inserts the message into looper.mQueue (ordered by uptimeMillis).
                3. Looper.loop() continuously pulls messages from MessageQueue via next() (blocks natively on epoll_wait when empty).
                4. Looper calls msg.target.dispatchMessage(msg).
                5. dispatchMessage checks:
                   a) msg.callback (Runnable posted) -> call runnable.run()
                   b) handler.mCallback -> call Callback.handleMessage(msg)
                   c) handler.handleMessage(msg)
            """.trimIndent(),
            runDemo = { runQ1Demo() }
        ),

        // Q2: Counts per thread
        DemoItem(
            qNumber = 2,
            title = "Q2: Looper & Queue Count Per Thread",
            phase = DemoPhase.BASICS,
            questionText = "How many Loopers and MessageQueues exist per thread? How many Handlers?",
            explanationText = """
                Thread-Looper-Queue Relationship:
                - Exactly 1 Looper per thread (stored in ThreadLocal<Looper>).
                - Exactly 1 MessageQueue per Looper.
                - N Handlers can point to the same Looper & MessageQueue!
                
                This demo instantiates 3 distinct Handlers on the main thread and logs their Looper and MessageQueue identities to verify they are identical.
            """.trimIndent(),
            runDemo = { runQ2Demo() }
        ),

        // Q3: Main thread Looper
        DemoItem(
            qNumber = 3,
            title = "Q3: Main Thread Looper Verification",
            phase = DemoPhase.BASICS,
            questionText = "Is a Looper automatically prepared for the main UI thread?",
            explanationText = """
                Yes! Android's ActivityThread.main() calls Looper.prepareMainLooper() and Looper.loop() before any component starts.
                Thus, Looper.getMainLooper() is non-null and equal to Looper.myLooper() on the main UI thread.
            """.trimIndent(),
            runDemo = { runQ3Demo() }
        ),

        // Q4: Manual Looper thread
        DemoItem(
            qNumber = 4,
            title = "Q4: Manual Looper Thread",
            phase = DemoPhase.BASICS,
            questionText = "How do you create a background thread with its own Looper?",
            explanationText = """
                To turn a standard Thread into a Looper thread:
                1. Call Looper.prepare() inside run() (initializes ThreadLocal Looper + MessageQueue).
                2. Instantiate Handler(Looper.myLooper()!!).
                3. Call Looper.loop() to start processing messages (blocks thread in endless loop).
                4. Call looper.quit() or looper.quitSafely() when done.
            """.trimIndent(),
            runDemo = { runQ4Demo() }
        ),

        // Q5: Missing Looper
        DemoItem(
            qNumber = 5,
            title = "Q5: Missing Looper Exception",
            phase = DemoPhase.BASICS,
            questionText = "What happens if you construct a Handler on a thread without Looper.prepare()?",
            explanationText = """
                Creating a Handler on a background thread without calling Looper.prepare() fails because Looper.myLooper() returns null.
                Calling Handler(Looper.myLooper()!!) or legacy Handler() throws:
                "RuntimeException: Can't create handler inside thread that has not called Looper.prepare()"
            """.trimIndent(),
            runDemo = { runQ5Demo() }
        )
    )

    private fun runQ1Demo() {
        LogBus.log("--- Starting Q1: Handler -> Queue -> Looper Flow Demo ---")

        val handler = object : Handler(
            Looper.getMainLooper(),
            Handler.Callback { msg ->
                LogBus.log("Handler.Callback received msg.what=${msg.what} (arg1=${msg.arg1})")
                false // Allow fallthrough to handleMessage if false, or consume if true
            }
        ) {
            override fun handleMessage(msg: Message) {
                LogBus.log("Handler.handleMessage received msg.what=${msg.what}")
            }
        }

        // 1. Post Runnable
        LogBus.log("Posting Runnable to main Handler...")
        handler.post {
            LogBus.log("Runnable executed on Looper thread")
        }

        // 2. Send Message
        LogBus.log("Sending Message (what=100) to main Handler...")
        val msg = handler.obtainMessage(100, 42, 0)
        handler.sendMessage(msg)
    }

    private fun runQ2Demo() {
        LogBus.log("--- Starting Q2: Counts per Thread Demo ---")

        val mainLooper = Looper.getMainLooper()
        val h1 = Handler(mainLooper)
        val h2 = Handler(mainLooper)
        val h3 = Handler(mainLooper)

        LogBus.log("Handler 1 Looper Hash: ${System.identityHashCode(h1.looper)}")
        LogBus.log("Handler 2 Looper Hash: ${System.identityHashCode(h2.looper)}")
        LogBus.log("Handler 3 Looper Hash: ${System.identityHashCode(h3.looper)}")

        val h1Queue = h1.looper.queue
        val h2Queue = h2.looper.queue
        val h3Queue = h3.looper.queue

        LogBus.log("Queue 1 Hash: ${System.identityHashCode(h1Queue)}")
        LogBus.log("Queue 2 Hash: ${System.identityHashCode(h2Queue)}")
        LogBus.log("Queue 3 Hash: ${System.identityHashCode(h3Queue)}")

        val sameLooper = (h1.looper === h2.looper) && (h2.looper === h3.looper)
        val sameQueue = (h1Queue === h2Queue) && (h2Queue === h3Queue)

        LogBus.log("Result: Handlers share same Looper? $sameLooper | Share same MessageQueue? $sameQueue")
        LogBus.log("Conclusion: 1 Thread : 1 Looper : 1 MessageQueue : N Handlers")
    }

    private fun runQ3Demo() {
        LogBus.log("--- Starting Q3: Main Thread Looper Verification ---")

        val currentLooper = Looper.myLooper()
        val mainLooper = Looper.getMainLooper()

        LogBus.log("Current Thread: ${Thread.currentThread().name}")
        LogBus.log("Current Thread Looper: $currentLooper")
        LogBus.log("Main Thread Looper: $mainLooper")
        LogBus.log("Looper.getMainLooper() === Looper.myLooper()? ${mainLooper === currentLooper}")
    }

    private fun runQ4Demo() {
        LogBus.log("--- Starting Q4: Manual Looper Thread Demo ---")

        class CustomLooperThread : Thread("CustomLooperThread") {
            var handler: Handler? = null
                private set
            private val lock = Any()

            override fun run() {
                Looper.prepare()
                synchronized(lock) {
                    handler = Handler(Looper.myLooper()!!)
                    (lock as java.lang.Object).notifyAll()
                }
                LogBus.log("Worker thread prepared Looper, entering Looper.loop()...")
                Looper.loop()
                LogBus.log("Worker thread exited Looper.loop()!")
            }

            fun waitUntilReady(): Handler {
                synchronized(lock) {
                    while (handler == null) {
                        (lock as java.lang.Object).wait()
                    }
                    return handler!!
                }
            }
        }

        val worker = CustomLooperThread()
        worker.start()

        val workerHandler = worker.waitUntilReady()

        LogBus.log("Main thread posting task to CustomLooperThread...")
        workerHandler.post {
            LogBus.log("Task running on worker looper thread: ${Thread.currentThread().name}")
            // Quit the looper after task execution
            Looper.myLooper()?.quit()
        }
    }

    private fun runQ5Demo() {
        LogBus.log("--- Starting Q5: Missing Looper Exception Demo ---")

        Thread({
            LogBus.log("Plain background thread running without Looper.prepare(). Attempting to create Handler...")
            try {
                val threadLooper = Looper.myLooper()
                    ?: throw RuntimeException("Can't create handler inside thread ${Thread.currentThread().name} that has not called Looper.prepare()")
                Handler(threadLooper)
            } catch (e: Exception) {
                LogBus.log("CAUGHT EXPECTED EXCEPTION: ${e.javaClass.simpleName} - ${e.message}")
            }
        }, "PlainThreadNoLooper").start()
    }
}
