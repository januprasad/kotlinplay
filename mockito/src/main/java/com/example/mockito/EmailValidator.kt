package com.example.mockito

class EmailValidator {
    companion object {
        fun isValidEmail(email: String): Boolean {
            return email.isNotEmpty()
        }
    }
}