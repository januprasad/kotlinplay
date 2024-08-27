package com.example.sample_xml_app.mvp

import com.example.sample_xml_app.mvp.model.LoginModel

class LoginPresenter(
    private val view: LoginView,
    private val loginModel: LoginModel,
) {
    fun login(
        username: String,
        password: String,
    ) {
        view.showLoading()
        try {
            val result = loginModel.login(username, password)
            if (result.isSuccess) {
                view.showLoginSuccess("Successfully logged in")
            } else {
                view.showLoginError(result.exceptionOrNull()?.message ?: "Unknown error")
            }
        } catch (e: Exception) {
            view.showLoginError(e.message!!)
        } finally {
            view.hideLoading()
        }
    }

    /**
     * this layer also called viewmodel
     */
}
