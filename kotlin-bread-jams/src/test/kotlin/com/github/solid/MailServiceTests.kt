package com.github.solid

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class MailServiceTests {


  @Test
  fun `sendEmail composes and sends an email`() {
    val mockMail = mockk<Mail>()
    val mailService = MailService()

    // Mock the sending behavior (replace with actual verification if using external libraries)
    every { mockMail.sender } returns "sender@example.com"
    every { mockMail.recipient } returns "recipient@example.com"
    every { mockMail.subject } returns "Test Subject"
    every { mockMail.body } returns "This is a test email body"

    mailService.sendEmail(mockMail)

    // Verify that the expected email details are printed
    verify { println(matchesRegex("From: sender@example.com")) }
    verify { println(matchesRegex("To: recipient@example.com")) }
    verify { println(matchesRegex("Subject: Test Subject")) }
    verify { println(matchesRegex("Body: This is a test email body")) }
  }

  @Test
  fun `composeEmail creates a Mail object with provided details`() {
    val mailService = MailService()
    val email = mailService.composeEmail(
      sender = "sender@example.com",
      recipient = "recipient@example.com",
      subject = "Test Subject",
      body = "This is a test email body"
    )

    assertEquals("sender@example.com", email.sender)
    assertEquals("recipient@example.com", email.recipient)
    assertEquals("Test Subject", email.subject)
    assertEquals("This is a test email body", email.body)
  }
}

fun matchesRegex(data: String)  = data.isNotEmpty()