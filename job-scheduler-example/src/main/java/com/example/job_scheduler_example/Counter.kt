package com.example.job_scheduler_example

class Counter {
    // Shared counter variable (not thread-safe)
    var count = 0

    // Thread-safe increment using synchronized block
    fun incrementSynchronized() {
        synchronized(this) {
            // Lock on current object
            count++
        }
    }

    // Thread-safe increment using volatile (visibility guarantee)
    @Volatile
    var volatileCount = 0

    fun incrementVolatile() {
        volatileCount++ // Might not be immediately visible to other threads
    }
}

fun main() {
    val counter = Counter()

    // Spawn multiple threads to increment the counter
    val threads =
        List(10_000) {
            Thread {
                Thread.sleep(100)
                counter.incrementSynchronized()
            }
        }
    threads.forEach { it.start() }
    threads.forEach { it.join() }

    println("Synchronized counter: ${counter.count}") // May or may not be 10

    // Reset counter and use volatile
    counter.volatileCount = 0
    val volatileThreads =
        List(10_000) {
            Thread {
                Thread.sleep(100)
                counter.incrementVolatile()
            }
        }
    volatileThreads.forEach { it.start() }
    volatileThreads.forEach { it.join() }

    println("Volatile counter: ${counter.volatileCount}") // Might not be 10 (depends on visibility)
}
