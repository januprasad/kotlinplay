package com.github.mvi_sample

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.fragment.app.FragmentActivity
import com.github.mvi_sample.ui.theme.KotlinTryoutsTheme

class MainActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KotlinTryoutsTheme {
                var isAuthenticated by rememberSaveable { mutableStateOf(false) }

                if (!isAuthenticated) {
                    AuthScreen(
                        onAuthenticated = { isAuthenticated = true }
                    )
                } else {
                    HomeScreen(
                        onLogout = { isAuthenticated = false }
                    )
                }
            }
        }
    }
}
