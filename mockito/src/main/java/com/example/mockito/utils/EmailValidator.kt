package com.example.mockito.utils

class EmailValidator {
    companion object {
        fun isValidEmail(email: String): Boolean = email.isNotEmpty()
    }
}
