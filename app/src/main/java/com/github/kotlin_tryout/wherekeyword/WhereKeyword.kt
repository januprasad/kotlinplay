package com.github.kotlin_tryout.wherekeyword

sealed class Notification {
    data class SMS(
        val content: String,
        val number: String,
        val timestamp: Long,
    ) : Notification()

    data class Email(
        val subject: String,
        val address: String,
        val body: String,
        val timestamp: Long,
    ) : Notification()

    data class Call(
        val number: String,
        val timestamp: Long,
    ) : Notification()
}

class NotificationManager<T> where T : Notification {
    fun notify(notification: T) {
        when (notification) {
            is Notification.SMS -> sendSMS(notification)
            is Notification.Email -> sendEmail(notification)
            is Notification.Call -> callPhone(notification)
        }
    }

    private fun callPhone(notification: Notification.Call) {
        println("calling to ${notification.number}")
    }

    private fun sendEmail(notification: Notification.Email) {
        println("sending email to ${notification.address} with ${notification.subject} ${notification.body}")
    }

    private fun sendSMS(notification: Notification.SMS) {
        println("sms forward to ${notification.number} with ${notification.content}")
    }
}

fun main() {
    val notification =
        listOf(
            Notification.SMS("Hello", "+1234567890", System.currentTimeMillis()),
            Notification.Email(
                "Meeting Reminder",
                "john.doe@example.com",
                "Don't forget about the meeting tomorrow at 10 AM",
                System.currentTimeMillis(),
            ),
            Notification.Call("+9876543210", System.currentTimeMillis()),
        )
    notification.forEach {
        NotificationManager<Notification>().notify(it) // type-safe call to sendSingle function with Notification type parameter
    }
}
