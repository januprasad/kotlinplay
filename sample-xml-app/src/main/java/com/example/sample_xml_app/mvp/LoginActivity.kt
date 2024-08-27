package com.example.sample_xml_app.mvp

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.sample_xml_app.R
import com.example.sample_xml_app.databinding.ActivityLoginBinding
import com.example.sample_xml_app.mvp.model.LoginModelImpl

class LoginActivity :
    AppCompatActivity(),
    LoginView {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val loginImpl = LoginModelImpl(LoginService())

        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        viewModel.injectLoginModel(loginImpl)
        binding.login.setOnClickListener {
            val username = "username"
            val password = "password"
            viewModel.login(username, password)
        }

        viewModel.loginState.observe(this) { state ->
            when (state) {
                is LoginState.Loading -> showLoading()
                is LoginState.Success -> showLoginSuccess(state.message)
                is LoginState.Error -> showLoginError(state.message)
            }
        }

//        binding.login.setOnClickListener<WeakReference<View.OnClickListener>> {
//            presenter.login("A", "B")
//        }
    }

    override fun showLoading() {
        disableUI()
        binding.progress.visibility = View.VISIBLE
    }

    fun disableUI() {
        binding.login.isEnabled = false
    }

    fun enableUI() {
        binding.login.isEnabled = true
    }

    override fun hideLoading() {
        binding.progress.visibility = View.GONE
    }

    override fun showLoginSuccess(message: String) {
        hideLoading()
        enableUI()
        Toast.makeText(this, "Login success", Toast.LENGTH_LONG).show()
    }

    override fun showLoginError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}
