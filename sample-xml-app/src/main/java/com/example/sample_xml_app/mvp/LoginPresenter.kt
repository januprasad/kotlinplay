package com.example.sample_xml_app.mvp

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginPresenter(
    private val view: LoginView,
    private val model: LoginModel,
) {
    fun login(
        username: String,
        password: String,
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            withContext(Dispatchers.Main) {
                view.showLoading()
                view.disableUI()
            }
            delay(2000L)
            model.login(
                username,
                password,
                object : LoginCallback {
                    override fun onLoginSuccess() {
                        CoroutineScope(Dispatchers.Main).launch {
                            view.hideLoading()
                            view.enableUI()
                            view.showLoginSuccess()
                        }
                    }

                    override fun onLoginFailure(errorMessage: String) {
                        CoroutineScope(Dispatchers.Main).launch {
                            view.hideLoading()
                            view.enableUI()
                            view.showLoginFailure(errorMessage)
                        }
                    }
                },
            )
        }
    }
}
