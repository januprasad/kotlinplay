package com.github.mvi_sample

import androidx.biometric.BiometricPrompt
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun AuthScreen(
    viewModel: AuthViewModel = viewModel(),
    onAuthenticated: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val context = LocalContext.current
    val activity = context as? FragmentActivity

    LaunchedEffect(Unit) {
        viewModel.sideEffects.collect { effect ->
            when (effect) {
                is AuthSideEffect.TriggerBiometricPrompt -> {
                    if (activity != null) {
                        val executor = ContextCompat.getMainExecutor(context)
                        val biometricPrompt = BiometricPrompt(
                            activity,
                            executor,
                            object : BiometricPrompt.AuthenticationCallback() {
                                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                                    super.onAuthenticationSucceeded(result)
                                    viewModel.onIntent(AuthIntent.AuthenticationSuccess)
                                }

                                override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                                    super.onAuthenticationError(errorCode, errString)
                                    viewModel.onIntent(AuthIntent.AuthenticationError(errString.toString()))
                                }

                                override fun onAuthenticationFailed() {
                                    super.onAuthenticationFailed()
                                    // optional handling
                                }
                            }
                        )

                        val promptInfo = BiometricPrompt.PromptInfo.Builder()
                            .setTitle("Biometric Authentication")
                            .setSubtitle("Log in using your biometric credential")
                            .setNegativeButtonText("Cancel")
                            .build()

                        biometricPrompt.authenticate(promptInfo)
                    } else {
                        viewModel.onIntent(AuthIntent.AuthenticationError("Activity context required"))
                    }
                }
                is AuthSideEffect.NavigateToHome -> {
                    onAuthenticated()
                }
            }
        }
    }

    Scaffold { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Secure MVI App", style = MaterialTheme.typography.headlineMedium)
            
            when (state) {
                is AuthState.NotAuthenticated -> {
                    Button(
                        onClick = { viewModel.onIntent(AuthIntent.StartAuthentication) },
                        modifier = Modifier.padding(top = 24.dp)
                    ) {
                        Text("Authenticate with Biometric")
                    }
                }
                is AuthState.Authenticating -> {
                    CircularProgressIndicator(modifier = Modifier.padding(top = 24.dp))
                }
                is AuthState.Authenticated -> {
                    Text("Authenticated successfully!", modifier = Modifier.padding(top = 24.dp))
                }
                is AuthState.Error -> {
                    val errorMsg = (state as AuthState.Error).message
                    Text("Error: $errorMsg", color = MaterialTheme.colorScheme.error, modifier = Modifier.padding(top = 24.dp))
                    Button(
                        onClick = { viewModel.onIntent(AuthIntent.StartAuthentication) },
                        modifier = Modifier.padding(top = 16.dp)
                    ) {
                        Text("Retry")
                    }
                }
            }
        }
    }
}
