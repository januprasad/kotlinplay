package com.example.sample_xml_app.mvp

interface LoginView {
    fun showLoading()

    fun hideLoading()

    fun showLoginSuccess(message: String)

    fun showLoginError(message: String)
}
