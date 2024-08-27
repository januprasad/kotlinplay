package com.example.sample_xml_app.mvp.model

import com.example.sample_xml_app.mvp.LoginService
import com.example.sample_xml_app.mvp.data.LoginRequest
import com.example.sample_xml_app.mvp.data.LoginResponse

interface LoginModel {
    fun login(
        username: String,
        password: String,
    ): Result<LoginResponse>
}

/**
 * inject using dagger inside viewmodel
 */
class LoginModelImpl(
    private val loginService: LoginService,
) : LoginModel {
    override fun login(
        username: String,
        password: String,
    ): Result<LoginResponse> =
        try {
            val response = loginService.login(LoginRequest(username, password))
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
}
