package com.example.sample_xml_app

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val score_view = findViewById<TextView>(R.id.score_view)
        val score_bt = findViewById<TextView>(R.id.score_bt)
        viewModel = ViewModelProvider(this)[MyViewModel::class.java]
        viewModel.userScore.observe(this) {
            score_view.text = it.score.toString()
        }

        score_bt.setOnClickListener {
            viewModel.scorePoint()
        }
        Log.v("Application", "onCreate")
    }

    override fun onStart() {
        super.onStart()
        Log.v("Application", "onStart")
    }

    override fun onPause() {
        super.onPause()
        Log.v("Application", "onPause")
    }

    override fun onResume() {
        super.onResume()
        Log.v("Application", "onResume")
    }

    override fun onStop() {
        super.onStop()
        Log.v("Application", "onStop")
        finish()
    }

    override fun onRestart() {
        super.onRestart()
        Log.v("Application", "onRestart")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.v("Application", "onDestroy")
    }
}
