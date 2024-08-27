package com.example.sample_xml_app.mvp.data

data class LoginResponse(
    val token: String,
    val user: User,
)

data class User(
    val id: Int,
    val username: String,
    val email: String,
)
