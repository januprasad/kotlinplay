package com.example.sample_xml_app.mvp

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.sample_xml_app.R
import com.example.sample_xml_app.databinding.ActivityLoginBinding

class LoginActivity :
    AppCompatActivity(),
    LoginView {
    private lateinit var presenter: LoginPresenter
    private lateinit var binding: ActivityLoginBinding

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

        presenter = LoginPresenter(this, LoginModelImpl())
        binding.login.setOnClickListener {
            presenter.login("A", "B")
        }

//        binding.login.setOnClickListener<WeakReference<View.OnClickListener>> {
//            presenter.login("A", "B")
//        }
    }

    override fun showLoading() {
        binding.progress.visibility = View.VISIBLE
    }

    override fun disableUI() {
        binding.login.isEnabled = false
    }

    override fun enableUI() {
        binding.login.isEnabled = true
    }

    override fun hideLoading() {
        binding.progress.visibility = View.GONE
    }

    override fun showLoginSuccess() {
        Toast.makeText(this, "Login success", Toast.LENGTH_LONG).show()
    }

    override fun showLoginFailure(errorMessage: String) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show()
    }
}
