package com.github.solid

fun main() {
    val thread1 =
        Thread {
            for (i in 1..5) {
                println("Thread 1 - $i")
                Thread.sleep(1000)
            }
        }

    val thread2 =
        Thread {
            for (i in 1..5) {
                println("Thread 2 - $i")
                Thread.sleep(1000)
            }
        }

    thread1.start()
    thread2.start()

    thread1.join()
    thread2.join()
}
