package com.example.sample_xml_app.mvp

import com.example.sample_xml_app.mvp.data.LoginRequest
import com.example.sample_xml_app.mvp.data.LoginResponse
import com.example.sample_xml_app.mvp.data.User

class LoginService {
    fun login(loginRequest: LoginRequest): LoginResponse =
        LoginResponse(
            "abced-efgh-ijklm",
            User(
                id = 1,
                username = "john_doe",
                email = "john.doe@example.com",
            ),
        )
}
