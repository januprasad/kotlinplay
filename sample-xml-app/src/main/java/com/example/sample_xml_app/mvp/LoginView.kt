package com.example.sample_xml_app.mvp

interface LoginView {
    fun showLoading()

    fun disableUI()

    fun enableUI()

    fun hideLoading()

    fun showLoginSuccess()

    fun showLoginFailure(errorMessage: String)
}
