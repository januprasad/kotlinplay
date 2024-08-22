package com.github.interview_prep.extensions

fun main() {
    Subscription("Amazon Prime").test()
    val yt =
        object : YtPremiumSubscription() {
            override fun getSubscriptionName(): String = subscribe()
        }
    println(yt.getSubscriptionName())
}

data class Subscription(
    val name: String,
) : CustomHolder

interface CustomHolder

fun Any.toString() {
    print("Object: $this")
}

fun Subscription.test() {
    println("${this.name}")
}

abstract class YtPremiumSubscription : CustomHolder {
    abstract fun getSubscriptionName(): String
}

fun YtPremiumSubscription.subscribe(): String = "Subscription activated"
