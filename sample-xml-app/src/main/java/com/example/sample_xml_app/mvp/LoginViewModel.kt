package com.example.sample_xml_app.mvp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sample_xml_app.mvp.model.LoginModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

sealed class LoginState {
    object Loading : LoginState()

    data class Success(
        val message: String,
    ) : LoginState()

    data class Error(
        val message: String,
    ) : LoginState()
}

class LoginViewModel : ViewModel() {
    private val _loginState = MutableLiveData<LoginState>()
    val loginState: LiveData<LoginState> = _loginState
    private lateinit var loginModel: LoginModel

    fun injectLoginModel(loginModel: LoginModel) {
        this.loginModel = loginModel
    }

    fun login(
        username: String,
        password: String,
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            _loginState.postValue(LoginState.Loading)
            delay(2000L)
            try {
                val result = loginModel.login(username, password)
                if (result.isSuccess) {
                    _loginState.postValue(LoginState.Success("Login successful"))
                } else {
                    _loginState.postValue(LoginState.Error(result.exceptionOrNull()?.message ?: "Unknown error"))
                }
            } catch (e: Exception) {
                _loginState.postValue(LoginState.Error(e.message!!))
            }
        }
    }
}
