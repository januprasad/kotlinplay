package com.example.sample_xml_app.mvp

interface LoginModel {
    fun login(
        username: String,
        password: String,
        callback: LoginCallback,
    )
}

class LoginModelImpl : LoginModel {
    override fun login(
        username: String,
        password: String,
        callback: LoginCallback,
    ) {
        val loginResult = listOf(true, false).random()
        when (loginResult) {
            true -> callback.onLoginSuccess()
            false -> callback.onLoginFailure("Login failed")
        }
    }
}

interface LoginCallback {
    fun onLoginSuccess()

    fun onLoginFailure(errorMessage: String)
}
