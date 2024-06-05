package com.github.solid

interface Mail {
  val sender: String
  val recipient: String
  val subject: String
  val body: String
}

class MailService {

  // Send an email with the provided details
  fun sendEmail(mail: Mail) {
    // Simulate sending the email (replace with actual sending logic)
    println("Sending email:")
    println("From: ${mail.sender}")
    println("To: ${mail.recipient}")
    println("Subject: ${mail.subject}")
    println("Body: ${mail.body}")
    println("-------")
  }

  // Compose an email with a pre-defined sender address
  fun composeEmail(sender: String, recipient: String, subject: String, body: String): Mail {
    return object : Mail {
      override val sender = sender
      override val recipient = recipient
      override val subject = subject
      override val body = body
    }
  }
}

class MyService {
  fun getData(param: String): String {
    // Implement logic to retrieve data
    return "data"
  }
}
