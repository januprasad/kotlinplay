package com.example.typesafe_nv

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen(
    navigateToLogin: () -> Unit,
    navigateToSignup: () -> Unit,
) {
    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .padding(16.dp),
    ) {
        Button(onClick = { navigateToLogin() }) {
            Text(text = "Login")
        }
        Button(onClick = { navigateToSignup() }) {
            Text(text = "Signup")
        }
    }
}

@Composable
fun LoginScreen(onLoginClick: (String) -> Unit) {
    var email by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }
    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        val mod =
            Modifier
                .fillMaxWidth()
        OutlinedTextField(
            modifier = mod,
            label = {
                Text(text = "Email")
            },
            value = email,
            onValueChange = {
                email = it
            },
        )
        OutlinedTextField(
            modifier = mod,
            label = {
                Text(text = "Password")
            },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            value = password,
            onValueChange = {
                password = it
            },
        )
        Button(onClick = { onLoginClick(email.split("@").first()) }) {
            Text(text = "Login")
        }
    }
}

@Composable
fun SignupScreen(onSignupClick: (String) -> Unit) {
    var email by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }
    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        val mod =
            Modifier
                .fillMaxWidth()
        OutlinedTextField(
            modifier = mod,
            label = {
                Text(text = "Email")
            },
            value = email,
            onValueChange = {
                email = it
            },
        )
        OutlinedTextField(
            modifier = mod,
            label = {
                Text(text = "Password")
            },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            value = password,
            onValueChange = {
                password = it
            },
        )
        Button(onClick = {
            onSignupClick(
                email.split("@").first(),
            )
        }) {
            Text(text = "Signup")
        }
    }
}

@Composable
fun ProfileScreen(
    email: Destination.Profile,
    onLogoutClick: () -> Unit,
) {
    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Text(text = "Welcome ${email.email}")
        Button(onClick = {
            onLogoutClick()
        }) {
            Text(text = "Logout")
        }
    }
}
