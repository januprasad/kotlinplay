package com.example.mockito

import com.example.mockito.utils.EmailValidator
import org.junit.Assert.assertTrue
import org.junit.Test

class EmailValidatorTest {
    @Test fun emailValidator_CorrectEmailSimple_ReturnsTrue() {
        assertTrue(EmailValidator.isValidEmail("name@email.com"))
    }
}
